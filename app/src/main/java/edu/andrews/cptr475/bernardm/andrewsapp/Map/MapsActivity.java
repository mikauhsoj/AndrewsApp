package edu.andrews.cptr475.bernardm.andrewsapp.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

/**
 * @author Bernardo Martinez
 * @version 0.1
 */
public class MapsActivity extends FragmentActivity {

    // private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        //changes the map from street to view to satellite.

        //  UrlTileProvider  urlTileProvider= new UrlTileProvide;
        // urlTileProvider.getTileUrl("https://www.andrews.edu/virtualtour/tiles/google-format/5/17/11.jpg");
    }
    //https://www.andrews.edu/virtualtour/tiles/google-format/5/17/11.jpg
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

}

