package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;


public class NewsActivity extends SingleFragmentActivity {

    @Override
    public NewsFragment createFragment(){
        return new NewsFragment();
    }
}
