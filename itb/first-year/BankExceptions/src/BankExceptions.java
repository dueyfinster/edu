/**
 * A class which sets up the Main Program and calls the other classes
 */
package BankExceptions;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author ngrogan
 *
 */
public class BankExceptions extends JFrame{
	
	JFrame myFrame = Interface.createJFrame("Bank Exceptions");
	JPanel jp = Interface.createJPanel();
	
	JFormattedTextField jft1 = Text.createFormatText("",5);
	JFormattedTextField jft2 = Text.createFormatText("",5);
	JTextField jtf1 = Text.createTextField("","",5,""); 
	
	BankExceptions(){
		//Create Frame (See Interface.java)
		myFrame.setSize(300,230);	myFrame.setResizable(false); 
		
		//Create Frame (See Interface.java)
		jp.setSize(300, 230);
		
		//Do Layout (See Interface.java)
	    SpringLayout layout = new SpringLayout();
	    jp.setLayout(layout); //add to panel
		
		//Set up MenuBar (Also in Interface)
		JMenuBar mb = Interface.createMenuBar();
		myFrame.setJMenuBar(mb);	//add it to the frame		
		
		//Create JLabels and Text Fields for Patients (see Text.java)
		JLabel jl1 = Text.createJLabel("Input:"); 	jp.add(jl1);
		 
		JLabel jl2 = Text.createJLabel("Output:");	jp.add(jl2);
			jp.add(jft1);
		

		//////////////////////////
		//Start of Input  Verfication
		//////////////////////////	
		
	    /*InputVerifier verifier = new InputVerifier() { //Call Input Verifier
	        public boolean verify(JComponent comp) { //New Component comp
	          boolean returnValue; // Sets up value to be returned
	          JTextField textField = (JTextField) comp; //Create faux TextField
	          try { //Tryif exception occurs
	            Integer.parseInt(textField.getText()); //Parse int's from Textfield
	            returnValue = true; //If it's good to go (a number!) return true
	            if(returnValue = true){
	            	//Do action of using input
	            }
	            
	          } catch (NumberFormatException e) { //Catch non number chars
	            returnValue = false; //false so that they cannot leave until a number is processed
	            JOptionPane.showMessageDialog(comp, "Sorry you must enter a number", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
	            jtf1.setText("");
	          }
	          return returnValue; //Return Value of true/false to verify input 
	        }
	      };
	      	
	      jtf1.setInputVerifier(verifier); // Add Verifier to Text Field*/
		    
		    jp.add(jtf1); // add Text Field to Panel
		    //////////////////////////
			// End of Input Verification
			//////////////////////////
		
		
		//Set up Text area for Address
		Balance bal = new Balance();
		JLabel jl4 = Text.createJLabel("Status:"); jp.add(jl4); jp.add(jft2);
		
	    
	    //JButtons with ActionListeners (in Buttons/Listeners.Java)								//Set Foreground Colour
	    JButton jb1 = Buttons.createButton("Check Balance","","ChkBal", "Arial", 10, "", 20,50); 	jb1.setForeground(Color.blue);	jp.add(jb1);
	    JButton jb2 = Buttons.createButton("Credit Account","","CredAC", "Arial", 10, "", 20,50);	jb2.setForeground(Color.green);	jp.add(jb2);
	    JButton jb3 = Buttons.createButton("Debit Account","","DebAC", "Arial", 10, "", 20,50); 	jb3.setForeground(Color.red);	jp.add(jb3);
	    JButton jb4 = Buttons.createButton("Check Pin","","ChkPin", "Arial", 10, "", 20,50); 	jb4.setForeground(Color.yellow);	jp.add(jb4);
	    JButton jb5 = Buttons.createButton("Exit","","Exit", "Arial", 10, "", 20,50);						jb5.setForeground(Color.red);	jp.add(jb5);
	    
	    
	    //LAYOUT JLABELS
	    //Locate JLabel West (left) (15 in) on JPanel
	    //Locate JLabel North (top) * (made to suit) amount down
	    layout.putConstraint(SpringLayout.WEST, jl1, 150, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jl1, 10, SpringLayout.NORTH,jp);
	    layout.putConstraint(SpringLayout.WEST, jl2, 150, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jl2, 40, SpringLayout.NORTH,jp);
	    layout.putConstraint(SpringLayout.WEST, jl4, 15, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jl4, 5, SpringLayout.NORTH,jp);
	    
	    layout.putConstraint(SpringLayout.WEST, jb1, 150, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jb1, 70, SpringLayout.NORTH,jp);
	    layout.putConstraint(SpringLayout.WEST, jb2, 150, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jb2, 90, SpringLayout.NORTH,jp);
	    layout.putConstraint(SpringLayout.WEST, jb3, 150, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jb3, 110, SpringLayout.NORTH,jp);
	    layout.putConstraint(SpringLayout.WEST, jb4, 150, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jb4, 130, SpringLayout.NORTH,jp);
	    layout.putConstraint(SpringLayout.WEST, jb5, 150, SpringLayout.WEST,jp);
	    layout.putConstraint(SpringLayout.NORTH, jb5, 150, SpringLayout.NORTH,jp);

	    // LAYOUT ELEMENTS
	    //Locate Element West (left) (5 in from JLabel)
	    //Locate Element North (top) * amount down (made to suit) on JPanel
	    layout.putConstraint(SpringLayout.WEST, jtf1, 17, SpringLayout.EAST, jl1);
	    layout.putConstraint(SpringLayout.NORTH, jtf1, 5, SpringLayout.NORTH, jp);
	    layout.putConstraint(SpringLayout.WEST, jft1, 7, SpringLayout.EAST, jl2);
	    layout.putConstraint(SpringLayout.NORTH, jft1, 35, SpringLayout.NORTH, jp);
	    layout.putConstraint(SpringLayout.WEST, jft2, 0, SpringLayout.SOUTH, jl4);
	    layout.putConstraint(SpringLayout.NORTH, jft2, 20, SpringLayout.NORTH, jp);
	    ;
	    // Set Background of JPanel to Light Gray
	    jp.setBackground(Color.LIGHT_GRAY);
	    
	    //Add Panel o Frame
	    myFrame.add(jp);
	    //Set Visible
	    myFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BankExceptions();
	}
	
	public String jtf1() {
		String text = jtf1.getText();
		return text;
	}
	
	public void jft1(String text) {
		jft1.setText(text);
		
	}
	
	public void jft2(String text) {
		jft2.setText(text);
		
	}

}
