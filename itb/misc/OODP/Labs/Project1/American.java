package Project1;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class American extends AddressFactory{
	public Address createAddress(){
		JPanel jpl = new JPanel();
		
		// JLabels
		JLabel jl1 = new JLabel("Corporation Name:");
		JLabel jl2 = new JLabel("Building Number:");
		JLabel jl3 = new JLabel("Street Address:");
		JLabel jl4 = new JLabel("County:");
		JLabel jl5 = new JLabel("City:");
		JLabel jl6 = new JLabel("Zip Code:");
		JLabel jl7 = new JLabel("State:");
		// Add JLabels to panel
		jpl.add(jl1);
		jpl.add(jl2);
		jpl.add(jl3);
		jpl.add(jl4);
		jpl.add(jl5);
		jpl.add(jl6);
		jpl.add(jl7);
		
		//JTextfields
		JTextField jtf1 = new JTextField(10);
		JTextField jtf2 = new JTextField(10);
		JTextField jtf3 = new JTextField(10);
		JTextField jtf4 = new JTextField(10);
		JTextField jtf5 = new JTextField(10);
		JTextField jtf6 = new JTextField(10);
		JTextField jtf7 = new JTextField(10);
		//Add JTextfields to Panel
		jpl.add(jtf1);
		jpl.add(jtf2);
		jpl.add(jtf3);
		jpl.add(jtf4);
		jpl.add(jtf5);
		jpl.add(jtf6);
		jpl.add(jtf7);
		
		
		
		return new Address(jpl);
	}
	
	public PhoneNo createPhoneNo(){
		JPanel jpl = new JPanel();
		
		// JLabels
		JLabel jl1 = new JLabel("International Code:");
		JLabel jl2 = new JLabel("Area code:");
		JLabel jl3 = new JLabel("Phone Number:");
		JLabel jl4 = new JLabel("Ext:");
		// Add JLabels to panel
		jpl.add(jl1);
		jpl.add(jl2);
		jpl.add(jl3);
		jpl.add(jl4);

		//JTextfields
		JTextField jtf1 = new JTextField(10);
		JTextField jtf2 = new JTextField(10);
		JTextField jtf3 = new JTextField(10);
		JTextField jtf4 = new JTextField(10);
		//Add JTextfields to Panel
		jpl.add(jtf1);
		jpl.add(jtf2);
		jpl.add(jtf3);
		jpl.add(jtf4);
		
		return new PhoneNo(jpl);
	}
}