/**
 * The Actually abstract class.
 * Contains two concrete, static nested classes.
 * CS2030S Lab 5
 * AY22/23 Semester 1
 *
 * @author Gautham Kailash (Lab 14B)
 */

package cs2030s.fp;

public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {

  public abstract T unwrap() throws Exception;

  public abstract T except(Constant<? extends T> c);

  public abstract void finish(Action<? super T> a);

  public abstract T unless(T t);

  public abstract <R> Actually<R> next(Immutator<? extends Actually<? extends R>, ? super T> immutator);

  public static <S> Actually<S> ok(S res) {

    return new Success<S>(res);

  }

  public static <S> Actually<S> err(Exception exception) {
  
    //As Failure extends Actually<Object>, it is safe 
    //to typecast it to a type of Actually, where the generic value
    //S is inferred from Failure to be Object. 
    @SuppressWarnings("unchecked")
    Actually<S> exc = (Actually<S>) new Failure(exception);
    return exc;
  }

  private static final class Success<T> extends Actually<T> {

    private final T value;

    private Success(T value) {

      this.value = value;

    }

    @Override
    public T unwrap() throws Exception {

      return this.value;

    }

    @Override
    public T except(Constant<? extends T> c) {

      return this.value;

    }

    @Override
    public void finish(Action<? super T> a) {

      a.call(this.value);

    }

    @Override
    public T unless(T t) {

      return this.value;

    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super T> immutator) {
      try {

        return Actually.ok(immutator.invoke(this.value));

      } catch (Exception e) {

        return Actually.err(e);
      }
    }

    @Override
    public void act(Action<? super T> action) {

      action.call(this.value); 

    }

    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<? extends R>, ? super T> immutator) {

      try {

        return immutator.invoke(this.value);

      } catch (Exception exc) {

        return Actually.err(exc);

      }

    }
    

    @Override
    public boolean equals(Object obj) {

      if (obj == this) {

        return true;

      }

      if (obj instanceof Success<?>) {

        Success<?> some = (Success<?>) obj;
        if (this.value == some.value) {

          return true;

        }

        if (this.value == null || some.value == null) {

          return false;

        }

        return this.value.equals(some.value);

      }

      return false;

    }

    @Override
    public String toString() {

      if (this.value == null) {

        return "<null>";

      } else {

        return "<" + this.value.toString() + ">";

      }

    }
      
  }

  private static final class Failure extends Actually<Object> {

    private final Exception exc;

    private Failure(Exception value) {

      this.exc = value;

    }

    @Override
    public Object unwrap() throws Exception {

      throw this.exc;

    }

    @Override
    public Object except(Constant<? extends Object> c) {

      return c.init();

    }

    @Override
    public void finish(Action<Object> a) {

    }

    @Override
    public Object unless(Object t) {

      return t;

    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super Object> immutator) {

      return Actually.err(this.exc);

    }

    @Override
    public void act(Action<? super Object> action) {

    }

    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<R>, ? super Object> immutator) {

      return Actually.err(this.exc);

    }

    @Override
    public boolean equals(Object obj) {

      if (obj == this) {

        return true;

      }

      if (obj instanceof Failure) {

        Failure some = (Failure) obj;
        if (this.exc == some.exc) {

          return true;

        }

        if (this.exc.getMessage() == null || some.exc.getMessage() == null) {

          return false;

        }

        return this.exc.getMessage().equals(some.exc.getMessage());

      }

      return false;

    }

    @Override
    public String toString() {
      
      return "[" + this.exc.getClass().getName() + "]" + " " + this.exc.getMessage();

    }

  }

}
