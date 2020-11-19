package ie.ucd.cs.neilgrogan13204052.p3.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ie.ucd.cs.neilgrogan13204052.p3.json.JSONParser;
import ie.ucd.cs.neilgrogan13204052.p3.model.Course;

/**
 * Class to convert JSON to our model
 * Created by ngrogan on 08/02/2015.
 */
public class DataBuilder {

    private final Context context;
    private final String fileName;
    private final List<Course> courses = new ArrayList<>();

    public DataBuilder(Context appContext, String fileName){
        this.context = appContext;
        this.fileName = fileName;
    }
    
    public List<Course> getListOfCourses(){
        JSONParser parser = new JSONParser(context, fileName);
        JSONObject result = parser.getJSONResult();
        try {
            JSONArray jsonCourses = result.getJSONArray("courses");
            for (int i = 0; i < jsonCourses.length(); i++) {
                JSONObject course = jsonCourses.getJSONObject(i);
                courses.add(new Course(course));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Collections.unmodifiableList(courses);
    }
}
