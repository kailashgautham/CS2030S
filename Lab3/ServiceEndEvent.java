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
      " service done (by " + this.counter.toString() + 
      " " + this.counter.counterQueueToString() + ")";

  }

  @Override
  public Event[] simulate() {
    
    this.counter.setAvailable();
    if (this.counter.counterQueueIsFull()) {

      if (!this.counter.counterQueueIsEmpty()) { 
        if (!this.shop.queueIsEmpty()) {
          Customer next = this.counter.dequeueCounter();
          Customer outside = this.shop.dequeueEntrance();
          return new Event[] {
            new DepartureEvent(this.getTime(), this.customer),
            new ServiceBeginEvent(this.getTime(), next, this.counter, this.shop),
            new CounterQueueEvent(this.getTime(), outside, this.counter, this.shop),
          };

        }  else {

          return new Event[] {
            new DepartureEvent(this.getTime(), this.customer),
            new ServiceBeginEvent(this.getTime(), this.counter.dequeueCounter(), 
                this.counter, this.shop),
          };

        } 
      } else if (!shop.queueIsEmpty()) {

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), this.shop.dequeueEntrance(), 
              this.counter, this.shop),
        };       

      } else {

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer)
        };

      }

    } else if (this.counter.counterQueueIsEmpty()) {

      if (this.shop.queueIsEmpty()) {

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer)
        };

      }  else {

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), this.shop.dequeueEntrance(), 
              this.counter, this.shop),
        };

      }

    } else {

      if (!shop.queueIsEmpty()) {

        Customer next = this.counter.dequeueCounter();
        Customer outside = this.shop.dequeueEntrance();
        this.counter.enqueueCounter(outside);
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new CounterQueueEvent(this.getTime(), outside, this.counter, this.shop),
          new ServiceBeginEvent(this.getTime(), next, this.counter, this.shop),
        };

      } else {

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), this.counter.dequeueCounter(), 
              this.counter, this.shop),
        };

      }

    }

  }

}
