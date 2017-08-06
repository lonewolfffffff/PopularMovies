package com.example.android.popularmovies;

/**
 * Created by Haven on 16-07-2017.
 */

public interface AsyncTaskListener<T> {

    public void onTaskStarted();
    public void onTaskComplete(T result);

}
