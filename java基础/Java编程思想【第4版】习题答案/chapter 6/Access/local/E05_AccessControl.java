//: access/local/E05_AccessControl.java
/****************** Exercise 5 ******************
 * Create a class with public, private,
 * protected, and package-access fields and
 * method members. Create an object of this class
 * and see what kind of compiler messages you get
 * when you try to access all the class members.
 * Remember that classes in the same directory
 * are part of the "default" package.
 ***********************************************/
package access.local;

public class E05_AccessControl {
  public int a;
  private int b;
  protected int c;
  int d; // Package access
  public void f1() {}
  private void f2() {}
  protected void f3() {}
  void f4() {} // Package access
  public static void main(String args[]) {
    E05_AccessControl test = new E05_AccessControl();
    // No problem accessing everything inside
    // of main() for this class, since main()
    // is a member and therefore has access:
    test.a = 1;
    test.b = 2;
    test.c = 3;
    test.d = 4;
    test.f1();
    test.f2();
    test.f3();
    test.f4();
  }
}  ///:~
