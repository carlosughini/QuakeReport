package com.example.android.quakereport;

public class Earthquake {

    /** Magnitude of the earthquake */
    private Double mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** Date of the earthquake */
    private long mTimeInMilliseconds;


    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude of the earthquake
     * @param location is the city where happened the earthquake
     * @param timeInMilliseconds is the time in milli seconds when happened the earthquake.
     */
    public Earthquake(Double magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    /**
     *
     * @return the magnitude of the earthquake
     */
    public Double getMagnitude() {
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
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
