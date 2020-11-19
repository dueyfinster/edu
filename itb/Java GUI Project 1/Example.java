import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Example extends JFrame{

	// Attributes
	private String code;
	private int x;
	private int y;
	private String title;

	// Default Constructors
	Example(){
		code = "01101100";
		x = 250;
		y = 250;
		title = "Code Example";
	}

	// User Defined Constructors
	Example(String c, int x1, int y1, String t){
		code = c;
		x = x1;
		y = y1;
		title = t;
	}

	// Display Method
	public void Display(){
		
		JFrame frame = new JFrame(title);

		JPanel panel = new JPanel();
		JTextArea ta = new JTextArea();
		ta.setText(code);
		panel.add(ta);

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(x, y);
		frame.setTitle(title);
		frame.setLocation(100, 100);
		frame.setVisible(true);

		/*  OLD CODE
		 *	Container c = getContentPane();
		 *	JPanel panel = new JPanel();
		 *	JTextArea ta = new JTextArea();
	     *
		 *	ta.setText(code);
		 *	panel.add(ta);
		 *	c.add(panel);
   		 *
		 */		
	}
	
	// Code
	public void setCode(String c){
		code = c;
	}

	// X
	public void setX(int x1){
		x = x1;
	}

	// Y
	public void setY(int y1){
		y = y1;
	}

	// Title
	public void setTitle(String t){
		title = t;
	}

}  // end of program