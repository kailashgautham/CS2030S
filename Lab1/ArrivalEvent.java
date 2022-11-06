/**
 * This class is a subclass of Event, specifically 
 * an event when the customer arrives at the shop. 
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class ArrivalEvent extends Event {

  private Customer customer;

  public ArrivalEvent(double time, Customer customer) {

    super(time);
    this.customer = customer;

  }


  @Override
  public String toString() {

    return super.toString() + ": Customer " + this.customer.toString() + " arrives";

  }

  @Override
  public Event[] simulate() {

    Counter counter = Shop.returnAvailableCounter();
    if (counter == null) {

    return new Event[] {
      new DepartureEvent(this.getTime(), this.customer)
    };

    }

    else {

      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, counter)
      };

    }

  }

}

	
