package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;

import java.text.MessageFormat;

/** Model information about an image in a photo gallery */
public class  NewsItem {
    private String mTitle;
    private String mDescription;
    private String mLink;
    private String mpubDate;

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    
    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getPubDate() {
        return mpubDate;
    }

    public void setPubDate(String pubDate) {
        mpubDate = pubDate;
    }

    public String toString() {
        return MessageFormat.format("{0},{1},{2}", mTitle, mpubDate, mDescription);
        //return mTitle+ ","+mpubDate+ ","+ mDescription ;
    }
}
