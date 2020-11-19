package presentation;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;//Import swing libraries
import run.*;

public class UI //User Interface
{

	public static void main(String[]args)
	{
		makeGUI();
	}
	
	public static void makeGUI()
	{
		mainFrame mainFrame1 = new mainFrame();
		mainFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = mainFrame1.getContentPane();
		contentPane.setLayout(new GridLayout(2,1));
		
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		final JComboBox countryCombo = new JComboBox(new String[] {"Select Country","US","Ireland"});
		panel1.add(countryCombo);
		
		JButton button1 = new JButton("Click Here");
		panel1.add(button1);
		panel2.setBackground(Color.black);
		panel1.setBackground(Color.black);
		final JTextArea countryText = new JTextArea(10,50);
		panel2.add(countryText);
		button1.addActionListener(new ActionListener() 
		{
            
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                if(countryCombo.getSelectedIndex()==0)
                {
                	countryText.setText("Choose a country!");
                }
                if(countryCombo.getSelectedIndex()==1)
                {
                	AddressFactory usAddressFactory = new USAddressFactory();
        	        Address usAddress = usAddressFactory.createAddress();
        	        PhoneNumber usPhone = usAddressFactory.createPhoneNumber();
        	        
        	        usAddress.setBusinessName("Marks US Business");
        	        usAddress.setStreet("New York Street");
        	        usAddress.setCity("NY");
        	        usAddress.setState("New York State");
        	        usAddress.setZip("54321");
        	        
        	        usPhone.setCountryCode("001");
        	        usPhone.setStateCode("123");
        	        usPhone.setLocalNumber("2123456789");
        	        
                	countryText.setText(usAddress.getFullAddress()+usPhone.getNumber());
                }
               if(countryCombo.getSelectedIndex()==2)
                {
	            	AddressFactory IrishAddressFactory = new IrishAddressFactory();
	       	        Address IrishAddress = IrishAddressFactory.createAddress();
	       	        PhoneNumber IrishPhone = IrishAddressFactory.createPhoneNumber();
	       	        
	       	        IrishAddress.setBusinessName("Marks Irish Business");
	       	        IrishAddress.setStreet("21 O Connell Street");
	 	        	IrishAddress.setCity("Dublin");
	 	        	IrishAddress.setCounty("Co. Dublin");
	 	        	
	 	        	IrishPhone.setLocalCode("01");
	 	        	IrishPhone.setLocalNumber("1234567");
	 	        	
	       	        countryText.setText(IrishAddress.getFullAddress()+IrishPhone.getNumber());
                }
                
            }
        });
		
		contentPane.add(panel1);
		contentPane.add(panel2);
		
		mainFrame1.setVisible(true);
	}
}
	
	
	class mainFrame extends JFrame
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public mainFrame()
		{
			//Get Resolution of screen and set window size...
			Toolkit kit1 = Toolkit.getDefaultToolkit();
			Dimension screenSize1 = kit1.getScreenSize();
			
			int screenWidth = (int) screenSize1.getWidth();
			int screenHeight = (int) screenSize1.getHeight();
			//These methods return doubles, these doubles are being cast as ints.
			
			//End Get Resolution of screen and set window size...
			
			setSize(screenWidth/2,screenHeight/2);
			
			setLocation(screenWidth / 4, screenHeight / 4);
			
			setTitle("Abstract Factory Pattern Project");
			
			
		}
	}
