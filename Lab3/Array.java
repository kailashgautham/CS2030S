/**
 * The Array for CS2030S.
 *                              
 * @author Gautham Kailash
 * @version CS2030S AY22/23 Semester 1
 */
class Array<T extends Comparable<T>> { 
  
  private T[] array;
  private int size;

  Array(int size) {
    //The only way we can put an object into Array is through the method set() and we
    //only put object of type T inside. So it is safe to cast `Object[]` to `T[]`.
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;
    this.size = size;

  }

  public void set(int index, T item) {

    this.array[index] = item;

  }

  public T get(int index) {
    
    return this.array[index];

  }

  public int getSize() {

    return this.size;

  }

  public T min() {

    T minValue = this.array[0];
    for (T t : this.array) {
      
      if (t == this.array[0]) {
        continue;
      }

      if (t.compareTo(minValue) < 0) {

        minValue = t;

      }

    }


    return minValue;

  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
