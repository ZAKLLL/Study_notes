//: innerclasses/E02_SequenceOfStringHolders.java
/****************** Exercise 2 *****************
 * Create a class that holds a String, with a
 * toString() method that displays this String.
 * Add several instances of your new class to a
 * Sequence object, then display them.
 ***********************************************/
package innerclasses;

class StringHolder {
  private String data;
  StringHolder(String data) { this.data = data; }
  public String toString() { return data; }
}

public class E02_SequenceOfStringHolders {
  public static void main(String[] args) {
    Sequence sequence = new Sequence(10);
    for(int i = 0; i < 10; i++)
      sequence.add(new StringHolder(Integer.toString(i)));
    Selector selector = sequence.selector();
    while(!selector.end()) {
      System.out.print(selector.current() + " ");
      selector.next();
    }
  }
} /* Output:
0 1 2 3 4 5 6 7 8 9
*///:~
