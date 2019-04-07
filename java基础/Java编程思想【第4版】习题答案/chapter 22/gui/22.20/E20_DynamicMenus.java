//: gui/E20_DynamicMenus.java
/****************** Exercise 20 ******************
 * Create a program that breaks a text file into
 * words. Distribute those words as labels on menus
 * and submenus.
 ***********************************************/
package gui;
import java.util.*;
import javax.swing.*;
import net.mindview.util.*;
import static net.mindview.util.SwingConsole.*;

class DynamicMenus extends JFrame {
  // You may want to change the code to use a command-line
  // parameter instead of a hard-coded value:
  private Set<String> words = new TreeSet<String>(
    new TextFile("E20_DynamicMenus.java", "\\W+"));
  private JMenuBar mb = new JMenuBar();
  // The top level menu is fixed:
  private JMenu
    // Words starting with an upper case letter:
    tm1 = new JMenu("Words Starting With UCC"),
    // Words starting with a lower case letter:
    tm2 = new JMenu("Words Starting With LCC"),
    // All other words not belonging to the above groups:
    tm3 = new JMenu("Other Words");
  DynamicMenus() {
    distributeWordsOnMenus();
    mb.add(tm1);
    mb.add(tm2);
    mb.add(tm3);
    setJMenuBar(mb);
  }
  private void distributeWordsOnMenus() {
    boolean firstInGroup;
    char currentGroup, lastGroup = (char)0;
    JMenu currentMenu;
    JMenu currentSM = null;   // The current sub-menu
    String word, nextWord = null;
    boolean callNext = true;
    for(Iterator<String> it = words.iterator();
        it.hasNext();) {
      if(callNext)
        word = it.next();
      else {
        word = nextWord;
        callNext = true;
      }
      if(word.matches("[0-9]+"))
        continue;  // Ignore plain numbers.
      currentGroup = word.charAt(0);
      firstInGroup = currentGroup != lastGroup;
      lastGroup = currentGroup;
      // Decide which top level menu to extend:
      if(Character.isLowerCase(currentGroup))
        currentMenu = tm2;
      else if(Character.isUpperCase(currentGroup))
        currentMenu = tm1;
      else
        currentMenu = tm3;
      // Decide whether this item should be put as a menu
      // item beneath the top menu, or under the
      // corresponding sub-menu:
      JMenuItem itemToAdd = new JMenuItem(word);
      if(firstInGroup) {
        // Now we need to see whether we need a new
        // sub-menu or just a plain menu item:
        if(!it.hasNext()) {
          currentMenu.add(itemToAdd);
          break;
        }
        nextWord = it.next();
        callNext = false;
        if(currentGroup != nextWord.charAt(0))
          currentMenu.add(itemToAdd);
        else {
          currentSM =
            new JMenu(Character.toString(currentGroup));
          currentSM.add(itemToAdd);
          currentMenu.add(currentSM);
        }
      } else
        currentSM.add(itemToAdd);
    }
  }
}

public class E20_DynamicMenus {
  public static void main(String[] args) {
    run(new DynamicMenus(), 400, 400);
  }
} ///:~
