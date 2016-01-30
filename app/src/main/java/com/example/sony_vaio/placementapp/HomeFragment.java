package com.example.sony_vaio.placementapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sony_vaio on 4/7/2015.
 */
public class HomeFragment extends android.app.Fragment {

    private ImageView pic;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        pic = (ImageView)rootView.findViewById(R.id.imageView);
        pic.setImageResource(R.drawable.directorphoto);

        return rootView;



    }
}