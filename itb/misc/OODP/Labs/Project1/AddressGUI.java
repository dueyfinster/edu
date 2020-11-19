package Project1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddressGUI {
	private JFrame jf = new JFrame();
	private JPanel pnlNorth = new JPanel(); 
	private JPanel pnlSouth = new JPanel(); 
	private JPanel pnlEast = new JPanel(); 
	private JPanel pnlWest = new JPanel(); 
	private JPanel pnlCenter = new JPanel(); 
	private JButton jb1 = new JButton("American");		
	private JButton jb2 = new JButton("Irish");		
	private JTextArea jta = new JTextArea();
	
	AddressGUI(){
		
		pnlNorth.add(jb1);
		pnlCenter.add(jb2);
		
		jb1.addActionListener(new ButtonListener());
		jb2.addActionListener(new ButtonListener());
		
		jf.getContentPane().setLayout(new BorderLayout());
		jf.getContentPane().add(pnlNorth, BorderLayout.NORTH);
		jf.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		jf.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		jf.getContentPane().add(pnlWest, BorderLayout.WEST);
		jf.getContentPane().add(pnlEast, BorderLayout.EAST);
		
		
		jf.setSize(300, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
		jf.pack(); //Adjusts panel to components for display
		jf.setVisible(true);
	}


	public static void main(String args[]){
		new AddressGUI();
	}

	class ButtonListener implements ActionListener {
		  ButtonListener() {
		  }

		  public void actionPerformed(ActionEvent e) {
			  if (e.getActionCommand().equals("American")) {
				  System.out.println("American has been clicked");
				  American am = new American();
				  pnlEast = am.createAddress().getAddress();
				  //pnlSouth = am.createPhoneNo().getAddress();
				  jf.repaint();
			  }else if(e.getActionCommand().equals("Irish")){
				  System.out.println("Irish has been clicked");
				  Irish ire = new Irish();
				  pnlEast = ire.createAddress().getAddress();
				  //pnlSouth = ire.createPhoneNo().getAddress();
				  jf.repaint();
			  }
		}

	}
	
}