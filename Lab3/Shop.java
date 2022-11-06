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

  private Array<Counter> counters;
  private Queue<Customer> entranceQueue;

  public Shop(int numOfCounters, int counterQueueLength, int queueLength) {

    this.counters = new Array<Counter>(numOfCounters);

    for (int i = 0; i < numOfCounters; i++) {

      this.counters.set(i, new Counter(counterQueueLength));

    }

    this.entranceQueue = new Queue<Customer>(queueLength);

  }


  public Counter returnShortestCounterQueue() {

    Counter c = counters.min();
    if (c.counterQueueIsFull()) {

      return null;

    }

    return c;

  }

  public Counter returnAvailableCounter() {

    for (int i = 0; i < this.counters.getSize(); i++) {
      
      if (this.counters.get(i).getAvailable()) {

        return this.counters.get(i);

      }

    }

    return null;

  }

  public String queueToString() {

    return this.entranceQueue.toString();

  }

  public Customer dequeueEntrance() {

    return this.entranceQueue.deq();

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


