package edu.andrews.cptr475.bernardm.andrewsapp.Dining;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import edu.andrews.cptr475.bernardm.andrewsapp.API_Static_Stuff;
import edu.andrews.cptr475.bernardm.andrewsapp.R;

public class LocalDiningActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_dining);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local_dining, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ListView yelpList;
        TextView businessName;
        ArrayList<String> yelpArray;



        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_local_dining, container, false);


            new GetRestaurants().execute();





            return rootView;
        }

        class GetRestaurants extends AsyncTask<Void, Integer, ArrayList<String>> {
            private Exception exception;

            protected void onPreExecute() {
                businessName = (TextView)getActivity().findViewById(R.id.businessNameTextView);
            }

            @Override
            protected ArrayList<String> doInBackground(Void... params) {
                API_Static_Stuff api_keys = new API_Static_Stuff();

                Yelp yelp = new Yelp(api_keys.getYelpConsumerKey(), api_keys.getYelpConsumerSecret(),
                        api_keys.getYelpToken(), api_keys.getYelpTokenSecret());
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

                int i = 0;
                String mobile_url = yParser.getBusinessMobileURL(i);
                String rating_url = yParser.getRatingURL(i);
                String b_name = yParser.getBusinessName(i);
                Bundle tmpBundle = yParser.getYelpBundle();
                ArrayList<String> tmpKeys = yParser.getBundleKeys();

                return tmpKeys;

            }

            @Override
            protected void onPostExecute(ArrayList<String> array) {
                yelpArray = array;

                yelpList = (ListView) getActivity().findViewById(R.id.localDiningListView);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.yelp_list_item,
                        R.id.businessNameTextView,
                        yelpArray);
                yelpList.setAdapter(adapter);

            }

        }
    }
}


