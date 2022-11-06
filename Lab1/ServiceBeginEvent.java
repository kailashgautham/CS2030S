/**
 * This class is a subclass of Event, used 
 * when a service begins for the customer. 
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class ServiceBeginEvent extends Event {

  private Customer customer;
  private Counter counter;
	
  public ServiceBeginEvent(double time, Customer customer, Counter counter) {

    super(time);
    this.customer = customer;
    this.counter = counter;

  }

  @Override
  public String toString() {
	
    return super.toString() + ": Customer " + customer.toString() + " service begin (by Counter " + this.counter.toString() + ")";

  }

  @Override
  public Event[] simulate() {

    this.counter.setAvailable();
    double endTime = this.customer.getTotalTime(this.getTime());
    return new Event[] {
      new ServiceEndEvent(endTime, this.customer, this.counter)
    };

  }	

}
