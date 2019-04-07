//: generics/E04_GenericSequence.java
/****************** Exercise 4 *****************
 * "Generify" innerclasses/Sequence.java.
 ************************************************/
package generics;
interface Selector<T> {
  boolean end();
  T current();
  void next();
}

class Sequence<T> {
  private Object[] items;
  private int next;
  public Sequence(int size) { items = new Object[size]; }
  public void add(T x) {
    if(next < items.length)
      items[next++] = x;
  }
  private class SequenceSelector implements Selector<T> {
    private int i;
    public boolean end() { return i == items.length; }
    @SuppressWarnings("unchecked")
    public T current() { return (T)items[i]; }
    public void next() { if(i < items.length) i++; }
  }
  public Selector<T> selector() {
    return new SequenceSelector();
  }
}

public class E04_GenericSequence {
  public static void main(String[] args) {
    Sequence<String> sequence = new Sequence<String>(10);
    for(int i = 0; i < 10; i++)
      sequence.add(Integer.toString(i));
    Selector<String> selector = sequence.selector();
    while(!selector.end()) {
      System.out.print(selector.current() + " ");
      selector.next();
    }
  }
} /* Output:
0 1 2 3 4 5 6 7 8 9
*///:~
