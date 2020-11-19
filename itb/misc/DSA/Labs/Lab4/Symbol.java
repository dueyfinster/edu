package Lab4;
/*
 * This is a square box that takes a string as an argument and displays the string over
 * the box.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class Symbol extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Symbol()
		{
		}
		
		public Symbol(String v)
		{
			
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			setPreferredSize(new Dimension(150, 50));
			setBorder(BorderFactory.createLineBorder (Color.blue, 2));
			setBackground(Color.red);
			Graphics2D g2d = (Graphics2D)g;
		    g2d.drawString("Hello", 10, 30);
			
		}
	}