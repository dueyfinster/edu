package ie.ucd.cs.neilgrogan13204052.assignment2;

import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_ICON;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_ICON_LETTER;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_NAME_LOCAL;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_NAME_RESOURCE;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Normalizer;

public class ViewFruitActivity extends Activity {
    private static final String TAG = "ViewCourseActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fruit);

        final Intent in = getIntent();
        final String fruitNameLocal = in.getStringExtra(TAG_NAME_LOCAL);
        final String fruitNameResource = in.getStringExtra(TAG_NAME_RESOURCE);
        String first_letter = flattenToAscii(String.valueOf(fruitNameLocal.toLowerCase().charAt(0)));
        int fruitLetterID = getResources().getIdentifier(TAG_ICON_LETTER + first_letter, "drawable", getPackageName());
        int fruitImageID = getResources().getIdentifier(TAG_ICON + fruitNameResource, "drawable", getPackageName());

        ((ImageView) findViewById(R.id.fruit_letter_image)).setImageResource(fruitLetterID);
        ((TextView) findViewById(R.id.fruit_name)).setText(fruitNameLocal);
        ((ImageView) findViewById(R.id.fruit_image)).setImageResource(fruitImageID);

    }



    public static String flattenToAscii(String string) {
        StringBuilder sb = new StringBuilder(string.length());
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        for (char c : string.toCharArray()) {
            if (c <= '\u007F') sb.append(c);
        }
        return sb.toString();
    }

}
