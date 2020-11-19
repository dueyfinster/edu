package BankExceptions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.security.PublicKey;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Text{
	static String text_content;
	/*
	 * JTEXT FIELD
	 */
	static JTextField createTextField(String name,String ac, int columns, String text_content){
		Listeners l = new Listeners();
		JTextField tf = new JTextField(name, columns);
		tf.setText(text_content);
		tf.addActionListener(l);
		tf.setActionCommand(ac);
		tf.setEditable(true);
		return tf;
	}
	
	static JFormattedTextField createFormatText(String text_content, int columns){
		Listeners l = new Listeners();
		JFormattedTextField jft = new JFormattedTextField();
		jft.setText(text_content);
		jft.setColumns(columns);
		jft.addActionListener(l);
		jft.setEditable(false);
		return jft;
		
	}
	
	
	
	/*
	 * JPASSWORD FIELD
	 */
	static JPasswordField createPassField(){
		JPasswordField pwdf = new JPasswordField();
		return pwdf;
	}
	
	/*
	 * JTEXT AREA
	 */
	static JTextArea createTextArea(int rows, int columns, String text_content){
		JTextArea ta = new JTextArea(rows, columns);
		ta.setPreferredSize(new Dimension(80, 80));
		ta.setLineWrap(false);
		ta.setText(text_content);
		ta.setEditable(true);
		ta.setEnabled(true);
		ta.setVisible(true);
		return ta;
	}
	
	
	static JLabel createJLabel(String title){
		JLabel jl = new JLabel();
		jl.setText(title);
		return jl;
	}
	
	static JComboBox createJCombo(String choices[]){
		JComboBox c = new JComboBox();
		
		int count = 0;
		int length = choices.length;

		for (int i = 0; i < length; i++){
		      c.addItem(choices[i]);
		    }
		    
			return c;
	}
	
	
}