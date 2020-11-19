package ie.ucd.cs.neilgrogan13204052.assignment1;

import static ie.ucd.cs.neilgrogan13204052.assignment1.Constants.*;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static final String BLANK_STRING = "";
    Integer[] fields = {R.id.from_field, R.id.to_field, R.id.cc_field, R.id.bcc_field, R.id.subject_field, R.id.message_field};
    String[] tags = {TAG_FROM, TAG_TO, TAG_CC, TAG_BCC, TAG_SUBJECT, TAG_MESSAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            getState(savedInstanceState);
        }

        Button send_button = (Button) findViewById(R.id.send_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendButtonAction();
            }
        });

        Button clear_button= (Button) findViewById(R.id.clear_button);
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtonAction();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        saveState(state);
    }

    private void saveState(Bundle state){
        int tag_id = 0;
        for(int view_id : fields){
            state.putSerializable(tags[tag_id], ((EditText) findViewById(view_id)).getText().toString());
            tag_id++;
        }
    }

    private void getState(Bundle state){
        int tag_id = 0;
        for(int view_id : fields){
            ((EditText) findViewById(view_id)).setText(retrieveStateValue(state,tags[tag_id]));
            tag_id++;
        }
    }

    private String retrieveStateValue(Bundle state, String TAG){
        String val = (String) state.getSerializable(TAG);

        if (val != null) {
            return val;
        }else{
            return "";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendButtonAction(){

        // getting values from selected ListItem
        String from = ((TextView) findViewById(R.id.from_field))
                .getText().toString();
        String to = ((TextView) findViewById(R.id.to_field))
                .getText().toString();
        String cc = ((TextView) findViewById(R.id.cc_field))
                .getText().toString();
        String bcc = ((TextView) findViewById(R.id.bcc_field))
                .getText().toString();
        String subject = ((TextView) findViewById(R.id.subject_field))
                .getText().toString();
        String message = ((TextView) findViewById(R.id.message_field))
                .getText().toString();

        // Starting single contact activity
        Intent in = new Intent(getApplicationContext(),
                EmailShowActivity.class);
        int tag_id = 0;
        for(int view_id : fields){
            in.putExtra(tags[tag_id], ((EditText) findViewById(view_id)).getText().toString());
            tag_id++;
        }
        startActivity(in);
    }

    private void clearButtonAction(){
        for(int view_id : fields){
            ((EditText) findViewById(view_id)).setText(BLANK_STRING);
        }
    }
}
