/**
 * This class is a subclass of Event, used 
 * when a customer's service is completed.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class ServiceEndEvent extends Event {

  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ServiceEndEvent(double endTime, Customer customer, Counter counter, Shop shop) {

    super(endTime);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;

  }

  @Override
  public String toString() {

    return super.toString() + ": " + customer.toString() + 
      " service done (by " + this.counter.toString() + ")";

  }

  @Override
  public Event[] simulate() {

    this.counter.setAvailable();
    
    if (!this.shop.queueIsEmpty()) {

      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), this.shop.dequeueEntrance(), this.counter, this.shop),
      };

    } else {

      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer), 
      };

    }

  }

}
