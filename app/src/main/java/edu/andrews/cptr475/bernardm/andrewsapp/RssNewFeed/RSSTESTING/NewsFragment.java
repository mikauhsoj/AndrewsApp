package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

import static edu.andrews.cptr475.bernardm.andrewsapp.R.id.listMainView;

public class NewsFragment extends ListFragment {
    ListView mListView;
    //GridView mGridView;
    ArrayList<NewsItem> mItems;
    /** Create an image view containing  the photo for each photo in the gallery */
    private class GalleryItemAdapter extends ArrayAdapter<NewsItem> {
        public GalleryItemAdapter(ArrayList<NewsItem> items) {
            super(getActivity(), 0, items);
        }

        /**
         * Setup the view for a News items at a given position in an array list
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                //There is now existing view to recycle,
                //Create a new one by inflating the gallery item layout
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_news_test, parent, false);
            }
            //Specify the image for the image view
            TextView textView = (TextView) convertView.findViewById(listMainView);
            textView.setText(getText(R.layout.fragment_news_test));
            return convertView;
        }
    }
    /** Asynchronous task responsible for downloading items from  Andrews Agenda */
    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<NewsItem>> {
        @Override
        protected ArrayList<NewsItem> doInBackground(Void... params){
            //Collections.reverse(mItems);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_test, container, false);
//        mListView.setAdapter((android.widget.ListAdapter) mItems);
        //System.out.print(mListView.setSelection(1));
        mListView = (ListView)v.findViewById(listMainView);
        setupAdapter();
        
        return v;
    }
    
    void setupAdapter() {
        if (getActivity() == null || mListView == null) return;
        
        if (mItems != null) {
            //There are news items, user our own Adapter to generate the views.
            mListView.setAdapter(new GalleryItemAdapter(mItems));
        } else {
            mListView.setAdapter(null);
        }
    }


}
