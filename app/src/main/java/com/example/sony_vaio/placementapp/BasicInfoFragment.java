package com.example.sony_vaio.placementapp;

/**
 * Created by SONY_VAIO on 29-03-2015.
 */

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BasicInfoFragment extends Fragment {


    private String URL = "";
    private static final String TAG_NAME = "name";
    private static final String TAG_ROLL_NO = "roll_no";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_DOB = "dob";
    private static final String TAG_FATHER_NAME = "father_name";
    private static final String TAG_MOTHER_NAME = "mother_name";
    private static final String TAG_EMAIL = "email";

    private String username;
    private ProgressBar spinner;
    private TextView name;
    private TextView roll_no;
    private TextView gender;
    private TextView dob;
    private TextView father_name;
    private TextView mother_name;
    private TextView email;

    public BasicInfoFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        username=getArguments().getString("username");

        View rootView = inflater.inflate(R.layout.fragment_basicinfo, container, false);


        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar1);

        spinner.setVisibility(View.VISIBLE);

        name = (TextView) rootView.findViewById(R.id.txtDisplayName);
        roll_no = (TextView) rootView.findViewById(R.id.txtDisplayRoll_No);
        gender = (TextView) rootView.findViewById(R.id.txtDisplayGender);
        dob = (TextView) rootView.findViewById(R.id.txtDisplayDob);
        father_name = (TextView) rootView.findViewById(R.id.txtDisplayFather_name);
        mother_name = (TextView) rootView.findViewById(R.id.txtDisplayMother_name);
        email = (TextView) rootView.findViewById(R.id.txtDisplayEmail);

        new JsonParserToArrayList().execute();
        return rootView;
    }

    class JsonParserToArrayList extends AsyncTask<String, String, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... arg0) {
            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.getJSONFromUrl(URL);
            return json;
        }

        @Override
        protected void onPostExecute(JSONArray json) {
            JSONObject jsonResultObject = null;

            String displayName = "";
            String displayRoll_No = "";
            String displayGender = "";
            String displayDob = "";
            String displayFather_name = "";
            String displayMother_name = "";
            String displayEmail = "";

            try {

                jsonResultObject = json.getJSONObject(0);
                displayName = jsonResultObject.getString(TAG_NAME);
                displayRoll_No = jsonResultObject.getString(TAG_ROLL_NO);
                displayGender = jsonResultObject.getString(TAG_GENDER);
                displayDob = jsonResultObject.getString(TAG_DOB);
                displayFather_name = jsonResultObject.getString(TAG_FATHER_NAME);
                displayMother_name = jsonResultObject.getString(TAG_MOTHER_NAME);
                displayEmail = jsonResultObject.getString(TAG_EMAIL);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            name.setText(displayName);
            gender.setText(displayGender);
            dob.setText(displayDob);
            roll_no.setText(displayRoll_No);
            father_name.setText(displayFather_name);
            mother_name.setText(displayMother_name);
            email.setText(displayEmail);

            spinner.setVisibility(View.GONE);

        }
    }

}