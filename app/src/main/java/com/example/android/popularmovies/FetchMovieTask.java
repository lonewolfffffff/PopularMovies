package com.example.android.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.popularmovies.utilities.MovieJsonUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by Haven on 16-07-2017.
 */

public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
    private static final String TAG = "FetchMovieTask";

    private Context context;
    private AsyncTaskListener<Movie[]> listener;

    public FetchMovieTask(Context context, AsyncTaskListener<Movie[]> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        listener.onTaskStarted();

    }

    @Override
    protected Movie[] doInBackground(String... params) {
        String sortBy = params[0];
        URL movieRequestUrl = NetworkUtils.buildUrl(sortBy);

        try {
            String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
            Movie[] simpleJsonMovieData = MovieJsonUtils.getMovieObjectsFromJson(jsonMovieResponse);
            return simpleJsonMovieData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Movie[] movieData) {

        listener.onTaskComplete(movieData);

    }
}
