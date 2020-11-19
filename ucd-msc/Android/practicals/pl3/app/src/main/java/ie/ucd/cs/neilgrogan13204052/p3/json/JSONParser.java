package ie.ucd.cs.neilgrogan13204052.p3.json;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import ie.ucd.cs.neilgrogan13204052.p3.MyModules;

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

            InputStream is = context.getAssets().open("courses.json");
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
