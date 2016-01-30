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
public class ExperiencesFragment extends Fragment {

    private String URL = "";
    private static final String TAG_INTERN_DETAIL = "intern_detail";
    private String username;
    private ProgressBar spinner;
    private static final String TAG_INTERN_PLACE = "intern_place";
    private static final String TAG_INTERN_TITLE = "intern_title";
    private static final String TAG_START_DATE = "start_date";
    private static final String TAG_END_DATE = "end_date";

    private TextView intern_detail;
    private TextView intern_place;
    private TextView intern_title;
    private TextView start_date;
    private TextView end_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        username=getArguments().getString("username");

        View rootView = inflater.inflate(R.layout.fragment_experiences, container, false);
        spinner = (ProgressBar)rootView.findViewById(R.id.progressBarExperiences);

        spinner.setVisibility(View.VISIBLE);

        intern_detail = (TextView)rootView.findViewById(R.id.txtDisplayDetail);
        intern_place = (TextView) rootView.findViewById(R.id.txtDisplayPlace);
        intern_title = (TextView)rootView.findViewById(R.id.txtTitle2);
        start_date = (TextView) rootView.findViewById(R.id.txtDisplayStart);
        end_date = (TextView) rootView.findViewById(R.id.textEnd);

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

            String displayDetail = "";
            String displayPlace = "";
            String displayTitle = "";
            String displayStart = "";
            String displayEnd = "";

            try {

                jsonResultObject = json.getJSONObject(0);
                displayDetail = jsonResultObject.getString(TAG_INTERN_DETAIL);
                displayPlace = jsonResultObject.getString(TAG_INTERN_PLACE);
                displayTitle = jsonResultObject.getString(TAG_INTERN_TITLE);
                displayStart = jsonResultObject.getString(TAG_START_DATE);
                displayEnd = jsonResultObject.getString(TAG_END_DATE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            intern_detail.setText(displayDetail);
            intern_place.setText(displayPlace);
            intern_title.setText(displayTitle);
            start_date.setText(displayStart);
            end_date.setText(displayEnd);

            spinner.setVisibility(View.GONE);


        }
    }


}
