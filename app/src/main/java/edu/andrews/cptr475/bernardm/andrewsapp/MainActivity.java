package edu.andrews.cptr475.bernardm.andrewsapp;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import edu.andrews.cptr475.bernardm.andrewsapp.Dining.LocalDiningFragment;
import edu.andrews.cptr475.bernardm.andrewsapp.Directory.DirectoryFragment;
import edu.andrews.cptr475.bernardm.andrewsapp.Map.MapsActivity;
import edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.CampusNews;
import edu.andrews.cptr475.bernardm.andrewsapp.VirtualTour.CampusTourFragment;
import edu.andrews.cptr475.bernardm.andrewsapp.Weather.WeatherActivity;
/**
 * @author Bernardo Martinez
 * @version 0.1
 */
public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

//Mainactivity updated 3/27/2015
//Recycler
private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
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



       /*Navegation drawer inmplementation for the project*/
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.DrawerLayout));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objfragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> al = getSupportFragmentManager().getFragments();
        String Tag = "";
        switch (position) {
            case 0:
                //allocates the default fragment onto location 1
                objfragment = new PlaceholderFragment();
                Tag = "MainScreen";
                break;
            case 1:

                objfragment = new CampusTourFragment();
                //Tramsaction removes get rides of of the previous fragment, a better way needs to be researched
                transaction.remove(al.get(1));
                Tag = "CampusTour";
                break;
            case 2:
                objfragment = new LocalDiningFragment();
                transaction.remove(al.get(1));
                Tag = "LocalDining";
                break;

            //place holder is supposed to callled back the base class implementation..
            case 3:
                objfragment = new DirectoryFragment();
                transaction.hide(al.get(1));
                //  transaction.remove(al.get(1));
                Tag = "DirectoryFrag";
                break;
        }
        //transaction.remove();
        String tag = "";

        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        }

        if (tag != "") {
            transaction.addToBackStack(tag).replace(R.id.container, objfragment, Tag)
                    .commit();
        } else {
            transaction.addToBackStack(null).replace(R.id.container, objfragment, Tag)
                    .commit();
        }
    }

    /*
     This defines the section attached but is not what is diplayed on the screen, the display switch is
     allocated inside the NavegationDrawerFragment
     */
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.Home);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
    ///adress issue of overlaping maps inside of placeholder

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
        Fragment objfragment = null;

        FragmentManager fragmentManager = getFragmentManager();
        public PlaceholderFragment() {
        }

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
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

            objfragment = new CampusTourFragment();
            FragmentManager fragmentManager = getFragmentManager();
            // FragmentTransaction transaction= fragmentManager.beginTransaction();
            //List<Fragment> al = this.getSupportFragmentManager().getFragments();
            //transaction.remove(al.get(1));
            fragmentManager.beginTransaction()
                    .replace(R.id.container, objfragment, "CampusTour")
                    .commit();

            //  Intent i = new Intent(getActivity(), CampusTourActivity.class);
            //getActivity().startActivity(i);
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

            //   Intent i = new Intent(getActivity(), Calendar.class);
            ///  getActivity().startActivity(i);
        }

        //changes
        private void displaydirectory() {
            objfragment = new DirectoryFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, objfragment, "DirectoryFrag")
                    .commit();
        }

        private void displayweather() {
            Intent i = new Intent(getActivity(), WeatherActivity.class);
            getActivity().startActivity(i);
        }

        private void displaydining() {
            objfragment = new LocalDiningFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, objfragment, "LocalDining")
                    .commit();

            //   Intent i = new Intent(getActivity(), LocalDiningActivity.class);
            //   getActivity().startActivity(i);
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
