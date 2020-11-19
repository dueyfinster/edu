package ie.ucd.cs.neilgrogan13204052.assignment1;

import static ie.ucd.cs.neilgrogan13204052.assignment1.Constants.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by ngrogan on 15/02/2015.
 */
public class EmailShowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_email);

        Button back_button = (Button) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonAction();
            }
        });

        Intent in = getIntent();

        // Labels
        TextView fromField = (TextView) findViewById(R.id.from_show_field);
        TextView toField = (TextView) findViewById(R.id.to_show_field);
        TextView ccField = (TextView) findViewById(R.id.cc_show_field);
        TextView subjectField = (TextView) findViewById(R.id.subject_show_field);
        TextView messageField = (TextView) findViewById(R.id.message_show_field);

        // Set values
        fromField.setText(in.getStringExtra(TAG_FROM));
        toField.setText(in.getStringExtra(TAG_TO));
        ccField.setText(in.getStringExtra(TAG_CC));
        subjectField.setText(in.getStringExtra(TAG_SUBJECT));
        messageField.setText(in.getStringExtra(TAG_MESSAGE));
    }

    private void backButtonAction() {
        //moveTaskToBack(true);
        EmailShowActivity.this.finish();
    }
}
