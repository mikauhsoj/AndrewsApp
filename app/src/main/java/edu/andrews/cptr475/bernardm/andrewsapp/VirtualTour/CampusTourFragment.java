package edu.andrews.cptr475.bernardm.andrewsapp.VirtualTour;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import edu.andrews.cptr475.bernardm.andrewsapp.Map.MapsActivity;
import edu.andrews.cptr475.bernardm.andrewsapp.R;

/**
 * @author Bernardo Martinez
 * @version 0.1
 */

public class CampusTourFragment extends Fragment {

    private ViewFlipper mviewflipper;
    private Button maboutus;
    private Button mwebviewtour;
    Fragment objfragment = null;

    private void setFlipperImage(Bitmap iv) {
        //  Log.i("Set Flipper Called", res+"");
        ImageView image = new ImageView(getActivity().getApplicationContext());
        //image.setBackgroundResource(res);
        image.setImageBitmap(iv);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        mviewflipper.addView(image);
    }

    private void displayaboutuspage() {

        ///finished refactoring for projec on fragmetns setup..
        //   Intent i = new Intent(getActivity(), AboutUsActivity.class);
        //  getActivity().startActivity(i);


        objfragment = new AboutUsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objfragment)
                .commit();
    }


    private void displaywebviewtour() {
        Intent i = new Intent(getActivity(), VirtualTourFragment.class);
        getActivity().startActivity(i);
    }

    private void displaygooglemap() {
        /**    Create	intent	with	name	of	class	for	second	activity.
         This	intent	will	be	sent	to	the	Activity	Manager	in	the	OS
         which	will	launch	the	activity.*/
        Intent i = new Intent(getActivity(), MapsActivity.class);
        getActivity().startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_campus_tour, container, false);
        mviewflipper = (ViewFlipper) rootView.findViewById(R.id.FotosFlipper);
        int gallery_grid_Images[] = {R.drawable.globe, R.drawable.seminary};
        for (int i = 0; i < gallery_grid_Images.length; i++) {
            //  This will create dynamic image view and add them to ViewFlipper

            Bitmap bMap = BitmapFactory.decodeResource(getResources(), gallery_grid_Images[i]);
            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 500, 500, true);
            setFlipperImage(bMapScaled);
            mviewflipper.setInAnimation(container.getContext(), R.anim.abc_fade_in);
        }
        maboutus = (Button) rootView.findViewById(R.id.aboutus);
        maboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayaboutuspage();
            }

        });


        mwebviewtour = (Button) rootView.findViewById(R.id.starttour);
        mwebviewtour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // displaywebviewtour();
                Bundle args = new Bundle();
                args.putString("A", "Stour");
                displaygooglemap();
            }

        });

        return rootView;
    }
}

