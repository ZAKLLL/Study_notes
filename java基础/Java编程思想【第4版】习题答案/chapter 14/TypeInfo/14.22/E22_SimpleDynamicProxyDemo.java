//: typeinfo/E22_SimpleDynamicProxyDemo.java
/****************** Exercise 22 *****************
 * Modify SimpleDynamicProxy.java so that it 
 * measures method-call times.
 ***********************************************/
package typeinfo;
import java.lang.reflect.*;

class DynamicProxyHandler implements InvocationHandler {
  private Object proxied;
  public DynamicProxyHandler(Object proxied) {
    this.proxied = proxied;
  }
  public Object
  invoke(Object proxy, Method method, Object[] args)
  throws Throwable {
    System.out.println("**** proxy: " + proxy.getClass() +
      ", method: " + method + ", args: " + args);
    if(args != null)
      for(Object arg : args)
        System.out.println("  " + arg);
    long start = System.nanoTime();
    Object ret = method.invoke(proxied, args);
    long duration = System.nanoTime() - start;
    System.out.println("METHOD-CALL TIME: " + duration);
    return ret;
  }
}

class E22_SimpleDynamicProxyDemo {
  public static void consumer(Interface iface) {
    iface.doSomething();
    iface.somethingElse("bonobo");
  }
  public static void main(String[] args) {
    RealObject real = new RealObject();
    consumer(real);
    Interface proxy = (Interface)Proxy.newProxyInstance(
      Interface.class.getClassLoader(),
      new Class<?>[]{ Interface.class },
      new DynamicProxyHandler(real));
    consumer(proxy);
  }
} /* Output: (85% match)
doSomething
somethingElse bonobo
**** proxy: class $Proxy0, method: public abstract void Interface.doSomething(), args: null
doSomething
METHOD-CALL TIME: 301155
**** proxy: class $Proxy0, method: public abstract void Interface.somethingElse(java.lang.String), args: [Ljava.lang.Object;@42e816
  bonobo
somethingElse bonobo
METHOD-CALL TIME: 84648
*///:~
