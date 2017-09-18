package com.example.android.popularmovies.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmovies.AsyncTaskListener;
import com.example.android.popularmovies.models.MovieTrailer;
import com.example.android.popularmovies.utilities.MovieJsonUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by Haven on 16-07-2017.
 */

public class FetchMovieTrailerTask extends AsyncTask<Integer, Void, MovieTrailer[]> {
    private static final String TAG = "FetchMovieTrailerTask";

    private Context context;
    private AsyncTaskListener<MovieTrailer[]> listener;

    public FetchMovieTrailerTask(Context context, AsyncTaskListener<MovieTrailer[]> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        listener.onTaskStarted();

    }

    @Override
    protected MovieTrailer[] doInBackground(Integer... params) {
        int movieId = params[0];
        URL movieDetailRequestUrl = NetworkUtils.buildUrl(movieId, NetworkUtils.TMDB_MOVIE_VIDEOS_ENDPOINT);

        try {
            String jsonMovieDetailResponse = NetworkUtils.getResponseFromHttpUrl(movieDetailRequestUrl);
            MovieTrailer[] simpleJsonMovieData = MovieJsonUtils.getMovieTrailerObjectFromJson(jsonMovieDetailResponse);
            return simpleJsonMovieData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(MovieTrailer[] movieData) {

        listener.onTaskComplete(movieData);

    }
}
