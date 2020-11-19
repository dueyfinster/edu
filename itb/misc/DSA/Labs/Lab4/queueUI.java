package Lab4;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class queueUI extends JFrame
{
	public JTextField jtf = new JTextField(20);
	public QueueArrayBased queue1 = new QueueArrayBased();
	/*
	 * Make Buttons
	 */
	JButton enqueue = new JButton("Enqueue");
	JButton dequeue = new JButton("Dequeue");
	JButton peek = new JButton("Peek");
	JButton delete = new JButton("Delete All");
	JButton display = new JButton("Display Queue");
	
	
	
	/*
	 * End of Make Buttons
	 * Now Make Queue, public so available to all methods
	 */
	public queueUI()
	{
		
		/*
		 * Get screen dimensions and size up window, add title to frame.
		 * Set up the frame before making it visible.
		 */
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);//X button
		Toolkit kit1 = Toolkit.getDefaultToolkit();//Creates Toolkit object
		Dimension screenSize1 = kit1.getScreenSize();//Uses toolkit to get resolution
		int screenWidth = (int) screenSize1.getWidth();//Get width as double in pixels
		int screenHeight = (int) screenSize1.getHeight();//Get Height as double in pixels
		setSize(screenWidth/2,screenHeight/2);//sets the size to half the screen size
		setLocation(screenWidth / 4, screenHeight / 4);//Sets the location to the centre of the screen
		setTitle("ADT Queue: ");// Sets the title of the window
		setResizable(false);//I don't want to let anyone resize the frame
		
		
		
		enqueue.addActionListener(new ButtonListener());
		dequeue.addActionListener(new ButtonListener());
		peek.addActionListener(new ButtonListener());
		delete.addActionListener(new ButtonListener());
		/*
		 * Create 10 panels
		 */
		JPanel pnlWest = new JPanel();
		for(int i=0;i<10;i++)
		{
			String printLabel = Integer.toString(i);
			pnlWest.add(new JLabel("Queue Pos: " +printLabel+" = "));
			//Adds 10 JLabels, each one lists a value from the queue.
		}
		
		JPanel pnlEast = new JPanel();
		pnlEast.add(new JLabel("Enter Something into the Queue! "));
		pnlEast.add(jtf);
		pnlEast.add(enqueue);
		pnlEast.add(dequeue);
		pnlEast.add(peek);
		pnlEast.add(delete);
		pnlEast.add(display);
		
		pnlWest.setLayout(new GridLayout(10,1));
		
		getContentPane().add(pnlWest);
		getContentPane().add(pnlEast);
		getContentPane().setLayout(new GridLayout(1,2));
		/*
		 * Last of all
		 */
		setVisible(true);//I want the frame to be visible
		/*
		 * The above code just sets up the window, the way we need it
		 */
	}
	
	
	
	
	public static void main(String[]args)
	{
		new queueUI();
	}
	
	
	
	
	public void delFromQueue()
	{
		queue1.dequeue();
		
	}
	
	public void peekFromQueue()
	{
		jtf.setText(queue1.peek().toString());
	}
	
	public void delAll()
	{
		
	}
	
	
	
}
class ButtonListener implements ActionListener 
{
	  ButtonListener() 
	  {
		  
	  }
	  
	  

	  public void actionPerformed(ActionEvent e) 
	  {
		  if (e.getActionCommand().equals("Enqueue")) 
		  
		  {
			  //Action to happen when Enqueue is pressed
			  System.out.print("Enqueue");
			  putInQueue();
		  }
		  	else if(e.getActionCommand().equals("Dequeue"))
		  {
		  		//Action to happen when Dequeue is pressed
		  		System.out.print("Dequeue");
		  }
		  	else if(e.getActionCommand().equals("Peek"))
		  {
		  		//Action to happen when Peek is pressed.
		  		System.out.print("Peek");
		  }
		  	else if(e.getActionCommand().equals("Delete All"))
		  {
				//Delete all items from queue
		  		System.out.print("Delete");
		  }
		  	else if(e.getActionCommand().equals("Display Queue"))
		  	{
		  		//Action to do when we want to display all items
		  		System.out.print("Display Queue");
		  	}
		  
		  
		  
	  }
	  public void putInQueue()
		{
			String item1 = new String(jtf.getText());
			queue1.enqueue(item1);
			jtf.setText("");
		}
	  
	  
}
