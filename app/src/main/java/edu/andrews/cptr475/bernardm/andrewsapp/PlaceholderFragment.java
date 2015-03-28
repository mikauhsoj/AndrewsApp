package edu.andrews.cptr475.bernardm.andrewsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

/**
 * This is part of the maps class and is a work on progress.
 * tks
 */
public class PlaceholderFragment extends Fragment {

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
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTitle().equals("MeierHall")) {// if marker equals meier hall
                    Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                    TextView frv = (TextView) getFragmentManager().findFragmentById(R.id.container).getView().findViewById(R.id.largetextmed);
                    TextView frv2 = (TextView) getFragmentManager().findFragmentById(R.id.container).getView().findViewById(R.id.mainbodymapstxt);

                    frv.setText("Meier Hall MH");
                    frv2.setText("During your stay at Meier Hall you'll be expected to adhere to guidelines that were designed to benefit you, " +
                            "and to preserve and bolster our mission along with the message " +

                            "and lifestyle of the Seventh-day Adventist church. Please regard them with mature and responsible attitudes.");
                }
                if (marker.getTitle().equals("University_Towers")) {// if marker equals meier hall\

                    Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                    TextView frv3 = (TextView) getFragmentManager().findFragmentById(R.id.container).getView().findViewById(R.id.largetextmed);
                    TextView frv4 = (TextView) getFragmentManager().findFragmentById(R.id.container).getView().findViewById(R.id.mainbodymapstxt);

                    frv3.setText("University Towers UT");
                    frv4.setText("University Towers is comprised of Burman Hall, the Andrews University residence " +
                            "hall for graduate aged men, constructed in 1981, and Damazo Hall, the Andrews University residence hall for graduate aged women, " +
                            "constructed in 2011. Besides integrated common areas, " +
                            "they also share a large lobby at the entrance to the facility. There students from both buildings may watch TV, study or visit.");
                }
                return false;
            }
        });


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

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
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
            //getFragmentManager().findFragmentById(R.layout.fragment_maps)

            mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            //         .getMap();
            // mMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
            // mMap =  ((SupportMapFragment)getFragmentManager().findFragmentById(R.layout.fragment_maps)).getMap();
            // mMap =  ((SupportMapFragment) getView().findViewById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        setUpMapIfNeeded();
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        //  CustomMapFragment mapFragment = new CustomMapFragment();
        //FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // transaction.add(R.id.map_container, mapFragment).commit();
        return rootView;
        //
        //changes the map from street to view to satellite.


    }

}
