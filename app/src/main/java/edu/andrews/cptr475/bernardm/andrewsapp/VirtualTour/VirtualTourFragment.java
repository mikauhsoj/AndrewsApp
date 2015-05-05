package edu.andrews.cptr475.bernardm.andrewsapp.VirtualTour;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

/**
 * @author Bernardo Martinez
 * @version 0.1
 */
public class VirtualTourFragment extends Fragment {



        private WebView mview;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_virtual_tour, container, false);
            String url = "https://www.andrews.edu/virtualtour/#/";
            mview = (WebView) rootView.findViewById(R.id.andrewsTourWebView);
            mview.getSettings().setJavaScriptEnabled(true);
            mview.loadUrl(url);
            return rootView;
        }
    }

