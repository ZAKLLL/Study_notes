//: control/E08_SwitchDemo2.java
// E08_SwitchDemo.java with the breaks removed.
package control;

public class E08_SwitchDemo2 {
  public static void main(String[] args) {
    for(int i = 0; i < 7; i++)
      switch(i) {
        case 1: System.out.println("case 1");
        case 2: System.out.println("case 2");
        case 3: System.out.println("case 3");
        case 4: System.out.println("case 4");
        case 5: System.out.println("case 5");
        default: System.out.println("default");
      }
  }
} /* Output:
default
case 1
case 2
case 3
case 4
case 5
default
case 2
case 3
case 4
case 5
default
case 3
case 4
case 5
default
case 4
case 5
default
case 5
default
default
*///:~
