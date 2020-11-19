package ie.ucd.cs.neilgrogan13204052.deathnotices.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Notice implements Serializable {

	@SerializedName("id")
    public int id;
	public String first_name;
    public String last_name;
    public String address;
    public String lat;
    public String lon;
	public String text;
	public Date date_of_death;
    public Date date_published;
	
	public Notice() {
		
	}

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate_of_death() {
        return date_of_death;
    }

    public void setDate_of_death(Date date_of_death) {
        this.date_of_death = date_of_death;
    }

    public Date getDate_published() {
        return date_published;
    }

    public void setDate_published(Date date_published) {
        this.date_published = date_published;
    }

    @Override
    public String toString(){
        return first_name + " " + last_name;
   }
}