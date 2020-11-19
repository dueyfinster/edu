package Project2.Decorator;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

//swing classes
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;


public class boxWindow extends JxFrame implements ActionListener{
   JLabel label;
   JButton Quit;
   
   public boxWindow()
   {
      super ("Box Window");
      JPanel jp = new JPanel();

      getContentPane().add(jp);
	  jp.add(new BoxDecorator (label = new JLabel("Boxy")));


      jp.add(Quit = new JButton("Quit"));
      Quit.addActionListener(this);
      setSize(new Dimension(200,100));

      setVisible(true);
      Quit.requestFocus();
   }
   public void actionPerformed(ActionEvent e)
   {
      System.exit(0);
   }
   static public void main(String argv[])
   {
      new boxWindow();
   }
}
