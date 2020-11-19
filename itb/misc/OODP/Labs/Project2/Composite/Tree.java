package Project2.Composite;

/**
 * A 1.4 application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.ImageIcon;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;

public class Tree extends JPanel implements TreeSelectionListener {
    private JEditorPane Pane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = true;

    public Tree() {
        super(new GridLayout(1,0));

        //Create top of the tree
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Produce");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);


        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        Pane = new JEditorPane();
        Pane.setEditable(false);
        JScrollPane htmlView = new JScrollPane(Pane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            Produce prod = (Produce)nodeInfo;
            if (DEBUG) {
                System.out.print(prod.toString());
            }
        } else {
        	Produce prod = (Produce)nodeInfo;
        	displayProduce(prod.toString()); 
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private void displayProduce(String name) {
        try {
            if (name != null) {
                Pane.setPage(name);
            } else { //null name
		Pane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display an Empty Produce.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read an invalid Produce name: " + name);
        }
    }

    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode produce;
        DefaultMutableTreeNode veg;
        DefaultMutableTreeNode liquid;
        DefaultMutableTreeNode juices;
        DefaultMutableTreeNode alcohol;
        DefaultMutableTreeNode meat;

        /////////////////////////////////
        // VEGTABLES
        /////////////////////////////////
        
        produce = new DefaultMutableTreeNode("Vegtables");
        top.add(produce);

        veg = new DefaultMutableTreeNode(new Produce("Root", 10));
        produce.add(veg);

        veg = new DefaultMutableTreeNode(new Produce("Tuber", 10));
        produce.add(veg);

        veg = new DefaultMutableTreeNode(new Produce("Stalk", 50));
        produce.add(veg);
        
        /////////////////////////////////
        // LIQUIDS
        /////////////////////////////////

        produce = new DefaultMutableTreeNode("Liquids");
        top.add(produce);
        
        /*
         * Juices
         */
        liquid = new DefaultMutableTreeNode("Juices");
        produce.add(liquid);
        
        juices = new DefaultMutableTreeNode(new Produce("Apple", 50));
        liquid.add(juices);
        
        juices = new DefaultMutableTreeNode(new Produce("Orange", 25));
        liquid.add(juices);
        
        juices = new DefaultMutableTreeNode(new Produce("Cranberry", 25));
        liquid.add(juices);
        
        /*
         * Alcohol
         */
        liquid = new DefaultMutableTreeNode("Alcohol");
        produce.add(liquid);
        
        alcohol = new DefaultMutableTreeNode(new Produce("Liquor", 35));
        liquid.add(alcohol);
        
        alcohol = new DefaultMutableTreeNode(new Produce("Wine", 65));
        liquid.add(alcohol);
        
        alcohol = new DefaultMutableTreeNode(new Produce("Beer", 100));
        liquid.add(alcohol);
        
        /////////////////////////////////
        // Meat
        /////////////////////////////////
        
        produce = new DefaultMutableTreeNode("Meat");
        top.add(produce);
        
        meat = new DefaultMutableTreeNode(new Produce("Poultry", 35));
        produce.add(meat);
        
        meat = new DefaultMutableTreeNode(new Produce("Beef", 65));
        produce.add(meat);
        
        meat = new DefaultMutableTreeNode(new Produce("Pork", 100));
        produce.add(meat);
        
        meat = new DefaultMutableTreeNode(new Produce("Lamb", 100));
        produce.add(meat);
    }
}

