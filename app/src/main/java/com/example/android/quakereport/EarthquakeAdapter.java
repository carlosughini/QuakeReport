package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";

    /**
     * Constructs a new {@link EarthquakeAdapter}.
     *
     * @param context of the app
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
     */
    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return a list item view that displays information about the earthquake at the given
     * position in the list of earthquakes.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }

        // Get the object Earthquake located at this position in the list
        Earthquake currentEarthquakeData = getItem(position);

        // Find the view in the list_item.xml layout with the ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

        String magnitude = formatMagnitude(currentEarthquakeData.getMagnitude());

        // Get the date from the currentEarthquakeData object and set this text on the TextView
        magnitudeView.setText(magnitude);

        String location = currentEarthquakeData.getCity();

        String primaryLocation;
        String locationOffSet;

        if (location.contains(LOCATION_SEPARATOR)) {
            String[] newLocation = location.split("(?<=of)");
            Log.v("EarthQuakeAdapter","String é: " + Arrays.toString(newLocation));
            locationOffSet = newLocation[0];
            primaryLocation = newLocation[1];
        } else {
            locationOffSet = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }

        // Find the view in the list_item.xml layout with the ID city
        TextView locationOffSetView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Get the date from the currentEarthquakeData object and set this text on the TextView
        locationOffSetView.setText(locationOffSet);

        // Find the view in the list_item.xml layout with the ID city
        TextView parentLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Get the date from the currentEarthquakeData object and set this text on the TextView
        parentLocationView.setText(primaryLocation);

        // Create a new object Date of the timeInMilliSeconds of the eartquake
        Date dateObject = new Date(currentEarthquakeData.getTimeInMilliseconds());

        // Find the view in the list_item.xml layout with the ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        // Format the string
        String formattedDate = formatDate(dateObject);

        // Set the text with the date
        dateView.setText(formattedDate);

        // Find the view in the list_item.xml layout with the ID date
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        // Format the string
        String formattedTime = formatTime(dateObject);

        // Set the text with the date
        timeView.setText(formattedTime);

        return listItemView;
    }

    /**
     * Retorna a data string formatada (i.e. "Mar 3, 1984") de um objeto Date.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy",Locale.getDefault());
        return dateFormat.format(dateObject);
    }

    /**
     * Retorna a data string formatada (i.e. "4:30 PM") de um objeto Date.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a",Locale.getDefault());
        return timeFormat.format(dateObject);
    }

    /**
     * Retorna a string magnitude formatada mostrando 1 casa decimal (i.e. "3.2")
     * de um valor de magnitude decimal.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
