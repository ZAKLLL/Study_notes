//: holding/E24_MapOrder2.java
/******************* Exercise 24 **************************
 * Fill a LinkedHashMap with String keys and objects.
 * Extract the pairs, sort them based on the keys, and 
 * re-insert them into the Map.
 *********************************************************/ 
package holding;
import java.util.*;
import net.mindview.util.*;

public class E24_MapOrder2 {
  public static void main(String[] args) {
    Map<String,String> m1 =
      new LinkedHashMap<String,String>(
        Countries.capitals(25));
    System.out.println(m1);
    String[] keys = m1.keySet().toArray(new String[0]);
    Arrays.sort(keys);
    Map<String,String> m2 =
      new LinkedHashMap<String,String>();
    for(String key : keys)
      m2.put(key, m1.get(key));
    System.out.println(m2);
  }
} /* Output:
{ALGERIA=Algiers, ANGOLA=Luanda, BENIN=Porto-Novo, BOTSWANA=Gaberone, BURKINA FASO=Ouagadougou, BURUNDI=Bujumbura, CAMEROON=Yaounde, CAPE VERDE=Praia, CENTRAL AFRICAN REPUBLIC=Bangui, CHAD=N'djamena, COMOROS=Moroni, CONGO=Brazzaville, DJIBOUTI=Dijibouti, EGYPT=Cairo, EQUATORIAL GUINEA=Malabo, ERITREA=Asmara, ETHIOPIA=Addis Ababa, GABON=Libreville, THE GAMBIA=Banjul, GHANA=Accra, GUINEA=Conakry, BISSAU=Bissau, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, KENYA=Nairobi, LESOTHO=Maseru}
{ALGERIA=Algiers, ANGOLA=Luanda, BENIN=Porto-Novo, BISSAU=Bissau, BOTSWANA=Gaberone, BURKINA FASO=Ouagadougou, BURUNDI=Bujumbura, CAMEROON=Yaounde, CAPE VERDE=Praia, CENTRAL AFRICAN REPUBLIC=Bangui, CHAD=N'djamena, COMOROS=Moroni, CONGO=Brazzaville, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, DJIBOUTI=Dijibouti, EGYPT=Cairo, EQUATORIAL GUINEA=Malabo, ERITREA=Asmara, ETHIOPIA=Addis Ababa, GABON=Libreville, GHANA=Accra, GUINEA=Conakry, KENYA=Nairobi, LESOTHO=Maseru, THE GAMBIA=Banjul}
*///:~
