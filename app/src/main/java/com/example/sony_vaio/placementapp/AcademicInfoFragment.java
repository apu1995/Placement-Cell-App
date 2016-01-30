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
public class AcademicInfoFragment extends Fragment {

    private String URL = "";
    private static final String TAG_SPI_1 = "spi_1";
    private static final String TAG_SPI_2 = "spi_2";
    private static final String TAG_SPI_3 = "spi_3";
    private static final String TAG_SPI_4 = "spi_4";
    private static final String TAG_SPI_5 = "spi_5";
    private static final String TAG_SPI_6 = "spi_6";
    private static final String TAG_CPI = "cpi";
    private static final String TAG_DEGREE = "degree";
    private static final String TAG_BRANCH = "branch";
    private static final String TAG_YEAR = "year_of_passing";

    private String username;
    private ProgressBar spinner;
    private TextView spi_1;
    private TextView spi_2;
    private TextView spi_3;
    private TextView spi_4;
    private TextView spi_5;
    private TextView spi_6;
    private TextView cpi;
    private TextView degree;
    private TextView branch;
    private TextView year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        username=getArguments().getString("username");

        View rootView = inflater.inflate(R.layout.fragment_academicinfo, container, false);

        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar2);

        spinner.setVisibility(View.VISIBLE);

        spi_1 = (TextView) rootView.findViewById(R.id.txtDisplaySpi1);
        spi_2 = (TextView) rootView.findViewById(R.id.txtDisplaySpi2);
        spi_3 = (TextView) rootView.findViewById(R.id.txtDisplaySpi3);
        spi_4 = (TextView) rootView.findViewById(R.id.txtDisplaySpi4);
        spi_5 = (TextView) rootView.findViewById(R.id.txtDisplaySpi5);
        spi_6 = (TextView) rootView.findViewById(R.id.txtDisplaySpi6);
        cpi = (TextView) rootView.findViewById(R.id.txtDisplayCpi);
        degree = (TextView) rootView.findViewById(R.id.txtDisplayDegree);
        branch = (TextView) rootView.findViewById(R.id.txtDisplayBranch);
        year = (TextView) rootView.findViewById(R.id.txtDisplayYear);

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

            String displaySpi1 = "";
            String displaySpi2 = "";
            String displaySpi3 = "";
            String displaySpi4 = "";
            String displaySpi5 = "";
            String displaySpi6 = "";
            String displayCpi = "";
            String displayDegree = "";
            String displayBranch = "";
            String displayYear = "";

            try {

                jsonResultObject = json.getJSONObject(0);
                displaySpi1 = jsonResultObject.getString(TAG_SPI_1);
                displaySpi2 = jsonResultObject.getString(TAG_SPI_2);
                displaySpi3 = jsonResultObject.getString(TAG_SPI_3);
                displaySpi4 = jsonResultObject.getString(TAG_SPI_4);
                displaySpi5 = jsonResultObject.getString(TAG_SPI_5);
                displaySpi6 = jsonResultObject.getString(TAG_SPI_6);
                displayCpi = jsonResultObject.getString(TAG_CPI);
                displayDegree = jsonResultObject.getString(TAG_DEGREE);
                displayBranch = jsonResultObject.getString(TAG_BRANCH);
                displayYear = jsonResultObject.getString(TAG_YEAR);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            spi_1.setText(displaySpi1);
            spi_2.setText(displaySpi2);
            spi_3.setText(displaySpi3);
            spi_4.setText(displaySpi4);
            spi_5.setText(displaySpi5);
            spi_6.setText(displaySpi6);
            cpi.setText(displayCpi);
            degree.setText(displayDegree);
            branch.setText(displayBranch);
            year.setText(displayYear);

            spinner.setVisibility(View.GONE);

        }
    }

}
