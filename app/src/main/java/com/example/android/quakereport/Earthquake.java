package com.example.android.quakereport;

public class Earthquake {

    /** Magnitude of the earthquake */
    private Double mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** Date of the earthquake */
    private long mTimeInMilliseconds;

    /** Url of the earthquake */
    private String mUrl;


    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude of the earthquake
     * @param location is the city where happened the earthquake
     * @param timeInMilliseconds is the time in milli seconds when happened the earthquake.
     */
    public Earthquake(Double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
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

    /**
     *
     * @return the date that happened the earthquake
     */
    public String getUrl() {
        return mUrl;
    }
}
