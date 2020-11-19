package Lab3;
/*
 * Methods used,
 * isEmpty() -- Used for the do.. while loop to check if the stack is empty
 * popAll() -- used with delete button
 * push() -- used with the push button
 * pop() -- used with the pop button
 * peek() -- not used yet!
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StackGUIDriver 
{
	public static final int MAX_ITEMS = 15;
	private JFrame jf = new JFrame();
	private JPanel pnlNorth = new JPanel(); 
	private JPanel pnlSouth = new JPanel(); 
	private JPanel pnlEast = new JPanel(); 
	private JPanel pnlWest = new JPanel(); 
	private JPanel pnlCenter = new JPanel(); 
	private JButton jb1 = new JButton("Push");		
	private JButton jb2 = new JButton("Pop");
	private JButton jb4 = new JButton("Delete");
	private JButton jb3 = new JButton("Display");
	private JButton jb5 = new JButton("Check Palendrome");
	private JTextArea jta = new JTextArea(20,20);
	private JTextField jtf = new JTextField(20);
	
	protected StackArrayBased stack1 = new StackArrayBased();
	//Stack for checking palendrome
	protected StackArrayBased palendromeStack = new StackArrayBased();
	
	StackGUIDriver(){
		
		pnlNorth.add(jb1);
		pnlNorth.add(jb2);
		pnlNorth.add(jb4);
		pnlNorth.add(jb3);
		pnlCenter.add(jta);
		pnlSouth.add(jtf);
		pnlSouth.add(jb5);
		
		jb1.addActionListener(new ButtonListener());
		jb2.addActionListener(new ButtonListener());
		jb3.addActionListener(new ButtonListener());
		jb4.addActionListener(new ButtonListener());
		jb5.addActionListener(new ButtonListener());
		
		jf.getContentPane().setLayout(new BorderLayout());
		jf.getContentPane().add(pnlNorth, BorderLayout.NORTH);
		jf.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		jf.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		jf.getContentPane().add(pnlWest, BorderLayout.WEST);
		jf.getContentPane().add(pnlEast, BorderLayout.EAST);
		
		
		jf.setSize(300, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
		jf.pack(); //Adjusts panel to components for display
		jf.setVisible(true);
	}


	public static void main(String args[]){
		new StackGUIDriver();
		
	}

	class ButtonListener implements ActionListener 
	{
		  ButtonListener() 
		  {
			  
		  }

		  public void actionPerformed(ActionEvent e) 
		  {
			  if (e.getActionCommand().equals("Push")) 
			  
			  {
				  //Action to happen when Push is pressed
				  pushStack();
				  
			  }
			  	else if(e.getActionCommand().equals("Pop"))
			  {
			  		//Action to happen when pop is pressed
				  popStack();
			  }
			  	else if(e.getActionCommand().equals("Display"))
			  {
			  		//Action to happen when Display is pressed.
			  		//Need to pop all items and display them line by line.
				  System.out.println("Display has been clicked");
				  display();
			  }
			  	else if(e.getActionCommand().equals("Delete"))
			  	{
			  		stack1.popAll();//Pops all items off the stack!
			  	}
			  	else if(e.getActionCommand().equals("Check Palendrome"))
			  	{
			  		checkPalendrome();
			  	}
		  }
		  
		  public void checkPalendrome()
		  {
			  String palendrome = jtf.getText();
			  String reversedP="";
			  /*
			   *Gets the string from the textfield and save them as a character array. 
			   */
			
			  char[] charArray1 = palendrome.toCharArray();
			  
			  for(int i=0;i<charArray1.length;i++)
			  {
				  palendromeStack.push(charArray1[i]);
				  //Adds each character to the stack 1 by 1
			  }
			  for(int i=0;i<charArray1.length;i++)
			  {
				  String a = palendromeStack.pop().toString();
				  //Adds each character to the stack 1 by 1
				  reversedP=reversedP+=a;
			  }
			  if(palendrome.equalsIgnoreCase(reversedP))
				  JOptionPane.showMessageDialog(jb1, "This String is a Palendrome");
			  else
				  JOptionPane.showMessageDialog(jb1, "This String is not a Palendrome");
				  
			  
			  
		  }
		  public void pushStack()
		  {
			  System.out.println("Push has been clicked");
			  stack1.push(jta.getText());
			  jta.setText("");
		  }
		  public void popStack()
		  {
			  System.out.println("Pop has been clicked");
			  try
			  {
				  stack1.pop();
			  }catch(StackException exception1)
			  {
				  JOptionPane.showMessageDialog(jb1, "No Items currently in stack!");
			  }
		  }
		  public void display()
		  {
			  jtf.setText("");//Clear text area.
			  try
			  {
				  do
				  {
					  jta.append(stack1.pop().toString()+'\n');
				  }while(!stack1.isEmpty());
				  
			  }catch(StackException exception1)
			  
			  {
				  JOptionPane.showMessageDialog(jb1, "No Items currently in stack!");
				  jta.setText("");
			  }
		  }

	}
	
}