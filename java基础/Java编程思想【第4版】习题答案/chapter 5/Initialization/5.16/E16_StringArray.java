//: initialization/E16_StringArray.java
/****************** Exercise 16 *****************
 * Assign a string to each element of an array of
 * String objects. Print the array using a for loop.
 ************************************************/
package initialization;

public class E16_StringArray {
  public static void main(String args[]) {
    // Doing it the hard way:
    String sa1[] = new String[4];
    sa1[0] = "These";
    sa1[1] = "are";
    sa1[2] = "some";
    sa1[3] = "strings";
    for(int i = 0; i < sa1.length; i++)
      System.out.println(sa1[i]);
    // Using aggregate initialization to
    // make it easier:
    String sa2[] = {
      "These", "are", "some", "strings"
    };
    for(int i = 0; i < sa2.length; i++)
      System.out.println(sa2[i]);
  }
} /* Output:
These
are
some
strings
These
are
some
strings
*///:~
