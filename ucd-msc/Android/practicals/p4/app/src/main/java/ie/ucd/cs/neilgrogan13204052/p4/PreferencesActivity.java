package ie.ucd.cs.neilgrogan13204052.p4;


import static ie.ucd.cs.neilgrogan13204052.p4.data.Constants.KEY_SAVED_RADIO_BUTTON_INDEX;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;

public class PreferencesActivity extends Activity {

    RadioGroup radioGroup;
    TextView textCheckedID, textCheckedIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefs);
        radioGroup = (RadioGroup)findViewById(R.id.colour_choice);
        radioGroup.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener);

        textCheckedID = (TextView)findViewById(R.id.checkedid);
        textCheckedIndex = (TextView)findViewById(R.id.checkedindex);

        LoadPreferences();
    }

    OnCheckedChangeListener radioGroupOnCheckedChangeListener =
            new OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(checkedId);
                    int checkedIndex = radioGroup.indexOfChild(checkedRadioButton);

                    textCheckedID.setText("checkedID = " + checkedId);
                    textCheckedIndex.setText("checkedIndex = " + checkedIndex);
                    SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                }};

    private void SavePreferences(String key, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton)radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }
}