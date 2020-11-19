package ie.ucd.cs.neilgrogan13204052.assignment2.json;

import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.JSON_FILE_NAME;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ngrogan on 08/02/2015.
 */
public class JSONParser {

    private static final String TAG = "JSONParser";
    private final Context context;
    private final String fileName;

    public JSONParser(Context appContext, String fileName){
        this.context = appContext;
        this.fileName = fileName;
    }

    public JSONObject getJSONResult(){
        JSONObject JSONobj = null;
        try {
            JSONobj = new JSONObject(loadJSONFromAsset(fileName));
        } catch (JSONException e) {
            Log.v(TAG, "Could not parse JSON: \n\n " +  e.getMessage());
        }

        Log.v(TAG, "Returning JSON: \n\n " +  JSONobj.toString());

        return JSONobj;
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(fileName);
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.v(TAG, "Loaded raw JSON: \n\n " + json);

        return json;
    }
}
