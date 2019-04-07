//: gui/E09_ShowMethods.java
/****************** Exercise 9 *****************
 * Starting with ShowAddListeners.java, create a
 * program with the full functionality of
 * typeinfo.ShowMethods.java.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.*;
import static net.mindview.util.SwingConsole.*;

class ShowMethodsClean extends JFrame {
  private static Pattern qualifier =
    Pattern.compile("\\w+\\.");
  String[] n;
  JTextField
    name = new JTextField(25),
    searchFor = new JTextField(25);
  JTextArea results = new JTextArea(40, 65);
  JScrollPane scrollPane = new JScrollPane(results);
  class NameL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String nm = name.getText().trim();
      if(nm.length() == 0) {
        results.setText("No match");
        return;
      }
      Class<?> cl;
      try {
        cl = Class.forName(nm);
      } catch(ClassNotFoundException ex) {
        results.setText("No match");
        return;
      }
      Method[] m = cl.getMethods();
      Constructor<?>[] ctor = cl.getConstructors();
      // Convert to an array of cleaned Strings:
      n = new String[m.length + ctor.length];
      for(int i = 0; i < m.length; i++)
        n[i] = m[i].toString();
      for(int i = 0; i < ctor.length; i++)
        n[i + m.length] = ctor[i].toString();
      reDisplay();
    }
  }
  void reDisplay() {
    results.setText("");
    if(searchFor.getText().trim().length() == 0)
      // Include everything:
      for(String s : n)
        results.append(s + "\n");
//      OR:
//      results.append(
//        qualifier.matcher(s).replaceAll("") + "\n");
    else {
      // Include only methods that have ALL the
      // words listed in searchFor:
      java.util.List<String> lookFor = Arrays.asList(
        searchFor.getText().split("\\s+"));
      for(String s : n) {
        Iterator<String> it = lookFor.iterator();
        boolean include = true;
        while(it.hasNext())
          if(s.indexOf(it.next()) == -1)
            include = false;
        if(include == true)
          results.append(s + "\n");
//        OR:
//        results.append(
//          qualifier.matcher(s).replaceAll("") + "\n");
      }
    }
    // Force the scrollpane back to the top:
    scrollPane.getViewport().setViewPosition(
      new Point(0, 0));
  }
  public ShowMethodsClean() {
    name.addActionListener(new NameL());
    // There is no need to parse the class file again when
    // only the search conditions have changed:
    searchFor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reDisplay();
      }
    });
    JPanel top1 = new JPanel();
    top1.add(new JLabel(
      "Qualified.class.name (press ENTER):"));
    top1.add(name);
    JPanel top2 = new JPanel();
    top2.add(new JLabel(
      "Words to search for (optional):"));
    top2.add(searchFor);
    JPanel top = new JPanel(new GridLayout(2,1));
    top.add(top1);
    top.add(top2);
    add(BorderLayout.NORTH, top);
    add(scrollPane);
  }
}

public class E09_ShowMethods {
  public static void main(String args[]) {
    run(new ShowMethodsClean(), 600, 400);
  }
} ///:~
