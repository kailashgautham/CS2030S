package cs2030s.fp;

/**
 * Class Lazy to calculate values only when needed.
 * CS2030S Lab 6
 * AY22/23 Semester 1
 *
 * @param <T> the type of Lazy.
 */

public class Lazy<T> implements Immutatorable<T> {

  /** A Constant to use when we want to calculate the required value.*/
  private Constant<? extends T> con;

  /**
   * Constructor for type Lazy.
   *
   * @param c The Constant for this class that can calculate the required value.
   */
  protected Lazy(Constant<? extends T> c) {

    this.con = c;

  }

  /** Create a new Lazy using a Constant lambda.
   *
   * @param <T> the type for the new Lazy to be returned.
   * @param v a parameter of type T to be returned when the constant is initialized.
   * @return the new Lazy created using the protected constructor.
   */
  public static <T> Lazy<T> from(T v) {

    Constant<? extends T> c = () -> v;
    return new Lazy<T>(c);

  }

  /** Create a new Lazy using a Constant passed in as a parameter.
   *
   * @param <T> the type for the new Lazy to be returned.
   * @param c a Constant to pass into the constructor.
   * @return the new Lazy created using the protected constructor.
   */
  public static <T> Lazy<T> from(Constant<? extends T> c) {

    return new Lazy<T>(c);

  }

  /** A function to get the variable from the Constant.
   *
   * @return the value initialized from Constant.init().
   */
  public T get() {

    return this.con.init();
  }

  /** A function to return a new Lazy of type R.
   *
   * @param <R> the type for the new Lazy to be returned.
   * @param immutator to immutate the value from type T to type R.
   * @return a new Lazy of type R.
   */
  public <R> Lazy<R> next(Immutator<? extends Lazy<? extends R>, ? super T> immutator) {

    Constant<? extends R> c = () -> immutator.invoke(this.get()).get();
    return new Lazy<R>(c);
  }


  @Override
  public <R> Lazy<R> transform(Immutator<? extends R, ? super T> immutator) {

    Constant<? extends R> c = () -> immutator.invoke(this.get());
    return new Lazy<R>(c);
  }

  @Override
  public String toString() {

    return this.get().toString();

  }

}
