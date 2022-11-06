/**
 * The Transformer abstract class.
 * It will have two non-abstract methods.
 * CS2030S Lab 5
 * AY22/23 Semester 1
 *
 * @author Gautham Kailash (Lab 14B)
 */

package cs2030s.fp;

public abstract class Transformer<R, P> implements Immutator<R, P> {

  public <N> Transformer<R, N> after(Transformer<? extends P, ? super N> transformer) {

    Transformer<R, P> f = this;

    Transformer<R, N> object = new Transformer<R, N>() {

      @Override
      public R invoke(N n) {

        return f.invoke(transformer.invoke(n));

      }

    };

    return object;

  }
  

  public <T> Transformer<T, P> before(Transformer<? extends T, ? super R> transformer) {

    Transformer<R, P> f = this;

    Transformer<T, P> object = new Transformer<T, P>() {

      @Override
      public T invoke(P p) {

        return transformer.invoke(f.invoke(p));

      }

    };

    return object;

  }

}


