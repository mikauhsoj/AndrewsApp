package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;

import android.net.Uri;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class NewsFetchr {
    /** Log message tag */
    public static final String TAG = "NewsFetcher";
    /** URL for flickr API */
    private static final String ENDPOINT = "https://www.andrews.edu/agenda/category/Campus+News/limit/15/rss";
    /** name of news tag in XML for the news story information */
    private static final String XML_ITEM = "item";

    /**
     * Request new articles from Andrews Agenda.
     * @return list of news items(images) download from Andrews Agenda.
     */
    public ArrayList<NewsItem> fetchItems(){
        ArrayList<NewsItem> items = new ArrayList<NewsItem>();
        try{
            String url = Uri.parse(ENDPOINT).buildUpon().build().toString();
            //Submit query to Andrews Agenda and download resulting information
            String xmlString = getUrl(url);
            Log.i(TAG, "Received xml: \n" + xmlString);
            //Create an instance of the xml parser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));

            //parse the xml photo elements
            //and create gallery items for each one
            parseItems(items, parser);
        } catch (IOException ioe){
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (XmlPullParserException xppe){
            Log.e(TAG, "Failed to parse items", xppe);
        }
        return items;
    }
    /**
     * Parse Photo elements in an XML string one-by-one
     * @param items List of GalleryItems to add to.
     *              Each photo elements result in a new NewsItem
     *              that is added to the list
     * @param parser XML parser object initialized with string to parse
     * @throws org.xmlpull.v1.XmlPullParserException
     * @throws java.io.IOException
     */
    void parseItems(ArrayList<NewsItem> items, XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        // Keep parsing photo elements and converting them to Gallery items until we reach the end of the XML string.
        while (eventType != XmlPullParser.END_DOCUMENT){
                if (eventType == XmlPullParser.START_TAG && XML_ITEM.equals(parser.getName())){
                //Extract image Id
                String title = parser.getAttributeValue(null, "title");
                //Extract image Title
                String description = parser.getAttributeValue(null, "description");
                //Extract url for image Thumbnail
                String pubDate = parser.getAttributeValue(null, "pubDate");

                //Create new Gallery item for image
                NewsItem item = new NewsItem();
                item.setTitle(title);
                item.setDescription(description);
                item.setPubDate(pubDate);
                items.add(item);

            }
            //move to next xml element in the file.
            eventType = parser.next();

        }
    }
     /**
     * Retrieve raw data from a given URL and return it as an array of bytes.
     * @param urlSpec String specifying the URL to access.
     * @return Array of bytes from given URL
     * @throws java.io.IOException
     */
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        // Setup a connection object, but don't connect until getInputStream called
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // Establish a connection
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            // read 1K of bytes from input stream until nothing left to read.
            // write each set of bytes to the output byte array
            int bytesRead = 0;
            byte[] buffer = new byte[2048];
            while ((bytesRead = in.read(buffer))> 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    /**
     * Retrieve raw data from a given URL and return it as a String.
     * @param urlSpec String specifying the URL to access.
     * @return String from given URL
     * @throws java.io.IOException
     */
    String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }
    

}
