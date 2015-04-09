package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;

import android.support.v4.app.Fragment;


public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return new PhotoGalleryFragment();
    }
}
