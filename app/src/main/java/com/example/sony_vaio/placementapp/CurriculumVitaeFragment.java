package com.example.sony_vaio.placementapp;

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


/**
 * Created by SONY_VAIO on 12-04-2015.
 */
public class CurriculumVitaeFragment extends Fragment {

    private static final String TAG_CV = "cv_link";
    private String username;
    private String URL = "";

    private TextView CV;
    private ProgressBar spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        username=getArguments().getString("username");

        View rootView = inflater.inflate(R.layout.fragment_curriculumvitae, container, false);
        spinner=(ProgressBar)rootView.findViewById(R.id.progressBarCV);

        spinner.setVisibility(View.VISIBLE);
        CV = (TextView)rootView.findViewById(R.id.txtDisplayCV);

        new JSONParserToArrayList().execute();

        return rootView;
    }

    public class JSONParserToArrayList extends AsyncTask<String,String,JSONArray>{

        @Override
        protected JSONArray doInBackground(String...arg0){
            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.getJSONFromUrl(URL);
            return json;
        }

        protected void onPostExecute(JSONArray json){
            JSONObject jsonResultObject = null;

            String displayCV = "";
            try {

                jsonResultObject = json.getJSONObject(0);
                displayCV = jsonResultObject.getString(TAG_CV);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            CV.setText(displayCV);

            spinner.setVisibility(View.GONE);



        }

    }

}
