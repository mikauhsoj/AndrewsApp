package edu.andrews.cptr475.bernardm.andrewsapp.Dining;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

public class LocalDiningFragment extends Fragment {

        ListView yelpList;
        TextView businessName;
        ArrayList<String> yelpArray;

        ArrayList<HashMap<String, String>> mBusiness = new ArrayList<HashMap<String, String>>();

        private static final String TAG_BNAME = "bname";
        private static final String TAG_RATING = "rating";
        private static final String TAG_LOCAT = "location";
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_local_dining, container, false);


            ConnectivityManager cm = (ConnectivityManager)
                    getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null) {
                new GetRestaurants().execute();
            } else {
                Toast.makeText(getActivity(), "You are not connected to a network. Please connect then try again.", Toast.LENGTH_LONG).show();
            }

            return rootView;
        }

        class GetRestaurants extends AsyncTask<Void, Integer, ArrayList<String>> {
            private Exception exception;

            protected void onPreExecute() {
                businessName = (TextView) getActivity().findViewById(R.id.businessNameTextView);
            }

            @Override
            protected ArrayList<String> doInBackground(Void... params) {
                //API_Static_Stuff api_keys = new API_Static_Stuff();

                //Yelp yelp = new Yelp(api_keys.getYelpConsumerKey(), api_keys.getYelpConsumerSecret(),
                //        api_keys.getYelpToken(), api_keys.getYelpTokenSecret());
                Yelp yelp = new Yelp(getResources().getString(R.string.yelp_consumer_key),
                        getResources().getString(R.string.yelp_consumer_secret),
                        getResources().getString(R.string.yelp_token),
                        getResources().getString(R.string.yelp_token_secret));
                String response = yelp.search("restaurant", 41.964473, -86.360150, 1);

                //"food", "Berrien+Springs"
                YelpParser yParser = new YelpParser();
                yParser.setResponse(response);
                try {
                    yParser.parseBusiness();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    //Do whatever you want with the error, like throw a Toast error report
                    Toast.makeText(getActivity(), "There was an error.", Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; yParser.getBundleKeysSize() > i; i++) {

                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(TAG_BNAME, yParser.getBusinessName(i));
                    map.put(TAG_RATING, yParser.getRating(i));
                    map.put(TAG_LOCAT, yParser.getBusinessMobileURL(i));

                    mBusiness.add(map);

                    //Bundle tmpBundle = yParser.getYelpBundle();


                }
                ArrayList<String> tmpKeys = yParser.getBundleKeys();

                return tmpKeys;

            }

            @Override
            protected void onPostExecute(ArrayList<String> array) {
                yelpArray = array;

                yelpList = (ListView) getActivity().findViewById(R.id.localDiningListView);

                ListAdapter adapter = new SimpleAdapter(getActivity(), mBusiness,
                        R.layout.list_item_local_dining, new String[] {TAG_BNAME, TAG_RATING}
                , new int[] {R.id.businessNameTextView, R.id.bRatingBar});

                ((SimpleAdapter) adapter).setViewBinder(new MyBinder());

                yelpList.setAdapter(adapter);


                yelpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        Intent browserIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse(mBusiness.get(+position).get("location")));
                        startActivity(browserIntent);
                    }
                });
            }
        }
    }
class MyBinder implements SimpleAdapter.ViewBinder {
    public boolean setViewValue(View view, Object data, String textRepresentation) {
        if (view.getId() == R.id.bRatingBar) {

            String stringval = (String) data;
            float ratingValue = Float.parseFloat(stringval);
            RatingBar ratingBar = (RatingBar) view;
            ratingBar.setRating(ratingValue);
            return true;
        }
        return false;
    }
}