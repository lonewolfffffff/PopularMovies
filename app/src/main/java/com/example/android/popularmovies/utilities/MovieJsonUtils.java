package com.example.android.popularmovies.utilities;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.models.MovieReview;
import com.example.android.popularmovies.models.MovieTrailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Haven on 09-07-2017.
 */

public class MovieJsonUtils {
    private static final String TAG = "MovieJsonUtils";

    public static Movie[] getMovieObjectsFromJson(String movieJsonString) throws JSONException {
        JSONObject movieQuery = new JSONObject(movieJsonString);
        JSONArray movieArray = movieQuery.getJSONArray("results");

        Movie[] parsedMovieData = new Movie[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {

            JSONObject movieDetails = movieArray.getJSONObject(i);
            Movie movie = new Movie(movieDetails);
            parsedMovieData[i] = movie;
        }

        return parsedMovieData;
    }

    public static MovieTrailer[] getMovieTrailerObjectFromJson(String movieJsonString) throws JSONException {
        // trailers
        JSONObject movieDetails = new JSONObject(movieJsonString);
        JSONArray movieVideoArray = movieDetails.getJSONArray("results");

        MovieTrailer[] parsedMovieData = new MovieTrailer[movieVideoArray.length()];

        for (int i = 0; i < movieVideoArray.length(); i++) {

            JSONObject movieVideo = movieVideoArray.getJSONObject(i);
            MovieTrailer trailer = new MovieTrailer(
                    movieVideo.getString("key"),
                    movieVideo.getString("name"),
                    movieVideo.getString("site")

            );

            parsedMovieData[i] = trailer;

        }

        return parsedMovieData;
    }

    public static MovieReview[] getMovieReviewObjectFromJson(String movieJsonString) throws JSONException {
        // reviews
        JSONObject movieDetails = new JSONObject(movieJsonString);
        JSONArray movieReviewArray = movieDetails.getJSONArray("results");

        MovieReview[] parsedMovieData = new MovieReview[movieReviewArray.length()];

        for (int i = 0; i < movieReviewArray.length(); i++) {

            JSONObject movieReview = movieReviewArray.getJSONObject(i);
            MovieReview review = new MovieReview(
                    movieReview.getString("id"),
                    movieReview.getString("author"),
                    movieReview.getString("content")

            );

            parsedMovieData[i] = review;

        }

        return parsedMovieData;
    }
}
