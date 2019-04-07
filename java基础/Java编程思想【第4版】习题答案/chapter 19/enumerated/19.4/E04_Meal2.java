//: enumerated/E04_Meal2.java
/****************** Exercise 4 *****************
 * Repeat the above exercise for Meal2.java.
 ***********************************************/
package enumerated;
import net.mindview.util.*;

enum Meal2 {
  APPETIZER(Food.Appetizer.class),
  MAINCOURSE(Food.MainCourse.class),
  BEVERAGE(Food.Beverage.class),
  DESSERT(Food.Dessert.class),
  COFFEE(Food.Coffee.class);
  private Food[] values;
  private Meal2(Class<? extends Food> kind) {
    values = kind.getEnumConstants();
  }
  public interface Food {
    enum Appetizer implements Food {
      SALAD, SOUP, SPRING_ROLLS;
    }
    enum MainCourse implements Food {
      LASAGNE, BURRITO, PAD_THAI,
      LENTILS, HUMMOUS, VINDALOO;
    }
    enum Beverage implements Food {
      BEER, VINE, JUICE, COLA, WATER;
    }
    enum Dessert implements Food {
      TIRAMISU, GELATO, BLACK_FOREST_CAKE,
      FRUIT, CREME_CARAMEL;
    }
    enum Coffee implements Food {
      BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
      LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
  }
  public Food randomSelection() {
    return Enums.random(values);
  }
}

public class E04_Meal2 {
  public static void main(String[] args) {
    for(int i = 0; i < 5; i++) {
      for(Meal2 meal : Meal2.values()) {
        Meal2.Food food = meal.randomSelection();
        System.out.println(food);
      }
      System.out.println("---");
    }
  }
} /* Output:
SPRING_ROLLS
VINDALOO
COLA
GELATO
TEA
---
SPRING_ROLLS
HUMMOUS
BEER
BLACK_FOREST_CAKE
BLACK_COFFEE
---
SALAD
LASAGNE
VINE
CREME_CARAMEL
LATTE
---
SOUP
HUMMOUS
VINE
TIRAMISU
ESPRESSO
---
SOUP
LASAGNE
VINE
BLACK_FOREST_CAKE
BLACK_COFFEE
---
*///:~
