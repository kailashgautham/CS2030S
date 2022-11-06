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

  public ServiceEndEvent(double endTime, Customer customer, Counter counter) {

    super(endTime);
    this.customer = customer;
    this.counter = counter;

  }

  @Override
  public String toString() {

    return super.toString() + ": Customer " + customer.toString() + " service done (by Counter " + this.counter.toString() + ")";

  }

  @Override
  public Event[] simulate() {

    this.counter.setAvailable();
    return new Event[] {
      new DepartureEvent(this.getTime(), this.customer),
      };

  }

}
