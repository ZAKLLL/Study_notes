//: generics/E39_DynamicProxyMixin2.java
/****************** Exercise 39 *****************
 * Add a new mixin class Colored to
 * DynamicProxyMixin.java, mix it in to mixin, and
 * show that it works.
 ************************************************/
package generics;
import static net.mindview.util.Tuple.*;

public class E39_DynamicProxyMixin2 {
  public static void main(String[] args) {
    Object mixin = MixinProxy.newInstance(
      tuple(new BasicImp(), Basic.class),
      tuple(new TimeStampedImp(), TimeStamped.class),
      tuple(new SerialNumberedImp(),SerialNumbered.class),
      tuple(new ColoredImp(), Colored.class));
    Basic b = (Basic)mixin;
    TimeStamped t = (TimeStamped)mixin;
    SerialNumbered s = (SerialNumbered)mixin;
    Colored c = (Colored)mixin;
    b.set("Hello");
    System.out.println(b.get());
    System.out.println(t.getStamp());
    System.out.println(s.getSerialNumber());
    System.out.println(c.getColor());
  }
} /* Output: (75% match)
Hello
1135959445620
1
java.awt.Color[r=186,g=36,b=66]
*///:~
