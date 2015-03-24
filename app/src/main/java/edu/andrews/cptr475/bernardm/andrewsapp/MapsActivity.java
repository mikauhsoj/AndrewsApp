package edu.andrews.cptr475.bernardm.andrewsapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        //changes the map from street to view to satellite.
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
      //  UrlTileProvider  urlTileProvider= new UrlTileProvide;
       // urlTileProvider.getTileUrl("https://www.andrews.edu/virtualtour/tiles/google-format/5/17/11.jpg");
    }
//https://www.andrews.edu/virtualtour/tiles/google-format/5/17/11.jpg
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        final String[][] arraymapsr = {{"Andrews University", "41.962708", "-86.359760"},
                {"Andrews Airpark", "41.950541", "-86.365176"},
                {"Andrews DairyFarm", "41.972163", " -86.361965"},
                {"Lamson Hall", "41.963363", "-86.36034"},
                {"MeierHall", "41.965497", " -86.361667"},
                {"University_Towers", "41.966215", "-86.363742"}};


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.962708, -86.359760), 13.0f));
        if (mMap.getCameraPosition().zoom == 13) {
            for (int i = 0; i < 3; i++) {

                mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(arraymapsr[i][1]), Double.parseDouble(arraymapsr[i][2]))).title(arraymapsr[i][0]));
                //  mMap.addMarker(new MarkerOptions().position(new LatLng(41.965497, -86.361667)).title("MeierHall"));
                //  mMap.addMarker(new MarkerOptions().position(new LatLng(41.966215, -86.363742)).title("University_Towers"));
            }
        }

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (cameraPosition.zoom > 15) {
                    for (int i = 3; i < 6; i++) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(arraymapsr[i][1]), Double.parseDouble(arraymapsr[i][2]))).title(arraymapsr[i][0]));
                        //  mMap.addMarker(new MarkerOptions().position(new LatLng(41.965497, -86.361667)).title("MeierHall")).setVisible(cameraPosition.zoom > 15);
                        //  mMap.addMarker(new MarkerOptions().position(new LatLng(41.966215, -86.363742)).title("University_Towers")).setVisible(cameraPosition.zoom > 15);
                    }


                }
                /* TODO implement a way to remove markers */
                if (cameraPosition.zoom < 12) {
                    for (int i = 3; i < 6; i++) {

                        mMap.clear();


                        // mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(arraymapsr[i][1]), Double.parseDouble(arraymapsr[i][2]))).title(arraymapsr[i][0])).setVisible(false);
                        //  mMap.addMarker(new MarkerOptions().position(new LatLng(41.965497, -86.361667)).title("MeierHall")).setVisible(cameraPosition.zoom > 15);
                        //  mMap.addMarker(new MarkerOptions().position(new LatLng(41.966215, -86.363742)).title("University_Towers")).setVisible(cameraPosition.zoom > 15);
                    }

                }
            }
        });
    }
}
