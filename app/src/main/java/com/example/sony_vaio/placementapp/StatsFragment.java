package com.example.sony_vaio.placementapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sony_vaio on 4/9/2015.
 */


public class StatsFragment extends android.app.Fragment
{   private ImageView pic1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stats, container, false);

        return rootView;
    }
}
