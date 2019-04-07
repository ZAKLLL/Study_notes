//: exceptions/E12_SequenceExceptions.java
// {ThrowsException}
/************************ Exercise 12 ********************
 * Modify innerclasses/Sequence.java so that it throws an
 * appropriate exception if you try to put in too many 
 * elements.
 *********************************************************/
package exceptions;

class SequenceFullException extends RuntimeException {}

class Sequence2 {
  private Object[] objects;
  private int next;
  public Sequence2(int size) { objects = new Object[size]; }
  public void add(Object x) {
    if(next < objects.length)
      objects[next++] = x;
    else
      throw new SequenceFullException();
  }
  private class SequenceSelector implements Selector {
    private int i;
    public boolean end() { return i == objects.length; }
    public Object current() { return objects[i]; }
    public void next() { if(i < objects.length) i++; }
  }
  public Selector selector() {
    return new SequenceSelector();
  }
}

public class E12_SequenceExceptions {
  public static void main(String[] args) {
    Sequence2 sequence = new Sequence2(10);
    for(int i = 0; i < 11; i++)
      sequence.add(Integer.toString(i));
  }
} ///:~
