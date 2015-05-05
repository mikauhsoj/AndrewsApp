package edu.andrews.cptr475.bernardm.andrewsapp.Directory;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

public class DirectoryFragment extends Fragment{
        ListView list;
        TextView fname;
        TextView minit;
        TextView lname;
        Button getDirectory;
        EditText searchDirectory;



        ArrayList<HashMap<String, String>> mDirectory = new ArrayList<HashMap<String, String>>();

        private String url;
        private static final String TAG_FNAME = "fname";
        private static final String TAG_MINIT = "minit";
        private static final String TAG_LNAME = "lname";
        private static final String TAG_USERNAME = "username";
        JSONArray directory = null;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = super.onCreateView(inflater, container, savedInstanceState);

            getActivity().setContentView(R.layout.fragment_directory);
            //mDirectory = new ArrayList<HashMap<String, String>>();

            searchDirectory = (EditText)getActivity().findViewById(R.id.searchEditText);
            searchDirectory.setHint("Enter name");

            searchDirectory.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        search();
                    }
                    return handled;
                }
            });

            getDirectory = (Button)getActivity().findViewById(R.id.searchButton);
            getDirectory.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ConnectivityManager cm = (ConnectivityManager)
                            getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (cm.getActiveNetworkInfo() != null) {
                        search();
                    } else {
                        Toast.makeText(getActivity(), "You are not connected to a network. Please connect then try again.", Toast.LENGTH_LONG).show();
                    }
                }
            });
            return v;
        }

        private void search(){
            url = "http://www.andrews.edu/directory/" + searchDirectory.getText().toString() + "/json";
            url = url.replace(" ","%20");
            try  {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "There was an error getting the directory.", Toast.LENGTH_LONG).show();
            }


            new JSONParse().execute();
        }

        private class JSONParse extends AsyncTask<String, String, JSONArray> {
            private ProgressDialog pDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                fname = (TextView)getActivity().findViewById(R.id.fnameTextView);
                minit = (TextView)getActivity().findViewById(R.id.minitTextView);
                lname = (TextView)getActivity().findViewById(R.id.lnameTextView);
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Getting Data ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected JSONArray doInBackground(String... args) {
                JsonParser jParser = new JsonParser();
                // Getting JSON from URL
                JSONArray json = jParser.getJSONfromURL(url);
                return json;
            }

            @Override
            protected void onPostExecute(JSONArray json) {
                pDialog.dismiss();
                directory = json;
                try {
                    // Getting JSON Array from URL
                    mDirectory.clear();
                    for(int i = 0; i < directory.length(); i++){
                        JSONObject c = directory.getJSONObject(i);
                        // Storing  JSON item in a Variable
                        String fname = c.getString("f");
                        String minit = c.getString("m");
                        String lname = c.getString("l");
                        String username = c.getString("u");
                        // Adding value HashMap key => value
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_FNAME, fname);
                        map.put(TAG_LNAME, lname);
                        map.put(TAG_MINIT, minit);
                        map.put(TAG_USERNAME, username);
                        mDirectory.add(map);
                        list = (ListView)getActivity().findViewById(R.id.directoryListView);
                        ListAdapter adapter = new SimpleAdapter(getActivity(), mDirectory,
                                R.layout.list_item_directory, new String[] { TAG_FNAME, TAG_MINIT, TAG_LNAME }, new int[] {R.id.fnameTextView, R.id.minitTextView, R.id.lnameTextView});
                        list.setAdapter(adapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Toast.makeText(getActivity(), "Email: " + mDirectory.get(+position).get("username") + "@andrews.edu", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (directory.length() == 0) {
                    list.setAdapter(null);
                    Toast.makeText(getActivity(), "No results.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

