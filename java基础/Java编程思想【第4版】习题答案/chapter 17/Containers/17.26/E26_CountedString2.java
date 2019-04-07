//: containers/E26_CountedString2.java
/****************** Exercise 26 *****************
 * Add a char field to CountedString that is also
 * initialized in the constructor, and modify the
 * hashCode() and equals() methods to include
 * the value of this char.
 ***********************************************/
package containers;
import java.util.*;
import static net.mindview.util.Print.*;

class CountedString2 {
  private static List<String> created =
    new ArrayList<String>();
  private String s;
  private char c;
  private int id;
  public CountedString2(String str, char ci) {
    s = str;
    c = ci;
    created.add(s);
    // id is the total number of instances
    // of this string in use by CountedString2:
    for(String s2 : created)
      if(s2.equals(s))
        id++;
  }
  public String toString() {
    return "String: " + s + " id: " + id + " char: " + c +
      " hashCode(): " + hashCode();
  }
  public int hashCode() {
    // The very simple approach:
    // return s.hashCode() * id;
    // Using Joshua Bloch's recipe:
    int result = 17;
    result = 37 * result + s.hashCode();
    result = 37 * result + id;
    result = 37 * result + c;
    return result;
  }
  public boolean equals(Object o) {
    return o instanceof CountedString2 &&
      s.equals(((CountedString2)o).s) &&
      id == ((CountedString2)o).id &&
      c == ((CountedString2)o).c;
  }
}

public class E26_CountedString2 {
  public static void main(String[] args) {
    Map<CountedString2,Integer> map =
      new HashMap<CountedString2,Integer>();
    CountedString2[] cs = new CountedString2[5];
    for(int i = 0; i < cs.length; i++) {
      cs[i] = new CountedString2("hi", 'c');
      map.put(cs[i], i); // Autobox int -> Integer
    }
    print(map);
    for(CountedString2 cstring : cs) {
      print("Looking up " + cstring);
      print(map.get(cstring));
    }
  }
} /* Output:
{String: hi id: 2 char: c hashCode(): 5418675=1, String: hi id: 4 char: c hashCode(): 5418749=3, String: hi id: 5 char: c hashCode(): 5418786=4, String: hi id: 1 char: c hashCode(): 5418638=0, String: hi id: 3 char: c hashCode(): 5418712=2}
Looking up String: hi id: 1 char: c hashCode(): 5418638
0
Looking up String: hi id: 2 char: c hashCode(): 5418675
1
Looking up String: hi id: 3 char: c hashCode(): 5418712
2
Looking up String: hi id: 4 char: c hashCode(): 5418749
3
Looking up String: hi id: 5 char: c hashCode(): 5418786
4
*///:~
