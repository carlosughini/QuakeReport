package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

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
        // Get the date from the currentEarthquakeData object and set this text on the TextView
        magnitudeView.setText(currentEarthquakeData.getMagnitude());

        // Find the view in the list_item.xml layout with the ID city
        TextView cityView = (TextView) listItemView.findViewById(R.id.location);
        // Get the date from the currentEarthquakeData object and set this text on the TextView
        cityView.setText(currentEarthquakeData.getCity());

        // Find the view in the list_item.xml layout with the ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Get the date from the currentEarthquakeData object and set this text on the TextView
        dateView.setText(currentEarthquakeData.getDate());

        return listItemView;
    }
}
