package edu.andrews.cptr475.bernardm.andrewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import edu.andrews.cptr475.bernardm.andrewsapp.CalendaRSS.Calendar;
import edu.andrews.cptr475.bernardm.andrewsapp.Dining.LocalDiningActivity;
import edu.andrews.cptr475.bernardm.andrewsapp.Directory.DirectoryActivity;
import edu.andrews.cptr475.bernardm.andrewsapp.Map.MapsActivity;
import edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.CampusNews;
import edu.andrews.cptr475.bernardm.andrewsapp.VirtualTour.CampusTourActivity;
import edu.andrews.cptr475.bernardm.andrewsapp.Weather.WeatherActivity;
/**
 * @author Bernardo Martinez
 * @version 0.1
 */
public class MainActivity extends ActionBarActivity {

//Mainactivity updated 3/27/2015
//Recycler
    // <meta-data android:name="com.google.android.maps.v2.API_KEY" android:value="@string/google_maps_key"/>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Actionbar -> trying to get logo to show
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.au_logo);
        actionBar.setTitle("");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        // Declaring Layout Manager as a linear layout manager
        RecyclerView.LayoutManager mLayoutManager;
        // Declaring DrawerLayout
        DrawerLayout Drawer = null;
        // Declaring Action Bar Drawer Toggle
        ActionBarDrawerToggle mDrawerToggle;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

/*
        // Assigning the RecyclerView Object to the xml View
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        // Letting the system know that the list objects are of fixed size
        mRecyclerView.setHasFixedSize(true);
        // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
        // Attaching the layout to the toolbar object
        appbar = (Toolbar) findViewById(R.id.app_bar);
        // Setting our toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(appbar);
        appbar.setLogo(R.drawable.au_logo_sm);


        mAdapter=null;
       // mAdapter = new NavDrawerAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE,this);
        // Setting the adapter to RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();
                    Toast.makeText(MainActivity.this,"The Item Clicked is: "+recyclerView.getChildPosition(child),Toast.LENGTH_SHORT).show();
                    return true;

                }
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            }
        });

        // Creating a layout Manager
        mLayoutManager = new LinearLayoutManager(this);
        // Setting the layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Drawer object Assigned to the view
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,appbar,"Open","Close"){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /** Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.*/
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

        private Button mgooglemaps;
        private Button mselfguidedtour;
        private Button mnewsrss;
        private Button mcalendar;
        private Button mdirectory;
        private Button mweather;
        private Button mdining;

        public PlaceholderFragment() {
        }

        /**
         * Launch	activity	to	display	author	fact
         */
        private void displaygooglemap() {
            /**    Create	intent	with	name	of	class	for	second	activity.
             This	intent	will	be	sent	to	the	Activity	Manager	in	the	OS
             which	will	launch	the	activity.*/
            Intent i = new Intent(getActivity(), MapsActivity.class);
            getActivity().startActivity(i);
        }

        private void displayselftourguide() {

            Intent i = new Intent(getActivity(), CampusTourActivity.class);
            getActivity().startActivity(i);
        }

        /**
         * Implement when fix NewsActivity.
         */
        /*  private void displayrssnews() {

            Intent i = new Intent(getActivity(), NewsActivity.class);
            getActivity().startActivity(i);
        }*/
        private void displayrssnews() {

            Intent i = new Intent(getActivity(), CampusNews.class);
            getActivity().startActivity(i);
        }

        private void displayrcalendar() {

            Intent i = new Intent(getActivity(), Calendar.class);
            getActivity().startActivity(i);
        }

        //changes
        private void displaydirectory() {
            Intent i = new Intent(getActivity(), DirectoryActivity.class);
            getActivity().startActivity(i);
        }

        private void displayweather() {
            Intent i = new Intent(getActivity(), WeatherActivity.class);
            getActivity().startActivity(i);
        }

        private void displaydining() {
            Intent i = new Intent(getActivity(), LocalDiningActivity.class);
            getActivity().startActivity(i);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mgooglemaps = (Button) rootView.findViewById(R.id.maps_button);
            mgooglemaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displaygooglemap();
                }

            });

            mselfguidedtour = (Button) rootView.findViewById(R.id.tour_button);
            mselfguidedtour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayselftourguide();
                }

            });

            mnewsrss = (Button) rootView.findViewById(R.id.news_button);
            mnewsrss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayrssnews();
                }
            });


            ///Calendar feature still needs to be properly implemented.
           /* mcalendar = (Button) rootView.findViewById(R.id.calendar_button);
            mcalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayrcalendar();
                }
            });*/

            mdirectory = (Button) rootView.findViewById(R.id.directory_button);
            mdirectory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displaydirectory();
                }
            });

            mweather = (Button) rootView.findViewById(R.id.weather_button);
            mweather.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayweather();
                }
            });

            mdining = (Button) rootView.findViewById(R.id.food_button);
            mdining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displaydining();
                }
            });

           /* code to create a banner for comments
            AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            return rootView;
        }
    }
}
