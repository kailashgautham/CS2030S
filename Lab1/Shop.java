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

  private static Counter [] counters;

  public static void setCounter(int numOfCounters) { 

    Shop.counters = new Counter[numOfCounters];

    for (int i = 0; i < numOfCounters; i++) {

      counters[i] = new Counter();

    }

  }

  public static Counter returnAvailableCounter() {

    for (int i = 0; i < Shop.counters.length; i++) {

      if (Shop.counters[i].getAvailable()) {

        return Shop.counters[i];

      }

    }

    return null;

  }

}


