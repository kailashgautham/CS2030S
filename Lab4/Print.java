/**
 * A non-generic Action to print the String
 * representation of the object.
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Gautham Kailash (Lab 14B)
 */

class Print implements Action<Object> {

  public void call(Object t) {

    System.out.println(t);

  }

}
