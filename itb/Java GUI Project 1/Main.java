import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JFrame implements ListSelectionListener{

	String choices[] = { "Frame","Panel + Label","Basic Text","Button","Password Field","Combo Box","List","Radio Button","Check Box","Layout Manager","Event Button","Event Window","Listener Button","Listener Window" };
	JList menuList;

	public Main(){

		super("Menu");
		Container c = getContentPane();
		JPanel panel = new JPanel();

 		menuList = new JList(choices);
		menuList.addListSelectionListener(this);

		Font menuFont = new Font("Verdana", Font.PLAIN, 14);
		menuList.setFont(menuFont);

		panel.add(menuList);
		c.add(panel);
		setSize(140, 325);
		setVisible(true);
	}
	public static void main(String[] args){
		Main panda = new Main();
	}

	public void valueChanged(ListSelectionEvent e) {

		Object source = e.getSource();

		if (source == menuList){

			// FRAME
			if(menuList.getSelectedValue() == "Frame"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\"); // set the title\n            Container c = getContentPane();\n            setSize(200, 100); // set the size (x, y)\n            setVisible(true); // show the frame\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(350);
				ex.setY(300);
				ex.setTitle("Frame Code");
				ex.Display();
			}
            
			// PANEL + LABEL			else if(menuList.getSelectedValue() == "Panel + Label"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel(); // create a panel\n            JLabel label = new JLabel(\"Hi! I'm a label!\"); // create a label and set the text\n\n            panel.add(label); // add the label to the panel\n            c.add(panel); // add the panel to the Container\n            setSize(200, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
            	ex.setCode(code);
				ex.setX(540);
				ex.setY(380);
				ex.setTitle("Panel + Label Code");
				ex.Display();
			}

			// BASIC TEXT
			else if(menuList.getSelectedValue() == "Basic Text"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel();\n            JLabel label1 = new JLabel(\"Basic Font\");\n            JLabel label2 = new JLabel(\"Fancy Font\");\n\n            Font fancyFont = new Font(\"Serif\", Font.BOLD | Font.ITALIC, 18); // Create custom font\n            label2.setFont(fancyFont); // set label2's font to be \"fancyFont\"\n\n            panel.add(label1);\n            panel.add(label2);\n            c.add(panel);\n            setSize(200, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(620);
				ex.setY(460);
				ex.setTitle("Basic Text Code");
				ex.Display();				
			}
			
			// BUTTON
			else if(menuList.getSelectedValue() == "Button"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel();\n            JButton button1 = new JButton(\"Click Me!\"); // create a button and set the text\n\n            panel.add(button1);\n            c.add(panel);\n            setSize(200, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(570);
				ex.setY(380);
				ex.setTitle("Button Code");
				ex.Display();
			}
					
			// PASSWORD FIELD
			else if(menuList.getSelectedValue() == "Password Field"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel();\n            JPasswordField pass = new JPasswordField(10); // create a password field and set its size\n\n            panel.add(pass);\n            c.add(panel);\n            setSize(200, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(630);
				ex.setY(380);
				ex.setTitle("Password Field Code");
				ex.Display();
			}
					
			// COMBO BOX
			else if(menuList.getSelectedValue() == "Combo Box"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel();\n\n            String choices[] = {\"Apple\", \"Orange\", \"Banana\", \"Pear\",\"Grapes\"}; // create a string of 5 items\n            JComboBox combo1 = new JComboBox(choices); // create a combo box and add the string \"chocies\" to it\n            combo1.setMaximumRowCount(4); // set the combo box to display only 4 items at a time\n\n            panel.add(combo1);\n            c.add(panel);\n            setSize(200, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(740);
				ex.setY(430);
				ex.setTitle("Combo Box Code");
				ex.Display();
			}

			// LIST
			else if(menuList.getSelectedValue() == "List"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel();\n\n            String choices[] = {\"Apple\", \"Orange\", \"Banana\", \"Pear\",\"Grapes\"}; // create a string of 5 items\n            JList list = new JList(choices); // create a list and add the string \"chocies\" to it\n            JScrollPane pane = new JScrollPane(list); // create a scroll pane and add the list to it\n\n            panel.add(pane);\n            c.add(panel);\n            setSize(200, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(650);
				ex.setY(430);
				ex.setTitle("List Code");
				ex.Display();
			}
			
			// RADIO BUTTON
			else if(menuList.getSelectedValue() == "Radio Button"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel();\n\n            ButtonGroup group = new ButtonGroup(); // create a new button group\n            JRadioButton radio1 = new JRadioButton(\"Radio 1\", true); // create a new radio button and set it as the default option\n            JRadioButton radio2 = new JRadioButton(\"Radio 2\"); // create another radio button\n\n            group.add(radio1); // add radio1 to the button group\n            group.add(radio2); // add radio2 to the button group\n\n            panel.add(radio1);\n            panel.add(radio2);\n            c.add(panel);\n            setSize(200, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(810);
				ex.setY(490);
				ex.setTitle("Radio Button Code");
				ex.Display();
			}

			// CHECK BOX
			else if(menuList.getSelectedValue() == "Check Box"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n            JPanel panel = new JPanel();\n\n            JCheckBox checkbox1 = new JCheckBox(\"Check box 1\", true); // create a new check box and set it as the default option\n            JCheckBox checkbox2 = new JCheckBox(\"Check box 2\"); // create another check box\n\n            panel.add(checkbox1);\n            panel.add(checkbox2);\n            c.add(panel);\n            setSize(250, 100);\n            setVisible(true);\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(820);
				ex.setY(430);
				ex.setTitle("Check Box Code");
				ex.Display();
			}

			// LAYOUT MANAGER
			else if(menuList.getSelectedValue() == "Layout Manager"){
				Example ex = new Example();
				String code = "import java.awt.*;\nimport javax.swing.*;\n\npublic class exampleCode extends JFrame{\n\n      public exampleCode(){\n            super(\"My Frame\");\n            Container c = getContentPane();\n\n            c.add(new JButton(\"Button 1 (NORTH)\"),BorderLayout.NORTH);\n            c.add(new JButton(\"Button 2 (CENTER)\"),BorderLayout.CENTER);\n            c.add(new JButton(\"Button 3 (WEST)\"),BorderLayout.WEST);\n            c.add(new JButton(\"Button 4 (SOUTH)\"),BorderLayout.SOUTH);\n            c.add(new JButton(\"Button 5 (EAST)\"),BorderLayout.EAST);\n\n            setSize(400, 160);\n            setVisible(true);;\n      }\n\n      public static void main(String[] args){\n            exampleCode example = new exampleCode();\n      }\n}";
				ex.setCode(code);
				ex.setX(460);
				ex.setY(410);
				ex.setTitle("Layout Manager Code");
				ex.Display();
			}

			/******** CODE RE-DONE TO HERE ********/
			
			// EVENT WINDOW
			else if(menuList.getSelectedValue() == "Event Window"){
				Example ex = new Example();
				String code = "//IMPORTS \n import java.awt.event.ActionEvent; \n import java.awt.event.WindowEvent; \n\n//CLASSES \n public class WindowEventDemo extends JApplet implements ActionListener{ \n\n //ADD TO OBJECT \n MyWindowIsCreated.addWindowListener(this); \n\n //ACTUALLY PERFORM ACTION:\n public void actionPerformed(ActionEvent e) { \n if (e.getActionCommand() == SHOW) { \n  window.pack(); \n  window.setVisible(true); \n } else { \n display.setText(''); \n }";
				ex.setCode(code);
				ex.setX(450);
				ex.setY(350);
				ex.setTitle("Event Window Code");
				ex.Display();		
			}
		
		/* STILL NEED

			"Event Button"
			"Listener Button"
			"Listener Window"
		
		*/

		} // end if(source == menuList)
		
	} // end valueChanged

}
