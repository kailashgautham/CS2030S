/**
 * This class is a subclass of Event. It performs 
 * functionalities for when a customer departs from 
 * the shop.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class DepartureEvent extends Event {

  private Customer customer;
	
  public DepartureEvent(double time, Customer customer) {

    super(time);
    this.customer = customer;

  }


  @Override 
  public String toString() {

    return super.toString() + ": Customer " + customer.toString() + " departed";

  }


  @Override
  public Event[] simulate() {

    return new Event[] {};

  }

}

