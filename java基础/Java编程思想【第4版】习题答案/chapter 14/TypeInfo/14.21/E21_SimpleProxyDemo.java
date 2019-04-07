//: typeinfo/E21_SimpleProxyDemo.java
/****************** Exercise 21 *****************
 * Modify SimpleProxyDemo.java so it measures
 * method-call times.
 ***********************************************/
package typeinfo;
import static net.mindview.util.Print.*;

interface Interface {
  void doSomething();
  void somethingElse(String arg);
}

class RealObject implements Interface {
  public void doSomething() { print("doSomething"); }
  public void somethingElse(String arg) {
    print("somethingElse " + arg);
  }
}

class SimpleProxy implements Interface {
  private Interface proxied;
  public SimpleProxy(Interface proxied) {
    this.proxied = proxied;
  }
  public void doSomething() {
    print("SimpleProxy doSomething");
    long start = System.nanoTime();
    proxied.doSomething();
    long duration = System.nanoTime() - start;
    print("METHOD-CALL TIME: " + duration);
  }
  public void somethingElse(String arg){
    print("SimpleProxy somethingElse " + arg);
    long start = System.nanoTime();
    proxied.somethingElse(arg);
    long duration = System.nanoTime() - start;
    print("METHOD-CALL TIME: " + duration);
  }
}

public class E21_SimpleProxyDemo {
  public static void consumer(Interface iface) {
    iface.doSomething();
    iface.somethingElse("bonobo");
  }
  public static void main(String[] args) {
    consumer(new RealObject());
    consumer(new SimpleProxy(new RealObject()));
  }
} /* Output: (90% match)
doSomething
somethingElse bonobo
SimpleProxy doSomething
doSomething
METHOD-CALL TIME: 52800
SimpleProxy somethingElse bonobo
somethingElse bonobo
METHOD-CALL TIME: 56711
*///:~
