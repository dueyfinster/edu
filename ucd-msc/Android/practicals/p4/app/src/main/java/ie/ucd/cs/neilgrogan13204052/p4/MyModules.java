package ie.ucd.cs.neilgrogan13204052.p4;

import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_NAME;
import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_CODE;
import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_CREDITS;
import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_DESC;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ie.ucd.cs.neilgrogan13204052.p4.data.DataBuilder;
import ie.ucd.cs.neilgrogan13204052.p4.model.Course;


public class MyModules extends ListActivity {
    private static final String TAG = "MyModules";
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_modules);

        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String credits = ((TextView) view.findViewById(R.id.credits))
                        .getText().toString();
                String code = ((TextView) view.findViewById(R.id.code))
                        .getText().toString();
                String description = ((TextView) view.findViewById(R.id.desc))
                        .getText().toString();

                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),
                        ViewCourseActivity.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_CREDITS, credits);
                in.putExtra(TAG_CODE, code);
                in.putExtra(TAG_DESC, description);
                startActivity(in);

            }
        });

        // Calling async task to get json
        new GetCourses().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_modules, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferencesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetCourses extends AsyncTask<Void, Void, Void> {

        // Hashmap for ListView
        ArrayList<HashMap<String, String>> courseList = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MyModules.this);
            pDialog.setMessage("Retrieving modules...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            DataBuilder db = new DataBuilder(getApplicationContext(), "courses.json");
            List<Course> courses = db.getListOfCourses();
            for(Course c : courses){

                HashMap<String, String> course = new HashMap<String, String>();

                Log.v(TAG, "Course Name: " + c.getName());
                Log.v(TAG, "Course Code: " + c.getCourseCode());
                Log.v(TAG, "Course Description: " + c.getDescription());
                Log.v(TAG, "Course Credits: " + c.getCredits());
                course.put(TAG_NAME,c.getName());
                course.put(TAG_CODE,c.getCourseCode());
                course.put(TAG_DESC,c.getDescription());
                course.put(TAG_CREDITS, Double.toString(c.getCredits()));

                courseList.add(course);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MyModules.this, courseList,
                    R.layout.list_item, new String[] { TAG_NAME, TAG_CODE,
                    TAG_DESC, TAG_CREDITS }, new int[] { R.id.name,
                    R.id.code, R.id.desc, R.id.credits });

            setListAdapter(adapter);
        }

    }
}
