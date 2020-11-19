package BankExceptions;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class Buttons{
	/*
	 * JBUTTON
	 */
	static JButton createButton(String title, String tooltip, String actionCommand, String fontface, int fontsize, String icon, int sizex, int sizey){
		Listeners l = new Listeners();
		// Create a new Button
	    JButton b = new JButton();

	    // add text
	    b.setText(title);
	    
	    //Set Label
	    b.setToolTipText(tooltip);
	    
	    //add text styling
	    Font f = new Font(fontface, Font.BOLD, fontsize);

	    //set font to label
	    b.setFont(f);

	    Icon i = new ImageIcon(icon);

		b.setIcon(i);
	    b.setSize(sizex, sizey);
	    b.setActionCommand(actionCommand);
	    b.addActionListener(l);
		return b;
	}
	
	/*
	 * JTOGGLE BUTTON
	 */
	static JToggleButton createTButton(){
		JToggleButton tb = new JToggleButton();
		return tb;
	}
	
	/*
	 * JCHECKBOX BUTTON
	 */
	static JCheckBox createChkButton(String title, boolean b){
		JCheckBox cb = new JCheckBox(title, b);
		return cb;
	}
	
	/*
	 * JRADIO BUTTON
	 */
	static JRadioButton createRButton(String text, String label, boolean b){
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rb = new JRadioButton();
		rb.setText(text);
		rb.setToolTipText(label);
		rb.setEnabled(b);
		return rb;
	}
	
	
	
	
	
	
	
	
}