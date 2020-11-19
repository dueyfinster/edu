package Project1;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Irish extends AddressFactory{
	public Address createAddress(){
		JPanel jpl = new JPanel();
		
		// JLabels
		JLabel jl1 = new JLabel("Company Name:");
		JLabel jl2 = new JLabel("Number:");
		JLabel jl3 = new JLabel("Street Address:");
		JLabel jl5 = new JLabel("Town:");
		JLabel jl4 = new JLabel("County:");
		// Add JLabels to panel
		jpl.add(jl1);
		jpl.add(jl2);
		jpl.add(jl3);
		jpl.add(jl4);
		jpl.add(jl5);

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
		
		return new Address(jpl);
	}
	
	public PhoneNo createPhoneNo(){
		JPanel jpl = new JPanel();
		
		// JLabels
		JLabel jl1 = new JLabel("International Code:");
		JLabel jl2 = new JLabel("Area code:");
		JLabel jl3 = new JLabel("Phone Number:");
		// Add JLabels to panel
		jpl.add(jl1);
		jpl.add(jl2);
		jpl.add(jl3);

		//JTextfields
		JTextField jtf1 = new JTextField(10);
		JTextField jtf2 = new JTextField(10);
		JTextField jtf3 = new JTextField(10);
		//Add JTextfields to Panel
		jpl.add(jtf1);
		jpl.add(jtf2);
		jpl.add(jtf3);
		
		return new PhoneNo(jpl);
	}
}