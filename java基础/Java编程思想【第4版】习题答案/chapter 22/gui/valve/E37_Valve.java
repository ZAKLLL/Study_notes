//: gui/valve/E37_Valve.java
/****************** Exercise 37 *****************
 * Create your own JavaBean called Valve that
 * contains two properties: a boolean called "on"
 * and an int called "level." Create a manifest file,
 * use jar to package your Bean, then load it into
 * the Bean Builder or into a Beans-enabled
 * program builder tool so that you can test it.
***********************************************/
// To test the program, run the following command
// in directory gui:
// java gui.valve.E37_Valve
// To make the jar file, run the following command
// in the directory above valve:
// jar cfm E37_Valve.jar E37_Valve.mf valve
package gui.valve;

public class E37_Valve implements java.io.Serializable {
  private boolean on;
  private int level;
  public E37_Valve() {}
  public E37_Valve(boolean on, int level) {
    this.on = on;
    this.level = level;
  }
  public boolean isOn() { return on; }
  public void setOn(boolean on) { this.on = on; }
  public int getLevel() { return level; }
  public void setLevel(int level) { this.level = level; }
  public static void main(String args[]) {
    E37_Valve v = new E37_Valve(true, 100);
    System.out.println("v.isOn() = " + v.isOn());
    System.out.println("v.getLevel() = " + v.getLevel());
  }
} ///:~
