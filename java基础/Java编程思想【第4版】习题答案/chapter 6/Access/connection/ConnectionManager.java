//: access/connection/ConnectionManager.java
package access.connection;

public class ConnectionManager {
  private static Connection[] pool = new Connection[10];
  private static int counter = 0;
  static {
    for(int i = 0; i < pool.length; i++)
      pool[i] = new Connection();
  }
  // Very simple -- just hands out each one once:
  public static Connection getConnection() {
    if(counter < pool.length)
      return pool[counter++];
    return null;
  }
} ///:~
