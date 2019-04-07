//: arrays/E17_BigDecimalGenerator.java
/****************** Exercise 17 *****************
 * Create and test a Generator for BigDecimal, and
 * ensure that it works with the Generated methods.
 ************************************************/
package arrays;
import java.math.*;
import java.util.*;
import net.mindview.util.*;

class BigDecimalGenerator implements Generator<BigDecimal> {
  private BigDecimal value = new BigDecimal(0);
  private BigDecimal step;
  BigDecimalGenerator(BigDecimal step) { this.step = step; }
  public BigDecimal next() {
    BigDecimal oldValue = value;
    value = value.add(step);
    return oldValue;
  }
}

public class E17_BigDecimalGenerator {
  public static void main(String[] args) {
    BigDecimal[] a = { new BigDecimal(9), new BigDecimal(8),
      new BigDecimal(7), new BigDecimal(6) };
    System.out.println(Arrays.toString(a));
    a = Generated.array(a, new BigDecimalGenerator(
      new BigDecimal("0.1")));
    System.out.println(Arrays.toString(a));
    BigDecimal[] b = Generated.array(BigDecimal.class,
      new BigDecimalGenerator(new BigDecimal("0.2")), 15);
    System.out.println(Arrays.toString(b));
  }
} /* Output:
[9, 8, 7, 6]
[0, 0.1, 0.2, 0.3]
[0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.8]
*///:~
