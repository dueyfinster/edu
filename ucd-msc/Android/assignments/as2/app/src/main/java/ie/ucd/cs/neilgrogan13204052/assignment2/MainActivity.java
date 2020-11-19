package ie.ucd.cs.neilgrogan13204052.assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import ie.ucd.cs.neilgrogan13204052.assignment2.data.DataBuilder;
import ie.ucd.cs.neilgrogan13204052.assignment2.model.Model;

import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.JSON_FILE_NAME;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_NAME_LOCAL;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_NAME_RESOURCE;

public class MainActivity extends ListActivity {


    private static final String TAG = "";

    public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		new GetFruits().execute();
    }

    private void startFruitDisplay(String localName, String resourceName){
        // Starting single contact activity
        Intent in = new Intent(getApplicationContext(),ViewFruitActivity.class);
        in.putExtra(TAG_NAME_LOCAL, localName);
        in.putExtra(TAG_NAME_RESOURCE, resourceName);

        startActivity(in);
    }

    public void myClickHandler(View v){
        String buttonName = getResources().getResourceEntryName(v.getId());

        //get the row the clicked button is in
        if(buttonName.contains("left")){
            LinearLayout vwParentRow = (LinearLayout)v.getParent();

            TextView localTextField = (TextView)vwParentRow.findViewById(R.id.fruit_name_local_left);
            TextView resourceTextField = (TextView)vwParentRow.findViewById(R.id.fruit_name_resource_left);
            Log.v("ButtonClick", localTextField.getText().toString() + " is picked");

            startFruitDisplay(localTextField.getText().toString(), resourceTextField.getText().toString());

        }else if(buttonName.contains("right")){
            LinearLayout vwParentRow = (LinearLayout)v.getParent();

            TextView localTextField = (TextView)vwParentRow.findViewById(R.id.fruit_name_local_right);
            TextView resourceTextField = (TextView)vwParentRow.findViewById(R.id.fruit_name_resource_right);
            Log.v("ButtonClick", localTextField.getText().toString() + " is picked");

            startFruitDisplay(localTextField.getText().toString(), resourceTextField.getText().toString());
        }
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetFruits extends AsyncTask<Void, Void, Void> {

        // Hashmap for ListView
        ArrayList<Model> fruitList = new ArrayList<>();
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Retrieving fruits...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg) {
            DataBuilder db = new DataBuilder(getApplicationContext(), JSON_FILE_NAME);
            fruitList = db.getListOfFruits();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            MyAdapter adapter = new MyAdapter(getBaseContext(), fruitList);
            setListAdapter(adapter);

            setListAdapter(adapter);
        }
    }

} 
