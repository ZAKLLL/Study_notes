//: gui/E40_DisplayProperties2.java
// {Requires: org.eclipse.swt.widgets.Display; You must
// install the SWT library from http://www.eclipse.org }
/****************** Exercise 40 ******************
 * Modify DisplayProperties.java so that it uses
 * SWTConsole.
 ***********************************************/
package gui;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import java.io.*;
import swt.util.*;

public 
class E40_DisplayProperties2 implements SWTApplication {
  public void createContents(Composite parent) {
    parent.setLayout(new FillLayout());
    Text text = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
    StringWriter props = new StringWriter();
    System.getProperties().list(new PrintWriter(props));
    text.setText(props.toString());
  }
  public static void main(String [] args) {
    SWTConsole.run(new E40_DisplayProperties2(), 400, 300);
  }
} ///:~
