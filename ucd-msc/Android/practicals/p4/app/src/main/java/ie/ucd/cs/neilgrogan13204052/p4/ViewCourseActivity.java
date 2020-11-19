package ie.ucd.cs.neilgrogan13204052.p4;

import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.KEY_SAVED_RADIO_BUTTON_INDEX;
import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_NAME;
import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_CODE;
import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_CREDITS;
import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.TAG_DESC;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ViewCourseActivity extends Activity {
    private static final String TAG = "ViewCourseActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_course);

        // Now get a handle to any View contained
        // within the main layout you are using
        View course_view = findViewById(R.id.view_course);

        // Find the root view
        View root = course_view.getRootView();

        // Set the color
        root.setBackgroundColor(GetColourFromPrefs());

        Intent in = getIntent();
        
        // Labels
        TextView courseNameLabel = (TextView) findViewById(R.id.name_label);
        TextView courseCreditLabel = (TextView) findViewById(R.id.credits_label);
        TextView courseCodeLabel = (TextView) findViewById(R.id.course_code_label);
        TextView descLabel = (TextView) findViewById(R.id.desc_label);

        // Set values
        courseNameLabel.setText(in.getStringExtra(TAG_NAME));
        courseCreditLabel.setText(in.getStringExtra(TAG_CREDITS));
        courseCodeLabel.setText(in.getStringExtra(TAG_CODE));
        descLabel.setText(in.getStringExtra(TAG_DESC));
    }


    private int GetColourFromPrefs(){

        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        Color c;

        switch(savedRadioIndex){
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
        }

        return 0;
    }
}
