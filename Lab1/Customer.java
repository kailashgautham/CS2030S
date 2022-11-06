/**
 * This class represents a customer. It 
 * contains a customer ID, service time
 * required for the customer, and functions
 * to take care of ID assignment and computing
 * total time.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class Customer {

  private int id;
  private double serviceTime;
  private static int nextId;

  public Customer(double serviceTime) {

    this.id = Customer.nextId;
    this.serviceTime = serviceTime;
    Customer.nextId += 1;

  }


  @Override
  public String toString() {
    
    return "" + this.id;

  }

  public double getTotalTime(double arrivalTime) {

    return arrivalTime + this.serviceTime;

  }

}
