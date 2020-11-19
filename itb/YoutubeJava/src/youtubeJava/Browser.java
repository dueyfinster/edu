package youtubeJava;

// Source code of demo application SimpleBrowser.
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.net.MalformedURLException;
import org.jdesktop.jdic.browser.*;

public class Browser
{
    public Browser(String passURL, String title)
    {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(450,0,0,0);

        WebBrowser webBrowser = new WebBrowser();

        //Use below code to check the status of the navigation process,
        //or register a listener for the notification events.
        webBrowser.addWebBrowserListener
        (
            new WebBrowserListener() 
            {
            public void downloadStarted(WebBrowserEvent event) {;}
            public void downloadCompleted(WebBrowserEvent event) {;}
            public void downloadProgress(WebBrowserEvent event) {;}
            public void downloadError(WebBrowserEvent event) {;}
            public void documentCompleted(WebBrowserEvent event) {;}
            public void titleChange(WebBrowserEvent event) {;}
            public void statusTextChange(WebBrowserEvent event) {;}
			public void initializationCompleted(WebBrowserEvent arg0) {
			}
			@Override
			public void windowClose(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
            }
        );

        try
        {
            webBrowser.setURL(new URL(passURL));
        }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(585, 420));
        panel.add(webBrowser, BorderLayout.CENTER);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
