/**
 * This class represents a singular
 * counter. It contains counter-related
 * attributes such as availability and ID.
 * It also takes care of toggling counter availability.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class Counter {

  private int id;
  private boolean available;
  private static int nextId;

  public Counter() {

    this.id = Counter.nextId;
    Counter.nextId += 1;
    this.available = true;

  }

  public void setAvailable()  {

    this.available = true;

  }

  public void setUnavailable() {

    this.available = false;

  }

  public boolean getAvailable() {

    return this.available;

  }

  @Override
  public String toString() {

    return "S" + this.id;

  }

}
