//: holding/E11_IterToString.java
/****************** Exercise 11 *****************
 * Write a method that uses an Iterator to step
 * through a Collection and print the toString()
 * of each object in the container. Fill all the
 * different types of Collections with objects and
 * apply your method to each container.
 ***********************************************/ 
package holding;
import java.util.*;

public class E11_IterToString {
  public static void printToStrings(Iterator<?> it) {
    while(it.hasNext())
      System.out.println(it.next().toString());
  }
  @SuppressWarnings("unchecked")
  public static void main(String args[]) {
    List<Collection<String>> ca =
      Arrays.<Collection<String>>asList(
        new ArrayList<String>(),
        new LinkedList<String>(),
        new HashSet<String>(),
        new TreeSet<String>());
    for(Collection<String> c : ca)
      E04_MovieNameGenerator.fill(c);
    for(Collection<String> c : ca)
      printToStrings(c.iterator());
  }
} /* Output:
Grumpy
Happy
Sleepy
Dopey
Doc
Sneezy
Bashful
Snow White
Witch Queen
Prince
Happy
Doc
Sleepy
Grumpy
Dopey
Bashful
Prince
Sneezy
Snow White
Witch Queen
*///:~
