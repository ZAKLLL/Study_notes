//: gui/E16_SimplifyList.java
/****************** Exercise 16 *****************
 * Simplify List.java by passing the array to the
 * constructor and eliminating the dynamic addition
 * of elements to the list.
 ***********************************************/
package gui;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import static net.mindview.util.SwingConsole.*;

public class E16_SimplifyList extends JFrame {
  private String[] flavors = {
    "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
    "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
    "Praline Cream", "Mud Pie"
  };
  private JList lst = new JList(flavors);
  private JTextArea t =
    new JTextArea(flavors.length, 20);
  private ListSelectionListener ll =
    new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) return;
        t.setText("");
        for(Object item : lst.getSelectedValues())
          t.append(item + "\n");
      }
    };
  public E16_SimplifyList() {
    t.setEditable(false);
    setLayout(new FlowLayout());
    Border brd = BorderFactory.createMatteBorder(
      1, 1, 2, 2, Color.BLACK);
    lst.setBorder(brd);
    t.setBorder(brd);
    add(t);
    add(lst);
    lst.addListSelectionListener(ll);
  }
  public static void main(String[] args) {
    run(new E16_SimplifyList(), 250, 375);
  }
} ///:~
