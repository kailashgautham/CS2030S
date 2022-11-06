/**
 * This class is a subclass of Event, specifically 
 * an event when the customer arrives at the shop. 
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class ArrivalEvent extends Event {

  private Customer customer;
  private Shop shop;

  public ArrivalEvent(double time, Customer customer, Shop shop) {

    super(time);
    this.customer = customer;
    this.shop = shop;

  }


  @Override
  public String toString() {

    return super.toString() + ": " + this.customer.toString() + 
      " arrived " + this.shop.queueToString();

  }

  @Override
  public Event[] simulate() {

    Counter counter = this.shop.returnAvailableCounter();

    if (counter == null) {

      if (this.shop.queueIsFull()) { 

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer)
        };
      
      } else {

        return new Event[] {
          new QueueEvent(this.getTime(), this.customer, this.shop)
        };

      }

    } else {

      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, counter, this.shop)
      };

    }

  }

}


