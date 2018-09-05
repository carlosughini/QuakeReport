/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Earthquake>> {

    public static final String TAG = EarthquakeActivity.class.getName();

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /**
     * Valor constante para o ID do loader de earthquake. Podemos escolher qualquer inteiro.
     * Isto só importa realmente se você estiver usando múltiplos loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        /**
         * Find a referente to the {@link ListView} in the layout
         */
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a customArrayAdapter of earthquakes
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        /**
         * Set the adapter on the {@link ListView}
         * so the list can be populated in the user interface
         */
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the current earthquake
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the URL String on a URI Object
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Instantiate a new intent to open the browser
                Intent web = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Start the web intent
                startActivity(web);
            }
        });

        // Get a reference to the LoaderManager, in order to interact with loaders.
        android.app.LoaderManager loaderManager = getLoaderManager();
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        // Limpa o adapter de dados de earthquake anteriores
        mAdapter.clear();

        /**
         * Se há uma lista válida de {@link Earthquake} então os adiciona ao data set do adapter.
         * Isto ativará a atualização da ListView.
         */
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mAdapter.clear();
    }

    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            // Create URL object
            URL url = QueryUtils.createUrl(strings[0]);

            // Perform the HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            super.onPostExecute(jsonResponse);

            updateUi(jsonResponse);
        }
    }

    private void updateUi(String jsonResponse) {


        /**
         * Get the list of earthquakes from {@link QueryUtils}
         */
        final List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(jsonResponse);



    }
}
