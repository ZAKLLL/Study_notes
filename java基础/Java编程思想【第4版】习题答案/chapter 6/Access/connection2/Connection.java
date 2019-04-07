//: access/connection2/Connection.java
package access.connection2;

public class Connection {
  private static int counter = 0;
  private int id = counter++;
  Connection() {}
  public String toString() {
    return "Connection " + id;
  }
  public void doSomething() {}
  public void checkIn() {
    ConnectionManager.checkIn(this);
  }
} ///:~
