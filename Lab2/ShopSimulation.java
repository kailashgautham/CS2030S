import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */ 
class ShopSimulation extends Simulation {

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;
  
  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {

    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int queueLength = sc.nextInt();
    int id = 0;
    Shop shop = new Shop(numOfCounters, queueLength);
    
    while (sc.hasNextDouble()) {

      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      initEvents[id] = new ArrivalEvent(arrivalTime, new Customer(serviceTime), shop);
      id += 1;

    }

  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }

}
