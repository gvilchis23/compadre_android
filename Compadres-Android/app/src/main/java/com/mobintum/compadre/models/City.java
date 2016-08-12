package com.mobintum.compadre.models;

import android.database.Cursor;

import com.mobintum.compadre.database.DatabaseAdapter;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Rick on 16/05/16.
 * email: ricardo.centeno@mobintum.com
 */
public class City implements Serializable {

    public static final String TABLE_NAME = "City";
    public static final String ID_CITY= "idCity";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String STATE = "state";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String PATH_MAIN_PHOTO = "pathMainPhoto";

    private int idCity;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private String pathMainPhoto;
    private String state;

    public City(int idCity, String name, String description, Double latitude, Double longitude, String pathMainPhoto, String state) {
        this.idCity = idCity;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pathMainPhoto = pathMainPhoto;
        this.state = state;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPathMainPhoto() {
        return pathMainPhoto;
    }

    public void setPathMainPhoto(String pathMainPhoto) {
        this.pathMainPhoto = pathMainPhoto;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static List<City> getCities(){
        List<City> objects = new ArrayList<>();
        Cursor cursor = DatabaseAdapter.getDB().query(TABLE_NAME,null,null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int idCity = cursor.getInt(cursor.getColumnIndexOrThrow(ID_CITY));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(STATE));
            double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(LATITUDE));
            double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(LONGITUDE));
            String pathMainPhoto = cursor.getString(cursor.getColumnIndexOrThrow(PATH_MAIN_PHOTO));
            objects.add(new City(idCity,name,description,latitude,longitude,pathMainPhoto,state));
        }
        return objects;
    }
}