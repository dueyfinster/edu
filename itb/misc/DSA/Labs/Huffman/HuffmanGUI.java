package Huffman;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HuffmanGUI extends JFrame implements ActionListener
{
  /**
   * Buttons
   */ 
  JButton enc = new JButton("Encode");
  JButton dec = new JButton("Decode");
  
 
  /**
   * Text Area's - will take input and output for decoding
   */
  JTextArea inp = new JTextArea("Input");
  JTextArea out = new JTextArea("Output");
 
  /**
   * Middle Console Panel which holds the button operations
   */ 
  JPanel middle = new JPanel();
  
  /**
   * Middle Console Panel which holds the button operations
   */ 
  JMenuBar jmb = new JMenuBar();
  
  /**
   * Middle Console Panel which holds the button operations
   */ 
  JMenu fle = new JMenu("File");
  jmb.add(fle);
  
  /**
   * Middle Console Panel which holds the button operations
   */ 
  JMenuItem quit = new JMenuItem("Quit");
  file.add(quit);
  
  

 
  /**
   * The top level panel which holds all.
   */
  JPanel jp = new JPanel();
  
//Install the menu bar in the frame
  jp.setJMenuBar(jmb);
 
  /**
   * Constructor
   */
  public HuffmanGUI()
  {
	  middle.setLayout(new FlowLayout());
	  middle.add(enc);
	  middle.add(dec);
 
	  jp.setLayout(new BorderLayout());
    
	  jp.add(inp, BorderLayout.NORTH);
	  jp.add(middle, BorderLayout.CENTER);
	  jp.add(out, BorderLayout.SOUTH);
    
 
    getContentPane().add(jp, BorderLayout.CENTER);
 
    enc.addActionListener(this);
    dec.addActionListener(this);
 
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
 
  /**
   * Main Class to initiate program
   */
  public static void main(String[] args)
  {
    HuffmanGUI myApplication = new HuffmanGUI();
 
    // Specify where will it appear on the screen:
    myApplication.setLocation(10, 10);
    myApplication.setSize(300, 300);
 
    // Show it!
    myApplication.setVisible(true);
  }
 
  /**
   * Each non abstract class that implements the ActionListener
   * must have this method.
   * 
   * @param e represents the Action being passed from GUI
   */
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == enc)
      enc.setText("Encode Action");
    //huff.encode();
    else if (e.getSource() == dec)
    	dec.setText("Decode Action");
  }
}

