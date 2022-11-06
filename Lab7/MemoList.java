import cs2030s.fp.Combiner;
import cs2030s.fp.Constant;
import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around a lazily evaluated and memoized
 * list that can be generated with a lambda expression.
 *
 * @author Gautham Kailash
 * @version CS2030S AY 22/23 Sem 1
 */
class MemoList<T> {
  /** The wrapped java.util.List object */
  private List<Memo<T>> list;

  /** 
   * A private constructor to initialize the list to the given one. 
   *
   * @param list The given java.util.List to wrap around.
   */
  private MemoList(List<Memo<T>> list) {
    this.list = list;
  }

  /** 
   * Generate the content of the list.  Given x and a lambda f, 
   * generate the list of n elements as [x, f(x), f(f(x)), f(f(f(x))), 
   * ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param seed The first element.
   * @param f The immutator function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T seed, Immutator<? extends T, ? super T> f) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    T curr = seed;
    Memo<T> memo = Memo.from(curr);
    for (int i = 0; i < n; i++) {
      memoList.list.add(memo);
      memo = memo.transform(f);
    }
    return memoList;
  }

  /**
   * Generate the content of the list given the first two elements.
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param fst The first element to start the list.
   * @param snd The second element in the list.
   * @param combiner To combine the two elements and return a combined Memo.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T fst, T snd, 
      Combiner<? extends T, ? super T, ? super T> combiner) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    Memo<T> first = Memo.from(fst);
    Memo<T> second = Memo.from(snd);
    Memo<T> temp;
    for (int i = 0; i < n; i++) {
      if (i == 0) {
        memoList.list.add(first);
      } else if (i == 1) {
        memoList.list.add(second);
      } else {
        temp = first.combine(second, combiner);
        first = second;
        second = temp;
        memoList.list.add(temp);
      }
    }

    return memoList;

  }

  /**
   * A new MemoList with the values mapped.
   *
   * @param immutator To immutate the values from type T to R.
   * @param <R> The type of the new list.
   * @return a new MemoList of type R.
   */
  public <R> MemoList<R> map(Immutator<? extends R, ? super T> immutator) {

    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    for (int i = 0; i < this.list.size(); i++) {
      memoList.list.add(this.list.get(i).transform(immutator));
    }
    return memoList;
  }

  /**
   * Function to flatten the nested lists.
   *
   * @param immutator The immutator to convert the type T into MemoList of type R.
   * @param <R> the type of the new list.
   * @return The new MemoList R with the merged lists.
   */
  public <R> MemoList<R> flatMap(Immutator<? extends MemoList<R>, ? super T> immutator) {
    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    for (int i = 0; i < this.list.size(); i++) {
      MemoList<R> newMemo = this.list.get(i).transform(immutator).get();
      for (int j = 0; j < newMemo.list.size(); j++) {
        memoList.list.add(newMemo.list.get(j));
      }
    }
    return memoList;
  }



  /** 
   * Return the element at index i of the list.  
   *
   * @param i The index of the element to retrieved (0 for the 1st element).
   * @return The element at index i.
   */
  public T get(int i) {
    return this.list.get(i).get();
  }

  /** 
   * Find the index of a given element.
   *
   * @param v The value of the element to look for.
   * @return The index of the element in the list.  -1 is element is not in the list.
   */
  public int indexOf(T v) {
    return this.list.indexOf(Memo.from(v));
  }

  /** 
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.list.toString();
  }
}
