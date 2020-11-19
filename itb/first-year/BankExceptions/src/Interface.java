package BankExceptions;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Interface extends JFrame {
	
	////////////////////////////////////////////////////////////
	// FRAMES
	////////////////////////////////////////////////////////////
	
	/*
	 * JInternal Frame
	 */
	   public static JInternalFrame createIFrame(String t, int sizex, int sizey, int locx, int locy) {
	      JInternalFrame jif = new JInternalFrame(t); //Constructor for Title of Internal Frame
	      jif.setResizable(true); 
	      jif.setClosable(true);
	      jif.setMaximizable(true);
	      jif.setIconifiable(true);
	      jif.setSize(sizex,sizey); //sets default size (overwritten in main)
	      jif.setLocation(locx,locy); // Set Location
	      jif.setVisible(true); //show it
	      jif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      return jif; // return values of F to main to set variables; similiar to CarHire Project last year
	   }
	   
	 /*
	  * JFrame
	  */
	   public static JFrame createJFrame(String t/*, int sizex, int sizey, int locx, int locy*/) {
			   JFrame jf = new JFrame(t); //Constructor for Title of Internal Frame
		      jf.setResizable(true); 
		      //jf.setSize(sizex,sizey); //sets default size (overwritten in main)
		      //jf.setLocation(locx,locy); // Set Location
		      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      jf.setVisible(true); //show it
		      jf.pack();
		      return jf; // return values of F to main to set variables; similiar to CarHire Project last year
		   }
		   
	   /*
		* JDesktop Pane
		*/
	   public static JDesktopPane createJDeskPane(){
		   JDesktopPane jdp = new JDesktopPane();
		   jdp.setVisible(true); //show it
		   return jdp;
	   }
	   
	   public static JPanel createJPanel(/*int sizex, int sizey, int locx, int locy*/){
		   JPanel jp = new JPanel();
		   //jp.setSize(sizex,sizey); //sets default size (overwritten in main)
		  // jp.setLocation(locx,locy); // Set Location
		   jp.setVisible(true);
		   return jp;
	   }
		   
	////////////////////////////////////////////////////////////
	// MENU BAR
	////////////////////////////////////////////////////////////
		   
	/*
	 * JMenu Bar
	 */
	   
	   public static JMenuBar createMenuBar() {
	        JMenuBar m = new JMenuBar();

	       JMenu fileMenu = new JMenu("File", false);
			fileMenu.add(new JMenuItem("New"));
			fileMenu.add(new JMenuItem("Save"));
			fileMenu.add(new JMenuItem("Close"));
			m.add(fileMenu);
			
			JMenu editMenu = new JMenu("Edit", false);
			editMenu.add(new JMenuItem("Undo"));
			editMenu.add(new JMenuItem("Redo"));
			editMenu.add(new JMenuItem("Copy"));
			editMenu.add(new JMenuItem("Paste"));
			m.add(editMenu);
			
			JMenu helpMenu = new JMenu("Help", false);
			helpMenu.add(new JMenuItem("Help Contents"));
			helpMenu.add(new JMenuItem("Search"));
			helpMenu.add(new JMenuItem("Tips and Tricks"));
			helpMenu.add(new JMenuItem("About this Program"));
			m.add(helpMenu);
	        return m;
	    }
}