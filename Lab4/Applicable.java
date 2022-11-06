/**
 * The Applicable interface that can probably
 * transform if given something that is
 * probably an Immutator.
 * Contains a single abstract method apply.
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Gautham Kailash (Lab 14B)
 */

interface Applicable<T> {

  public <R> Probably<R> apply(Probably<? extends Immutator<? extends R, ? super T>> immutator);

}
