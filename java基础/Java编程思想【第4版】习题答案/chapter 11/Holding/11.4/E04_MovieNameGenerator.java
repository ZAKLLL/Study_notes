//: holding/E04_MovieNameGenerator.java
/****************** Exercise 4 ******************
 * Create a generator class that produces String objects 
 * with the names of characters from your favorite
 * movie each time you call next(), and then loops
 * around to the beginning of the character list
 * when it runs out of names. Use this generator to
 * fill an array, an ArrayList, a LinkedList, a
 * HashSet, a LinkedHashSet, and a TreeSet, then
 * print each container.
 ***********************************************/ 
package holding;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

class MovieNameGenerator implements Generator<String> {
  String[] characters = {
    "Grumpy", "Happy", "Sleepy", "Dopey", "Doc", "Sneezy",
    "Bashful", "Snow White", "Witch Queen", "Prince"
  };
  int next;
  public String next() {
    String r = characters[next];
    next = (next + 1) % characters.length;
    return r;
  }
}

public class E04_MovieNameGenerator {
  private static final MovieNameGenerator mng =
    new MovieNameGenerator();
  static String[] fill(String[] array) {
    for(int i = 0; i < array.length; i++)
      array[i] = mng.next();
    return array;
  }
  static Collection<String> 
  fill(Collection<String> collection) {
    for(int i = 0; i < 5; i++)
      collection.add(mng.next());
    return collection;
  }
  public static void main(String[] args) {
    print(Arrays.toString(fill(new String[5])));
    print(fill(new ArrayList<String>()));
    print(fill(new LinkedList<String>()));
    print(fill(new HashSet<String>()));
    print(fill(new LinkedHashSet<String>()));
    print(fill(new TreeSet<String>()));
  }
} /* Output:
[Grumpy, Happy, Sleepy, Dopey, Doc]
[Sneezy, Bashful, Snow White, Witch Queen, Prince]
[Grumpy, Happy, Sleepy, Dopey, Doc]
[Snow White, Witch Queen, Sneezy, Bashful, Prince]
[Grumpy, Happy, Sleepy, Dopey, Doc]
[Bashful, Prince, Sneezy, Snow White, Witch Queen]
*///:~
