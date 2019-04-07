//: annotations/Divisor.java
package annotations;

@ExtractInterface("IDivisor")
class Divisor {
  public int divide(int x, int y) {
    int total = 0;
    while(x >= y) {
      x = sub(x, y);
      total++;
    }
    return total;
  }
  private int sub(int x, int y) { return x - y; }
  public static void main(String[] args) {
    System.out.println(
      "2678/134 = " + new Divisor().divide(2678, 134));
  }
} /* Output:
2678/134 = 19
*///:~
