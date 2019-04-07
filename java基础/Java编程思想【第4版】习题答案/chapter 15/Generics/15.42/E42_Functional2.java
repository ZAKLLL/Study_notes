//: generics/E42_Functional2.java
/****************** Exercise 42 *****************
 * Create two separate classes, with nothing in
 * common. Each class should hold a value, and at
 * least have methods that produce that value and
 * perform a modification upon that value. Modify
 * Functional.java so that it performs functional
 * operations on collections of your classes (these
 * operations do not have to be arithmetic as they
 * are in Functional.java).
 ************************************************/
package generics;
import java.util.*;
import static net.mindview.util.Print.*;

final class DataManipulator1
implements Comparable<DataManipulator1> {
  private int value;
  public DataManipulator1(int value) { this.value = value; }
  public void increment() { ++value; }
  public int getValue() { return value; }
  public int compareTo(DataManipulator1 other) {
    return Integer.valueOf(value).compareTo(other.value);
  }
  public String toString() {
    return Integer.toString(value);
  }
}

final class DataManipulator2 {
  private String value;
  public DataManipulator2(String value) { setValue(value); }
  public void setValue(String value) { this.value = value; }
  public String getValue() { return value; }
}

public class E42_Functional2 {
  // To use the above generic methods, we need to create
  // function objects to adapt to our particular needs:
  static class Incrementor implements
  UnaryFunction<DataManipulator1,DataManipulator1> {
    public DataManipulator1 function(DataManipulator1 x) {
      x.increment();
      return x;
    }
  }
  static class UpperCaseConverter
  implements UnaryFunction<String,DataManipulator2> {
    public String function(DataManipulator2 x) {
      return x.getValue().toUpperCase();
    }
  }
  static class Concatenator
  implements Combiner<DataManipulator2> {
    public DataManipulator2
    combine(DataManipulator2 x,DataManipulator2 y) {
      x.setValue(x.getValue() + y.getValue());
      return x;
    }
  }
  public static void main(String[] args) {
    DataManipulator1 rf = new DataManipulator1(4);
    List<DataManipulator1> ldm1 = Arrays.asList(
      new DataManipulator1(3), new DataManipulator1(10),
      new DataManipulator1(1),new DataManipulator1(7));
    print(
      Functional.transform(
        Functional.filter(ldm1,
          new Functional.GreaterThan<DataManipulator1>(rf)),
        new Incrementor()));

    List<DataManipulator2> ldm2 = Arrays.asList(
      new DataManipulator2("a"), new DataManipulator2("B"),
      new DataManipulator2("c"),new DataManipulator2("d"));
    print(
      Functional.transform(ldm2, new UpperCaseConverter()));
    print(Functional.reduce(ldm2,
      new Concatenator()).getValue());
  }
} /* Output:
[11, 8]
[A, B, C, D]
aBcd
*///:~
