package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class HuffmanUI extends JFrame
{
	/*
	 * Variables and Objects
	 */
	
	/*
	 * Variables and Objects
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HuffmanUI()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);//X button
		Toolkit kit1 = Toolkit.getDefaultToolkit();//Creates Toolkit object
		Dimension screenSize1 = kit1.getScreenSize();//Uses toolkit to get resolution
		int screenWidth = (int) screenSize1.getWidth();//Get width as double in pixels
		int screenHeight = (int) screenSize1.getHeight();//Get Height as double in pixels
		setSize(screenWidth/2,screenHeight/2);//sets the size to half the screen size
		setLocation(screenWidth / 4, screenHeight / 4);//Sets the location to the centre of the screen
		setTitle("Huffman User Interface: ");// Sets the title of the window
		setResizable(false);//I don't want to let anyone resize the frame
		setVisible(true);
	}

}
