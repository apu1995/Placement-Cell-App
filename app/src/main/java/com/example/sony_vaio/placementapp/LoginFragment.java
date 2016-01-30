package com.example.sony_vaio.placementapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginFragment extends Fragment {

    private String URL = "";
    private static final String TAG_USER = "id";
    private String username;
    private String password;
    private ProgressBar spinner;
    EditText user,pass;
    Button submit;
    String id="00";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        submit = (Button)rootView.findViewById(R.id.button1);
        user = (EditText)rootView.findViewById(R.id.editText1);
        pass = (EditText)rootView.findViewById(R.id.editText2);

        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar1);


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                spinner.setVisibility(View.VISIBLE);

                username = user.getText().toString();
                password = pass.getText().toString();

                new JsonParserToArrayList().execute();

            }
        });

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

            try {
                jsonResultObject = json.getJSONObject(0);
                id = jsonResultObject.getString(TAG_USER);

                if(id.equalsIgnoreCase(username)) {

                    spinner.setVisibility(View.GONE);
                    Intent intent = new Intent(getActivity().getBaseContext(),
                            MainActivityHome.class);
                    intent.putExtra("username", username);
                    getActivity().startActivity(intent);


                }
                else{


                    spinner.setVisibility(View.GONE);
                    user.setText(null);
                    user.setText(null);


                    Toast.makeText(getActivity(),"Wrong Username or Password",Toast.LENGTH_LONG).show();
                }

            }
            catch(NullPointerException e){

                spinner.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Invalid Username or Password",Toast.LENGTH_LONG).show();


                Fragment currentFragment = getActivity().getFragmentManager().findFragmentById(R.id.frame_container);
                if (currentFragment instanceof LoginFragment) {
                    FragmentTransaction fragTransaction =   (getActivity()).getFragmentManager().beginTransaction();
                    fragTransaction.detach(currentFragment);
                    fragTransaction.attach(currentFragment);
                    fragTransaction.commit();}

                user.setText(null);
                pass.setText(null);
            }

            catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}