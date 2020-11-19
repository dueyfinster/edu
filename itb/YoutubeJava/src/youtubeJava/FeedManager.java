package youtubeJava;

//Required Java imports
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//Required gdata imports
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;


public class FeedManager
{
	private String[] titles4List = new String[25];
	private String[] ids4List = new String[25];
	/**
	 * FeedManager takes a string of feedURL example of a url http://gdata.youtube.com/feeds/api/standardfeeds/top_rated for top rated feeds
	 * @param feedUrl
	 */
	public FeedManager(String feedUrl)
	{
		/**
		 * New Youtube service contains developer id and key
		 */
		YouTubeService service = new YouTubeService("ytapi-MarkHoward-JavaSwingYoutube-3lgf9m38-0", "AI39si78qpyO0ri7bZTZytIpAwP0PBwCdVwjE6qUcAoL8ROSWDyGPewJQoH3Yu_47ll0y1I9oZG7xK5XupH-S0t8fNjZZn1sGw");
		
		/** 
	     * Start of Try-Catch clause just in case video feed is unsuccessful
	     */
		try 
		{
			VideoFeed videoFeed = service.getFeed(new URL(feedUrl), VideoFeed.class);
			/**
			 * When the videofeed entry has been populated it is then passed to display entries to populate a text area
			 */
			DisplayEntries(videoFeed);
		
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ServiceException e) 
		{
			e.printStackTrace();
		}
	
		
	}
	
	
	/** 
     * Get a list of the Video ID's and Names; and split them into lists.
     */
	public void DisplayEntries(VideoFeed videoFeed)
	{
		String[] videoTitles = new String[25];
		String[] videoIDs = new String[25];
		
		int i=0;

		for(VideoEntry videoEntry:videoFeed.getEntries())
		{
			collectEntries(videoEntry, i, videoTitles, videoIDs);
			i++;
		}
		titles4List = videoTitles;
		ids4List = videoIDs;
		
		
	}
	
	/** 
     * Collection of Video entries with split ID's and Titles.
     */
	public void collectEntries(VideoEntry videoEntry, int i, String[] videoTitles, String[] videoIDs)
	{
		videoTitles[i]=videoEntry.getTitle().getPlainText();
		videoIDs[i]=videoEntry.getId().substring(27, 38);
	}

	/** 
     * A Return to get the Titles (of Videos) list
     */
	public String[] getListValues()
	{
		return titles4List;
	}

	/** 
     * A Return to get the ID list (of Videos)
     */
	public String[] getListIDs()
	{
		return ids4List;
	}



    
	
}
