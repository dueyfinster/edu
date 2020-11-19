package ie.ucd.cs.neilgrogan13204052.p3;

import static ie.ucd.cs.neilgrogan13204052.p3.data.Constants.TAG_NAME;
import static ie.ucd.cs.neilgrogan13204052.p3.data.Constants.TAG_CODE;
import static ie.ucd.cs.neilgrogan13204052.p3.data.Constants.TAG_CREDITS;
import static ie.ucd.cs.neilgrogan13204052.p3.data.Constants.TAG_DESC;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewCourseActivity extends Activity {
    private static final String TAG = "ViewCourseActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_course);

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
}
