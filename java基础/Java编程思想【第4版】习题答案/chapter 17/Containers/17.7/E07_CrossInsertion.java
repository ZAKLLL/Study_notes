//: containers/E07_CrossInsertion.java
/****************** Exercise 7 *****************
 * Create both an ArrayList and a LinkedList, and
 * fill each using the Countries.names() generator.
 * Print each list using an ordinary Iterator, then
 * insert one list into the other by using a
 * ListIterator, inserting at every other location.
 * Now perform the insertion starting at the end of
 * the first list and moving backward.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E07_CrossInsertion {
  public static void main(String args[]) {
    ArrayList<String> al =
      new ArrayList<String>(Countries.names(10));
    LinkedList<String> ll =
      new LinkedList<String>(
        Countries.names(20).subList(10, 20));
    for(Iterator<String> it = al.iterator(); it.hasNext();)
      print(it.next());
    print("********");
    for(Iterator<String> it = ll.iterator(); it.hasNext();)
      print(it.next());
    print("********");
    int alindex = 0;
    for(ListIterator<String> lit2 = ll.listIterator();
      lit2.hasNext();) {
      al.add(alindex, lit2.next());
      alindex += 2;
    }
    print("al = " + al);
    print("********");
    alindex = 0;
    // Start at the end:
    for(ListIterator<String>
      lit2 = ll.listIterator(ll.size());
      lit2.hasPrevious();) {
      al.add(alindex, lit2.previous());
      alindex += 2;
    }
    print("al = " + al);
  }
} /* Output:
ALGERIA
ANGOLA
BENIN
BOTSWANA
BURKINA FASO
BURUNDI
CAMEROON
CAPE VERDE
CENTRAL AFRICAN REPUBLIC
CHAD
********
COMOROS
CONGO
DJIBOUTI
EGYPT
EQUATORIAL GUINEA
ERITREA
ETHIOPIA
GABON
THE GAMBIA
GHANA
********
al = [COMOROS, ALGERIA, CONGO, ANGOLA, DJIBOUTI, BENIN, EGYPT, BOTSWANA, EQUATORIAL GUINEA, BURKINA FASO, ERITREA, BURUNDI, ETHIOPIA, CAMEROON, GABON, CAPE VERDE, THE GAMBIA, CENTRAL AFRICAN REPUBLIC, GHANA, CHAD]
********
al = [GHANA, COMOROS, THE GAMBIA, ALGERIA, GABON, CONGO, ETHIOPIA, ANGOLA, ERITREA, DJIBOUTI, EQUATORIAL GUINEA, BENIN, EGYPT, EGYPT, DJIBOUTI, BOTSWANA, CONGO, EQUATORIAL GUINEA, COMOROS, BURKINA FASO, ERITREA, BURUNDI, ETHIOPIA, CAMEROON, GABON, CAPE VERDE, THE GAMBIA, CENTRAL AFRICAN REPUBLIC, GHANA, CHAD]
*///:~
