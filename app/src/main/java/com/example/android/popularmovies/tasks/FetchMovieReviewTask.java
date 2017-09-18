package com.example.android.popularmovies.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmovies.AsyncTaskListener;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.models.MovieReview;
import com.example.android.popularmovies.utilities.MovieJsonUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by Haven on 16-07-2017.
 */

public class FetchMovieReviewTask extends AsyncTask<Integer, Void, MovieReview[]> {
    private static final String TAG = "FetchMovieReviewTask";

    private Context context;
    private AsyncTaskListener<MovieReview[]> listener;

    public FetchMovieReviewTask(Context context, AsyncTaskListener<MovieReview[]> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        listener.onTaskStarted();

    }

    @Override
    protected MovieReview[] doInBackground(Integer... params) {
        int movieId = params[0];
        URL movieDetailRequestUrl = NetworkUtils.buildUrl(movieId, NetworkUtils.TMDB_MOVIE_REVIEWS_ENDPOINT);

        try {
            String jsonMovieDetailResponse = NetworkUtils.getResponseFromHttpUrl(movieDetailRequestUrl);
            MovieReview[] simpleJsonMovieData = MovieJsonUtils.getMovieReviewObjectFromJson(jsonMovieDetailResponse);
            return simpleJsonMovieData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(MovieReview[] movieData) {

        listener.onTaskComplete(movieData);

    }
}
