package com.example.android.popularmovies;

import java.io.Serializable;

/**
 * Created by Haven on 09-07-2017.
 */

public class Movie implements Serializable {
    public String originalTitle;
    public String posterPath;
    public String overview;
    public double voteAverage;
    public String releaseDate;

    public Movie(String originalTitle,String posterPath,String overview,double voteAverage,String releaseDate) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }
}
