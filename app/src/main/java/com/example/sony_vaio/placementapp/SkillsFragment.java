package com.example.sony_vaio.placementapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SONY_VAIO on 12-04-2015.
 */
public class SkillsFragment extends Fragment{

    private String URL = "";
    private static final String TAG_SKILLS = "skills";
    private String username;
    private static final String TAG_DESCRIPTION = "description";





    private TextView skills;
    private TextView description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        username=getArguments().getString("username");
        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);
        skills = (TextView) rootView.findViewById(R.id.txtDisplaySkills);
        description = (TextView) rootView.findViewById(R.id.txtDisplayDescription);

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

            String displaySkills = "";
            String displayDescription = "";

            try {

                jsonResultObject = json.getJSONObject(0);
                displaySkills = jsonResultObject.getString(TAG_SKILLS);
                displayDescription = jsonResultObject.getString(TAG_DESCRIPTION);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            skills.setText(displaySkills);
            description.setText(displayDescription);

        }
    }


}
