package com.example.android.popularmovies.models;

/**
 * Created by Haven on 06-08-2017.
 */

public class MovieReview {
    public String id, author, content;

    public MovieReview(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }
}
