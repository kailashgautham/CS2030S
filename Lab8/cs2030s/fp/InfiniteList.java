package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The class InfiniteList, created to build a list with
 * possibly an infinite number of elements. It is done using 
 * lazy evaluation.
 */
public class InfiniteList<T> {

  /** The head of the InfiniteList. */
  private Memo<Actually<T>> head;

  /** The tail of the InfiniteList. */
  private Memo<InfiniteList<T>> tail;

  /** The static final END object of type End, which signifies the end of a list. */
  private static final End END = new End(null, null);

  /**
   * A private Constructor that creates an InfiniteList with a head and tail.
   *
   * @param head the Head of the InfiniteList.
   * @param tail the Tail of the InfiniteList.
   */
  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * A function that generates a new InfiniteList given a function
   * that produces values.
   *
   * @param <T> the type of the new InfiniteList.
   * @param prod the Constant that produces the values for the InfiniteList.
   *
   * @return a new InfiniteList of type T.
   */
  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    
    return new InfiniteList<T>(Memo.from(() -> Actually.ok(prod.init())), 
        Memo.from(() -> InfiniteList.<T>generate(prod)));
  }
  

  /**
   * A function to create an InfiniteList based on a certain function.
   *
   * @param <T> the type of the InfiniteList to be created.
   * @param seed the starting value of the InfiniteList.
   * @param func the function that iterates and creates the InfiniteList.
   *
   * @return a new InfiniteList of type T.
   */
  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {

    return new InfiniteList<T>(Memo.from(Actually.ok(seed)), 
        Memo.from(() -> InfiniteList.<T>iterate(func.invoke(seed), func)));
  }
  
  /**
   * A function to return the head of the InfiniteList.
   *
   * @return a value of type T, the head of the list.
   */
  public T head() {
  
    return this.head.get().except(() -> this.tail.get().head());
  }
  
  /**
   * A function to return the tail of the InfiniteList.
   * 
   * @return the tail of the current InfiniteList.
   */
  public InfiniteList<T> tail() {
    return this.head.get().transform(x -> this.tail.get()).except(() -> this.tail.get().tail());
  }
  
  /**
   * A function to map all the values of the InfiniteList
   * based on a given function.
   *
   * @param f the Immutator that maps the given values to a different value.
   * @param <R> the type of the new InfiniteList to be returned.
   *
   * @return a new InfiniteList of type R.
   */
  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    return new InfiniteList<>(Memo.from(() -> this.head.get().transform(f)), 
        Memo.from(() -> this.tail.get().map(f)));
  }
  
  /**
   * A function to filter out values from the InfiniteList
   * based on meeting a given condition.
   *
   * @param pred the Immutator that converts the value into a true/false.
   * 
   * @return a new InfiniteList with the filtered values.
   */
  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    return new InfiniteList<>(Memo.from(() -> this.head.get().check(pred)), 
        Memo.from(() -> this.tail.get().filter(pred)));
  }
  
  /**
   * A function to return values of the InfiniteList,
   * restricted by a length n.
   *
   * @param n the length of the list to be returned.
   *
   * @return the InfiniteList with a fixed length n.
   */
  public InfiniteList<T> limit(long n) {
    if (n < 1) {
      return this.end();
    }
    return new InfiniteList<T>(this.head, Memo.from(() -> this.tail.get()
          .limit(n - this.head.get().transform(x -> 1).except(() -> 0))));
  }
  
  /**
   * A function to return the elements of InfiniteList
   * as long as the given predicate is true.
   *
   * @param pred the Immutator that converts the value into a true/false.
   *
   * @return a truncated InfiniteList of values that match the given predicate.
   */
  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {

    Memo<Actually<T>> head = Memo.from(() -> Actually.ok(this.head()).check(pred));
    Memo<InfiniteList<T>> tail = Memo.from(() -> {
      return head.get()
        .transform(h -> this.tail().takeWhile(pred))
        .unless(InfiniteList.end());
    });
    return new InfiniteList<>(head, tail);
  }
  
  /**
   * A function to convert the InfiniteList into a type List.
   *
   * @return a new List with the values of this InfiniteList.
   *
   */
  public List<T> toList() {

    InfiniteList<T> list = this;
    List<T> t = new ArrayList<>();
    while (!list.isEnd()) {

      list.head.get().finish(x -> t.add(x));
      list = list.tail.get();

    }

    return t;
   
  }

  /**
   * A function that implements the terminal operation
   * reduce, which performs an operation on the InfiniteList values
   * and returns a value.
   *
   * @param <U> the type of the variable being returned.
   * @param id the identity value for the reduce function.
   * @param acc the combiner that performs an operation.
   *
   * @return a variable of type U which is derived from
   *     the reduce operation.
   */
  public <U> U reduce(U id, Combiner<U, U, ? super T> acc) {

    return this.head.get()
      .transform(x -> this.tail.get().reduce(acc.combine(id, x), acc))
      .except(() -> this.tail.get().reduce(id, acc));
  }


  /** 
   * A function to return the number of items
   * in the InfiniteList.
   *
   * @return a long variable with the length of the InfiniteList.
   */
  public long count() {
    
    long id = 0;
    return this.reduce(id, (x, y) -> x + 1);

  }

  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
  
  /** 
   * A function to check whether the object
   * is of type End or not.
   *
   * @return a boolean indicating whether it is
   *     of type End.
   */
  public boolean isEnd() {
    return false;
  }

  /**
   * A static function to return the END object to signify
   * the end of an InfiniteList.
   *
   * @param <T> The type of the InfiniteList for which
   *     the END object is returned.
   *
   * @return end the END object, typecasted to type InfiniteList of type T. 
   */
  public static <T> InfiniteList<T> end() {

    @SuppressWarnings("unchecked")
    InfiniteList<T> end = (InfiniteList<T>) InfiniteList.END;
    return end;

  }

  /**
   * The class End which extends InfiniteList. 
   * It is used to signify the end of an InfiniteList.
   */
  private static class End extends InfiniteList<Object> {

    /** Private constructor for the End class.
     *
     * @param head The head of the InfiniteList, which is null (end of list).
     * @param tail The tail of the InfiniteList, which is null (end of list).
     */
    private End(Memo<Actually<Object>> head, Memo<InfiniteList<Object>> tail) {

      super(null, null);

    }


    @Override
    public String toString() {

      return "-";

    }

    @Override
    public End head() {

      throw new NoSuchElementException();

    }


    @Override
    public End tail() {

      throw new NoSuchElementException();

    }

    @Override
    public boolean isEnd() {

      return true;

    }

    
    @Override 
    public <R> InfiniteList<R> map(Immutator<? extends R, ? super Object> f) {
      return this.end();
    }
  
    @Override
    public InfiniteList<Object> filter(Immutator<Boolean, ? super Object> pred) {
      return this.end();
    }
  
    @Override
    public InfiniteList<Object> limit(long n) {
      return this.end();
    }

    @Override
    public InfiniteList<Object> takeWhile(Immutator<Boolean, ? super Object> pred) {

      return this.end();

    }

    @Override  
    public <U> U reduce(U id, Combiner<U, U, ? super Object> acc) {

      return id;

    }

  }

}
