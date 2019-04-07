//: strings/E05_ComplexConversion.java
/****************** Exercise 5 ******************
 * For each of the basic conversion types in the
 * above table, write the most complex formatting
 * expression possible. That is, use all the
 * possible format specifiers available for that
 * conversion type.
 ************************************************/
package strings;
import java.math.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class E05_ComplexConversion {
  public static void main(String[] args) {
    Formatter f = new Formatter(System.out, Locale.US);
    char u = 'a';
    print("u = 'a'");
    f.format("s: %1$-#10s\n", u);
    f.format("c: %1$-10c\n", u);
    f.format("b: %1$-10.10b\n", u);
    f.format("h: %1$-10.10h\n", u);
    int v = 1000;
    print("v = 1000");
    f.format("d 1: %1$(,0+10d\n", v);
    f.format("d 2: %1$-(, 10d\n", v);
    f.format("d 3, v = -v: %1$-(, 10d\n", -v);
    f.format("c, v = 121: %1$-10c\n", 121);
    f.format("b: %1$-10.10b\n", v);
    f.format("s: %1$#-10.10s\n", v);
    f.format("x: %1$-#10x\n", v);
    f.format("h: %1$-10.10h\n", v);
    BigInteger w = new BigInteger("50000000000000");
    print("w = new BigInteger(\"50000000000000\")");
    f.format("d 1: %1$(,0+10d\n", w);
    f.format("d 2: %1$-(, 10d\n", w);
    f.format("d 3, w = -w: %1$-(, 10d\n", w.negate());
    f.format("b: %1$-10.10b\n", w);
    f.format("s: %1$#-10.10s\n", w);
    f.format("x 1: %1$(0+10x\n", w);
    f.format("x 2: %1$-( 10x\n", w);
    f.format("x 3, w = -w: %1$-( 10x\n", w.negate());
    f.format("h: %1$-10.10h\n", w);
    double x = 179.543;
    print("x = 179.543");
    f.format("b: %1$-10.10b\n", x);
    f.format("s: %1$#-10.10s\n", x);
    f.format("f 1: %1$#(,0+10.2f\n", x);
    f.format("f 2: %1$#(,- 10.2f\n", x);
    f.format("f 3, x = -x: %1$#(,0+10.2f\n", -x);
    f.format("e 1: %1$#(0+10.2e\n", x);
    f.format("e 2: %1$#(- 10.2e\n", x);
    f.format("e 3, x = -x: %1$#(0+10.2e\n", -x);
    f.format("h: %1$-10.10h\n", x);
    Object y = new Object();
    print("y = new Object()");
    f.format("b: %1$-10.10b\n", y);
    f.format("s: %1$#-10.10s\n", y);
    f.format("h: %1$-10.10h\n", y);
    boolean z = false;
    print("z = false");
    f.format("b: %1$-10.10b\n", z);
    f.format("s: %1$#-10.10s\n", z);
    f.format("h: %1$-10.10h\n", z);
    // A special no argument conversion type
    f.format("%%: %-10%");
  }
} /* Output: (95% match)
u = 'a'
s: a
c: a
b: true
h: 61
v = 1000
d 1: +00001,000
d 2:  1,000
d 3, v = -v: (1,000)
c, v = 121: y
b: true
s: 1000
x: 0x3e8
h: 3e8
w = new BigInteger("50000000000000")
d 1: +50,000,000,000,000
d 2:  50,000,000,000,000
d 3, w = -w: (50,000,000,000,000)
b: true
s: 5000000000
x 1: +2d79883d2000
x 2:  2d79883d2000
x 3, w = -w: (2d79883d2000)
h: 8842a1a7
x = 179.543
b: true
s: 179.543
f 1: +000179.54
f 2:  179.54
f 3, x = -x: (00179.54)
e 1: +01.80e+02
e 2:  1.80e+02
e 3, x = -x: (1.80e+02)
h: 1ef462c
y = new Object()
b: true
s: java.lang.
h: 3e25a5
z = false
b: false
s: false
h: 4d5
%: %
*///:~
