//: gui/E06_TestRegularExpression2.java
/******************** Exercise 6 ************************
 * Turn strings/TestRegularExpression.java into an
 * interactive Swing program that allows you to put an
 * input string in one JTextArea and a regular expression in
 * a JTextField. The results should be displayed in a second
 * JTextArea.
 *********************************************************/
package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;
import static net.mindview.util.SwingConsole.*;

public class E06_TestRegularExpression2 extends JFrame {
  private JTextArea input = new JTextArea(3, 60),
                   output = new JTextArea(3, 60);
  private JTextField expression = new JTextField(40);
  private JButton match = new JButton("Match");
  private JLabel inputL = new JLabel("Input"),
                 outputL = new JLabel("Output"),
                 expressionL = new JLabel("Expression");
  private JPanel panel1 = new JPanel(),
                 panel2 = new JPanel(),
                 panel3 = new JPanel();
  public E06_TestRegularExpression2() {
    setLayout(new GridLayout(3,1));
    panel1.add(inputL);
    panel1.add(new JScrollPane(input));
    add(panel1);
    panel2.add(expressionL);
    panel2.add(expression);
    panel2.add(match);
    add(panel2);
    panel3.add(outputL);
    panel3.add(new JScrollPane(output));
    add(panel3);
    match.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String inputS = input.getText();
        String regEx = expression.getText();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(inputS);
        String outputS = "";
        while(m.find()) {
          outputS += "Match \"" + m.group() +
            "\" at positions " + m.start() + "-" +
            (m.end() - 1) + '\n';
        }
        output.setText(outputS);
      }
    });
  }
  public static void main(String[] args) {
    run(new E06_TestRegularExpression2(), 700, 400);
  }
} ///:~
