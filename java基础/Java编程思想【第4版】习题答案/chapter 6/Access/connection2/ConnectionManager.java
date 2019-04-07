//: access/connection2/ConnectionManager.java
package access.connection2;

public class ConnectionManager {
  private static Connection[] pool = new Connection[10];
  static {
    for(int i = 0; i < pool.length; i++)
      pool[i] = new Connection();
  }
  // Produce the first available connection:
  public static Connection getConnection() {
    for(int i = 0; i < pool.length; i++)
      if(pool[i] != null) {
        Connection c = pool[i];
        pool[i] = null; // Indicates "in use"
        return c;
      }
    return null; // None left
  }
  public static void checkIn(Connection c) {
    for(int i = 0; i < pool.length; i++)
      if(pool[i] == null) {
        pool[i] = c; // Check it back in
        return;
      }
  }
} ///:~
