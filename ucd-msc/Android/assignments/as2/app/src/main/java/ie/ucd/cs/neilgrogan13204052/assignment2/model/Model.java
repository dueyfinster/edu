package ie.ucd.cs.neilgrogan13204052.assignment2.model;

import android.util.Log;

import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_ICON;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_ICON_LETTER;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_NAME_RESOURCE;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Internal model for JSON Course Data
 *
 * Created by ngrogan on 08/02/2015.
 */
public class Model {
    private static final String TAG = "Model";
    private List<Fruit> fruits = new ArrayList<>();

    public Model(JSONObject obj) throws JSONException {
        Log.v(TAG, "Fruit to be added: " + obj.getString(TAG_NAME_RESOURCE));
        fruits.add(new Fruit(obj.getString(TAG_NAME_RESOURCE)));
    }

    public Model(JSONObject obj, JSONObject obj2) throws JSONException {
        Log.v(TAG, "Fruits to be added: " + obj.getString(TAG_NAME_RESOURCE) +", " + obj2.getString(TAG_NAME_RESOURCE));

        fruits.add(new Fruit(obj.getString(TAG_NAME_RESOURCE)));
        fruits.add(new Fruit(obj2.getString(TAG_NAME_RESOURCE)));
    }

    public List<Fruit> getFruits(){
        return fruits;
    }

}
