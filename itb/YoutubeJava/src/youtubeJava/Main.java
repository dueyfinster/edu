	/** 
     * YouTube Java Default Package
     */
package youtubeJava;

/** 
 * Import our GUI package
 */
import gui.UI;

public class Main {

	/** 
     * Main class which creates the GUI and sets the program in motion.
     */
	public static void main(String[] args)
    {
        //new Browser("http://www.d1004748.cp.blacknight.com/phpsandbox/youtube.php?id=Tb12nQEOyfM");
		/** 
	     * Create new instance of the UI and set it to be visible.
	     */
		new UI().setVisible(true);

	}

}
