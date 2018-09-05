package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag para mensagens de log */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** URL da busca */
    private String mUrl;

    /**
     * Constrói um novo {@link EarthquakeLoader}.
     * @param context - Contexto da activity
     * @param url - A URL de onde carregaremos dados
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Realiza requisição de rede, decodifica a resposta, e extra ma lista de earthquakes.
        List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(mUrl);
        return earthquakes;
    }

}
