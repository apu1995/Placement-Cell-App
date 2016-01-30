package com.example.sony_vaio.placementapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by _000 on 4/17/2015.
 */
public class ContactFragment extends android.app.Fragment{
    ImageView b1, b2, b3;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        b1 = (ImageView) rootView.findViewById(R.id.call1);
        b2 = (ImageView) rootView.findViewById(R.id.call4);
        b3 = (ImageView) rootView.findViewById(R.id.call5);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+917597333722"));
                startActivity(callIntent);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+9179982078877"));
                startActivity(callIntent);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+918764029554"));
                startActivity(callIntent);

            }
        });


        return rootView;

    }
}
