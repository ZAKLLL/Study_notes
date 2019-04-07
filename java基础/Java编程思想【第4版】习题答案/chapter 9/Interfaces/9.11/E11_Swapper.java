//: interfaces/E11_Swapper.java
/****************** Exercise 11 ******************
 * Create a class with a method that takes a String
 * argument and produces a result that swaps each
 * pair of characters in that argument. Adapt the
 * class to work with
 * interfaceprocessor.Apply.process().
 ***********************************************/
package interfaces;
import interfaces.interfaceprocessor.*;

class CharacterPairSwapper {
  static String swap(String s) {
    StringBuilder sb = new StringBuilder(s);
    for(int i = 0; i < sb.length() - 1; i += 2) {
      char c1 = sb.charAt(i);
      char c2 = sb.charAt(i + 1);
      sb.setCharAt(i, c2);
      sb.setCharAt(i + 1, c1);
    }
    return sb.toString();
  }
}

class SwapperAdapter implements Processor {
  public String name() {
    return CharacterPairSwapper.class.getSimpleName();
  }
  public String process(Object input) {
    return CharacterPairSwapper.swap((String)input);
  }
}

public class E11_Swapper {
  public static void main(String[] args) {
    Apply.process(new SwapperAdapter(), "1234");
    Apply.process(new SwapperAdapter(), "abcde");
  }
} /* Output:
Using Processor CharacterPairSwapper
2143
Using Processor CharacterPairSwapper
badce
*///:~
