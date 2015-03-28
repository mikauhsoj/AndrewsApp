package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * SAX tag handler
 *
 * @author Shemaiah Telemaque
 * @version 0.1
 */
public class RssParserHandler extends DefaultHandler {
    /**
     * Arrays
     *
     * @param rssItems Stores xml download.
     */
    private List<RssItem> rssItems;
    /**
     * References
     *
     * @param currentItem used to reference item while parsing
     */

    private RssItem currentItem;
    /**
     * Declaring parsing indicators
     *
     * @param parsingTitle Parsing title for article title.
     * @param parsingLink Parsing the link to the article.
     * @param parsingPubdate Parsing the date the story was published.
     * @param parsingDescription Parsing the contents of the story.
     */
    private boolean parsingTitle;
    private boolean parsingLink;
    private boolean parsingPubdate;
    private boolean parsingDescription;

    public RssParserHandler() {
        rssItems = new ArrayList<RssItem>();
    }

    public List<RssItem> getItems() {
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RssItem();
        } else if ("title".equals(qName)) {
            parsingTitle = true;
        } else if ("link".equals(qName)) {
            parsingLink = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;
        } else if ("link".equals(qName)) {
            parsingLink = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }
        } else if (parsingPubdate) {
            if (currentItem != null) {
                currentItem.setPubdate(new String(ch, start, length));
                parsingPubdate = false;
            }
        } else if (parsingDescription) {
            if (currentItem != null) {
                currentItem.setPubdate(new String(ch, start, length));
                parsingDescription = false;
            }
        }
    }

}
