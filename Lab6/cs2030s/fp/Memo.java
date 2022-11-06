package cs2030s.fp;

/**
 * The Memo class extends Lazy and is a smarter implementation 
 * where we memoize the calculated value.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class Memo<T> extends Lazy<T> {
  /** An Actually of type T to store the value of the Memo. */
  private Actually<T> value;

  /** 
   * A protected constructor to initialize Memo.
   *
   * @param c of type Constant to pass to the Lazy constructor.
   * @param value of type Actually to store in the Memo class.
   */
  protected Memo(Constant<? extends T> c, Actually<T> value) {

    super(c);
    this.value = value;

  }

  /** 
   * Create a new Memo using the constructor. Use a lambda function to create the Constant.
   * 
   *
   * @param v of type T to store in the new Memo.
   * @param <T> as the generic type of the new Memo value.
   * @return a new Memo of type T generated from the constructor.
   */
  public static <T> Memo<T> from(T v) {

    Constant<? extends T> c = () -> v;
    return new Memo<T>(c, Actually.ok(v));

  }

  /** 
   * Create a new Memo using the constructor.
   *
   * @param c of type Constant for our new Memo
   * @param <T> which is the generic type of our new Memo
   * @return a new Memo by calling the constructor,
   */
  public static <T> Memo<T> from(Constant<? extends T> c) {

    return new Memo<T>(c, Actually.err(new Exception()));

  }

  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> immutator) {

    Constant<? extends R> c = () -> immutator.invoke(this.get());
    return Memo.from(c);

  }

  @Override
  public <R> Memo<R> next(Immutator<? extends Lazy<? extends R>, ? super T> immutator) {

    Constant<? extends R> c = () -> immutator.invoke(this.get()).get();
    return Memo.from(c);
  }

  @Override
  public T get() {

    Constant<? extends T> c = () -> super.get();
    T t = this.value.except(c);
    this.value = Actually.<T>ok(t);
    return t;

  }

  /**
   * A combine function to combine two parameters of type T and S into a new Memo R.
   *
   * @param memo which is one of the values we plan on combining.
   * @param combiner which serves to combine the two values.
   * @param <R> the type that we want to convert to.
   * @param <S> one of the types that we are combining.
   * @return a new Memo of the combined type.
   */
  public <R, S> Memo<R> combine(Memo<? extends S> memo, Combiner<? extends R, ? super T, ? super S> combiner) {

    Constant<? extends R> c = () -> combiner.combine(this.get(), memo.get());
    return Memo.from(c);

  }

  @Override 
  public String toString() {

    Constant<String> unknown = () -> "?";
    Immutator<Actually<String>, T> convert = (T t) -> Actually.ok(t.toString());
    return this.value.<String>next(convert).except(unknown);


  }

}
