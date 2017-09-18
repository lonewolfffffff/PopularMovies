package com.example.android.popularmovies.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Haven on 09-07-2017.
 */

public class Movie implements Serializable {
    public int id;
    public String originalTitle;
    public String posterPath;
    public String overview;
    public double voteAverage;
    public String releaseDate;
    public MovieTrailer[] trailers;

    public Movie(JSONObject movieJsonObject) throws JSONException {
        this.id = movieJsonObject.getInt("id");
        this.originalTitle = movieJsonObject.getString("original_title");
        this.posterPath = movieJsonObject.getString("poster_path");
        this.overview = movieJsonObject.getString("overview");
        this.voteAverage = movieJsonObject.getDouble("vote_average");
        this.releaseDate = movieJsonObject.getString("release_date");
    }
}
