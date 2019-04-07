//: strings/E09_Replacing2.java
/****************** Exercise 9 ******************
 * Using the documentation for java.util.regex.Pattern
 * as a resource, replace all the vowels in
 * Splitting.knights with underscores.
 ************************************************/
package strings;

public class E09_Replacing2 {
  public static void main(String[] args) {
     System.out.println(Splitting.knights.replaceAll(
       "(?i)[aeiou]","_"));
  }
} /* Output:
Th_n, wh_n y__ h_v_ f__nd th_ shr_bb_ry, y__ m_st c_t d_wn th_ m_ght__st tr__ _n th_ f_r_st... w_th... _ h_rr_ng!
*///:~
