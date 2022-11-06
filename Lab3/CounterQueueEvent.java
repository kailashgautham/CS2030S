/**
 * This class is a subclass of Event, which
 * is called when there are no available
 * counters, to check if there is space 
 * in the counter queue.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class CounterQueueEvent extends Event {

  private Customer customer;
  private Counter counter;
  private Shop shop;

  public CounterQueueEvent(double time, Customer customer, Counter counter, Shop shop) {

    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {

    return
      super.toString() + ": " + this.customer.toString() + 
      " joined counter queue (at " + this.counter.toString() + 
      " " + this.counter.counterQueueToString() + ")";

  }

  @Override
  public Event[] simulate() {

    this.counter.enqueueCounter(customer);
    return new Event[] {};

  }

}
