package com.example.android.popularmovies.utilities;

import android.util.Log;

import com.example.android.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Haven on 09-07-2017.
 */

public class MovieJsonUtils {
    private static final String TAG = "MovieJsonUtils";

    public static Movie[] getMovieObjectsFromJson(String movieJsonString) throws JSONException {
        Movie[] parsedMovieData = null;

        JSONObject movieQuery = new JSONObject(movieJsonString);
        JSONArray movieArray = movieQuery.getJSONArray("results");

        parsedMovieData = new Movie[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {

            JSONObject movieDetail = movieArray.getJSONObject(i);
            Movie movie = new Movie(
                    movieDetail.getString("original_title"),
                    movieDetail.getString("poster_path"),
                    movieDetail.getString("overview"),
                    movieDetail.getDouble("vote_average"),
                    movieDetail.getString("release_date"));
            parsedMovieData[i] = movie;
        }

        return parsedMovieData;
    }
}
