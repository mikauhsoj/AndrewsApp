package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

public class NewsFragment extends Fragment {
    ListView mListView;
    //GridView mGridView;
    ArrayList<NewsItem> mItems;
    /** Create an image view containing  the photo for each photo in the gallery */
    private class GalleryItemAdapter extends ArrayAdapter<NewsItem> {
        public GalleryItemAdapter(ArrayList<NewsItem> items) {
            super(getActivity(), 0, items);
        }

        /**
         * Setup the view for a gallery items at a given position in an array list
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                //There is now existing view to recycle,
                //Create a new one by inflating the gallery item layout
                convertView = getActivity().getLayoutInflater().inflate(R.layout.gallery_item, parent, false);
            }
            //Specify the image for the image view
            ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_items_imageView);
            imageView.setImageResource(android.R.drawable.ic_menu_gallery);
            return convertView;
        }
    }
    /** Asynchronous task responsible for downloading items from flickr */
    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<NewsItem>> {
        @Override
        protected ArrayList<NewsItem> doInBackground(Void... params){
            return new NewsFetchr().fetchItems();
        }
        @Override
        protected void onPostExecute(ArrayList<NewsItem> items){
            mItems = items;
            setupAdapter();
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setRetainInstance(true);
        //Start AsyncTask running
        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_test, container, false);
        
        mListView = (ListView)v.findViewById(R.id.listView);
        setupAdapter();
        
        return v;
    }
    
    void setupAdapter() {
        if (getActivity() == null || mListView == null) return;
        
        if (mItems != null) {
            //There are gallery items, user our own Adapter to generate the views.
            mListView.setAdapter(new GalleryItemAdapter(mItems));
        } else {
            mListView.setAdapter(null);
        }
    }


}
