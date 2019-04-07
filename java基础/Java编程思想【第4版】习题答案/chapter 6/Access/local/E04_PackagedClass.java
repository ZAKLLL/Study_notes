//: access/local/E04_PackagedClass.java
/****************** Exercise 4 ****************
 * Show that protected methods have package 
 * access but are not public.
 ***********************************************/
package access.local;

public class E04_PackagedClass {
  protected static void greeting() {
    System.out.println("Hello client programmer!");
  }
} ///:~
