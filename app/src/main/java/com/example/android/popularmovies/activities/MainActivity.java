package com.example.android.popularmovies.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.AsyncTaskListener;
import com.example.android.popularmovies.FetchMovieTask;
import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.adapters.MovieAdapter;
import com.example.android.popularmovies.adapters.MovieAdapter.MovieAdapterOnClickHandler;
import com.example.android.popularmovies.R;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickHandler {
    private static final String TAG = "MainActivity";

    final static String TMDB_POPULAR_MOVIE = "/movie/popular";
    final static String TMDB_TOP_RATED_MOVIE = "/movie/top_rated";

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns());
        mRecyclerView.setLayoutManager(layoutManager);

        loadMovieData(TMDB_POPULAR_MOVIE);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    public void onListItemClick(Movie clickedItem) {

        Context context = MainActivity.this;
        Class detailActivity = DetailActivity.class;
        Intent startDetailActivityIntent = new Intent(context, detailActivity);
        startDetailActivityIntent.putExtra("movieObject",clickedItem);
        startActivity(startDetailActivityIntent);

    }

    private void showMovieDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void loadMovieData(String sortBy) {
        showMovieDataView();

        new FetchMovieTask(this, new FetchMovieTaskListener()).execute(sortBy);

    }

    public class FetchMovieTaskListener implements AsyncTaskListener<Movie[]> {
        @Override
        public void onTaskStarted() {
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTaskComplete(Movie[] movieData) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movieData);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        item.setChecked(true);
        switch(id) {
            case R.id.sort_by_popular:
                loadMovieData(TMDB_POPULAR_MOVIE);
                break;
            case R.id.sort_by_top_rated:
                loadMovieData(TMDB_TOP_RATED_MOVIE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
