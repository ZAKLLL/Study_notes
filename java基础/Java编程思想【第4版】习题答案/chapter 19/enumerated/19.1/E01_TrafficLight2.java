//: enumerated/E01_TrafficLight2.java
/****************** Exercise 1 *****************
 * Use a static import to modify TrafficLight.java
 * so you don't have to qualify the enum instances.
 ***********************************************/
package enumerated;
import static net.mindview.util.Print.*;
import static enumerated.Signal.*;

public class E01_TrafficLight2 {
  Signal color = RED;
  public void change() {
    switch(color) {
      case RED:    color = GREEN;
                   break;
      case GREEN:  color = YELLOW;
                   break;
      case YELLOW: color = RED;
    }
  }
  public String toString() {
    return "The traffic light is " + color;
  }
  public static void main(String[] args) {
    E01_TrafficLight2 t = new E01_TrafficLight2();
    for(int i = 0; i < 7; i++) {
      print(t);
      t.change();
    }
  }
} /* Output:
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
*///:~
