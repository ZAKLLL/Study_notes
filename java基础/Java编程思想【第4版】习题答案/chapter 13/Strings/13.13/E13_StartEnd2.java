//: strings/E13_StartEnd2.java
/************************** Exercise 13 ******************
 * Modify StartEnd.java so that it uses Groups.POEM as
 * input, but still produces positive outputs for find(),
 * lookingAt() and matches().
 *********************************************************/
package strings;
import java.util.regex.*;
import static net.mindview.util.Print.*;

public class E13_StartEnd2 {
  private static class Display {
    private boolean regexPrinted = false;
    private String regex;
    Display(String regex) { this.regex = regex; }
    void display(String message) {
      if(!regexPrinted) {
        print(regex);
        regexPrinted = true;
      }
      print(message);
    }
  }
  static void examine(String s, String regex) {
    Display d = new Display(regex);
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(s);
    while(m.find())
      d.display("find() '" + m.group() +
        "' start = "+ m.start() + " end = " + m.end());
    if(m.lookingAt()) // No reset() necessary
      d.display("lookingAt() start = "
        + m.start() + " end = " + m.end());
    if(m.matches()) // No reset() necessary
      d.display("matches() start = "
        + m.start() + " end = " + m.end());
  }
  public static void main(String[] args) {
    for(String in : Groups.POEM.split("\n")) {
      print("input : " + in);
      for(String regex : new String[] {"\\w*ere\\w*",
        "\\w*at", "t\\w+", "T.*?."})
        examine(in, regex);
    }
  }
} /* Output:
input : Twas brillig, and the slithy toves
t\w+
find() 'the' start = 18 end = 21
find() 'thy' start = 25 end = 28
find() 'toves' start = 29 end = 34
T.*?.
find() 'Tw' start = 0 end = 2
lookingAt() start = 0 end = 2
matches() start = 0 end = 34
input : Did gyre and gimble in the wabe.
t\w+
find() 'the' start = 23 end = 26
input : All mimsy were the borogoves,
\w*ere\w*
find() 'were' start = 10 end = 14
t\w+
find() 'the' start = 15 end = 18
input : And the mome raths outgrabe.
\w*at
find() 'rat' start = 13 end = 16
t\w+
find() 'the' start = 4 end = 7
find() 'ths' start = 15 end = 18
find() 'tgrabe' start = 21 end = 27
input :
input : Beware the Jabberwock, my son,
t\w+
find() 'the' start = 7 end = 10
input : The jaws that bite, the claws that catch.
\w*at
find() 'that' start = 9 end = 13
find() 'that' start = 30 end = 34
find() 'cat' start = 35 end = 38
t\w+
find() 'that' start = 9 end = 13
find() 'te' start = 16 end = 18
find() 'the' start = 20 end = 23
find() 'that' start = 30 end = 34
find() 'tch' start = 37 end = 40
T.*?.
find() 'Th' start = 0 end = 2
lookingAt() start = 0 end = 2
matches() start = 0 end = 41
input : Beware the Jubjub bird, and shun
t\w+
find() 'the' start = 7 end = 10
input : The frumious Bandersnatch.
\w*at
find() 'Bandersnat' start = 13 end = 23
t\w+
find() 'tch' start = 22 end = 25
T.*?.
find() 'Th' start = 0 end = 2
lookingAt() start = 0 end = 2
matches() start = 0 end = 26
*///:~
