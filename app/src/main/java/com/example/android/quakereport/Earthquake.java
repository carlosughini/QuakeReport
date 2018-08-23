package com.example.android.quakereport;

public class Earthquake {

    /** Magnitude of the earthquake */
    private String mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** Date of the earthquake */
    private String mDate;


    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude of the earthquake
     * @param location is the city where happened the earthquake
     * @param date is when happened the earthquake.
     */
    public Earthquake(String magnitude, String location, String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    /**
     *
     * @return the magnitude of the earthquake
     */
    public String getMagnitude() {
        return mMagnitude;
    }

    /**
     *
     * @return the city where happened the earthquake
     */
    public String getCity() {
        return mLocation;
    }

    /**
     *
     * @return the date that happened the earthquake
     */
    public String getDate() {
        return mDate;
    }
}
