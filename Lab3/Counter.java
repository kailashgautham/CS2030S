/**
 * This class represents a singular
 * counter. It contains counter-related
 * attributes such as availability and ID.
 * It also takes care of toggling counter availability.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class Counter implements Comparable<Counter> {

  private int id;
  private boolean available;
  private Queue<Customer> counterQueue;
  private int counterQueueLength;
  private static int nextId;

  public Counter(int counterQueueLength) {

    this.id = Counter.nextId;
    Counter.nextId += 1;
    this.counterQueue = new Queue<Customer>(counterQueueLength);
    this.counterQueueLength = counterQueueLength;
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

  public void enqueueCounter(Customer c) {

    this.counterQueue.enq(c);

  }

  public Customer dequeueCounter() {

    return this.counterQueue.deq();

  }

  public boolean counterQueueIsFull() {

    return this.counterQueue.isFull();

  }

  public boolean counterQueueIsEmpty() {

    return this.counterQueue.isEmpty();

  }

  public String counterQueueToString() {

    return this.counterQueue.toString();

  }

  @Override
  public int compareTo(Counter c) {

    if (this.counterQueue.length() < c.counterQueue.length()) {

      return -1;

    } else if (this.counterQueue.length() > c.counterQueue.length()) {

      return 1; 

    } else {

      return 0;

    }

  }

  @Override
  public String toString() {

    return "S" + this.id;

  }

}
