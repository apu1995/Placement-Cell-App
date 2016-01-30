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
public class ContactInfoFragment extends Fragment {


    private String URL = "";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS_CORRES = "address_corres";
    private static final String TAG_ADDRESS_PERM = "address_perm";
    private static final String TAG_CONTACT_HOME = "contact_home";
    private static final String TAG_CONTACT_PERSONAL = "contact_personal";
    private static final String TAG_LINKED_IN = "linked_in";

    private String username;
    private ProgressBar spinner;
    private TextView email;
    private TextView contact_home;
    private TextView contact_personal;
    private TextView address_perm;
    private TextView address_corres;
    private TextView linked_in;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        username=getArguments().getString("username");
        View rootView = inflater.inflate(R.layout.fragment_contactinfo, container, false);
        spinner = (ProgressBar)rootView.findViewById(R.id.progressBarContact);

        spinner.setVisibility(View.VISIBLE);

        email = (TextView)  rootView.findViewById(R.id.txtDisplayEmail1);
        contact_personal = (TextView) rootView.findViewById(R.id.txtDisplayPhoneP);
        contact_home = (TextView)  rootView.findViewById(R.id.txtDisplayPhoneH);
        address_perm = (TextView) rootView.findViewById(R.id.txtDisplayAddressP);
        address_corres = (TextView)  rootView.findViewById(R.id.txtDisplayAddressC);
        linked_in = (TextView) rootView.findViewById(R.id.txtDisplayLinked);

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
            String displayPhp = "";
            String displayEmail = "";
            String displayPhh = "";
            String displayAdp = "";
            String displayAdc = "";
            String displayLinked = "";
            try {
                jsonResultObject = json.getJSONObject(0);
                displayEmail = jsonResultObject.getString(TAG_EMAIL);
                displayPhp = jsonResultObject.getString(TAG_CONTACT_PERSONAL);
                displayPhh = jsonResultObject.getString(TAG_CONTACT_HOME);
                displayAdp = jsonResultObject.getString(TAG_ADDRESS_PERM);
                displayAdc = jsonResultObject.getString(TAG_ADDRESS_CORRES);
                displayLinked = jsonResultObject.getString(TAG_LINKED_IN);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            contact_personal.setText(displayPhp);
            email.setText(displayEmail);
            contact_home.setText(displayPhh);
            address_perm.setText(displayAdp);
            address_corres.setText(displayAdc);
            linked_in.setText(displayLinked);

            spinner.setVisibility(View.GONE);
        }
    }


}
