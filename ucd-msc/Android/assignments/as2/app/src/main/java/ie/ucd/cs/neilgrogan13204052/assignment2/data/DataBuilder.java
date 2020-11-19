package ie.ucd.cs.neilgrogan13204052.assignment2.data;

import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.JSON_ARRAY_NAME;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import ie.ucd.cs.neilgrogan13204052.assignment2.model.Model;
import ie.ucd.cs.neilgrogan13204052.assignment2.json.JSONParser;

/**
 * Class to convert JSON to our model
 * Created by ngrogan on 08/02/2015.
 */
public class DataBuilder {

    private final Context context;
    private final String fileName;
    private final ArrayList<Model> models = new ArrayList<>();

    public DataBuilder(Context appContext, String fileName){
        this.context = appContext;
        this.fileName = fileName;
    }
    
    public ArrayList<Model> getListOfFruits(){
        JSONParser parser = new JSONParser(context, fileName);
        JSONObject result = parser.getJSONResult();
        try {
            JSONArray jsonFruits = result.getJSONArray(JSON_ARRAY_NAME);

            for (int i = 0; i < jsonFruits.length(); i++) {
                if((i+1) < jsonFruits.length()){
                    models.add(new Model(jsonFruits.getJSONObject(i), jsonFruits.getJSONObject(i+1)));
                    i++;
                }else{
                    models.add(new Model(jsonFruits.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return models;
    }
}
