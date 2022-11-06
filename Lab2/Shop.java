/**
 * This class represents the Shop as 
 * an entity. It contains all the 
 * shop counters, and takes care of 
 * counter-related functionalities.
 *
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
public class Shop { 

  private Counter [] counters;
  private Queue entranceQueue;

  public Shop(int numOfCounters, int queueLength) {

    this.counters = new Counter[numOfCounters];

    for (int i = 0; i < numOfCounters; i++) {

      counters[i] = new Counter();

    }

    this.entranceQueue = new Queue(queueLength);

  }


  public Counter returnAvailableCounter() {

    for (int i = 0; i < this.counters.length; i++) {

      if (this.counters[i].getAvailable()) {

        return this.counters[i];

      }

    }

    return null;

  }

  public String queueToString() {

    return this.entranceQueue.toString();

  }

  public Customer dequeueEntrance() {

    return (Customer) this.entranceQueue.deq();

  }

  public void enqueueEntrance(Customer c) {

    this.entranceQueue.enq(c);

  }

  public boolean queueIsEmpty() { 

    return this.entranceQueue.isEmpty();

  }

  public boolean queueIsFull() {

    return this.entranceQueue.isFull();

  }

}


