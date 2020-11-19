package ie.ucd.cs.neilgrogan13204052.p3.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Internal model for JSON Course Data
 *
 * Created by ngrogan on 08/02/2015.
 */
public class Course {
    private String name;
    private String description;
    private double credits;
    private String courseCode;

    public Course(JSONObject obj) throws JSONException {
        this.name = obj.getString("course_name");
        this.description = obj.getString("description");
        this.credits = Double.parseDouble(obj.getString("credits"));
        this.courseCode = obj.getString("course_code");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCredits() {
        return credits;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
