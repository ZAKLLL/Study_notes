//: strings/E02_RepairInfinite.java
/****************** Exercise 2 ******************
 * Repair InfiniteRecursion.java.
 ************************************************/
package strings;
import java.util.*;

public class E02_RepairInfinite {
  public String toString() {
    return " E02_RepairInfinite address: "
      + super.toString() + "\n";
  }
  public static void main(String[] args) {
    List<E02_RepairInfinite> v =
      new ArrayList<E02_RepairInfinite>();
    for(int i = 0; i < 10; i++)
      v.add(new E02_RepairInfinite());
    System.out.println(v);
  }
} /* Output: (70% match)
[ E02_RepairInfinite address: E02_RepairInfinite@3e25a5
,  E02_RepairInfinite address: E02_RepairInfinite@19821f
,  E02_RepairInfinite address: E02_RepairInfinite@addbf1
,  E02_RepairInfinite address: E02_RepairInfinite@42e816
,  E02_RepairInfinite address: E02_RepairInfinite@9304b1
,  E02_RepairInfinite address: E02_RepairInfinite@190d11
,  E02_RepairInfinite address: E02_RepairInfinite@a90653
,  E02_RepairInfinite address: E02_RepairInfinite@de6ced
,  E02_RepairInfinite address: E02_RepairInfinite@c17164
,  E02_RepairInfinite address: E02_RepairInfinite@1fb8ee3
]
*///:~
