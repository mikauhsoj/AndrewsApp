package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;

/** Model information about an image in a photo gallery */
public class NewsItem {
    private String mTitle;
    private String mDescription;
    private String mLink;

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

    public void setUrl(String link) {
        mLink = link;
    }

    public String toString() {
        return mTitle;
    }
}
