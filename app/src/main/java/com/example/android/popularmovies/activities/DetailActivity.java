package com.example.android.popularmovies.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.AsyncTaskListener;
import com.example.android.popularmovies.adapters.MovieReviewAdapter;
import com.example.android.popularmovies.adapters.MovieTrailerAdapter;
import com.example.android.popularmovies.models.MovieReview;
import com.example.android.popularmovies.tasks.FetchMovieReviewTask;
import com.example.android.popularmovies.tasks.FetchMovieTrailerTask;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.models.MovieTrailer;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements MovieTrailerAdapter.MovieDetailAdapterOnClickHandler, MovieReviewAdapter.MovieDetailAdapterOnClickHandler {
    private static final String TAG = "DetailActivity";

    @BindView(R.id.tv_original_title) TextView mOriginalTitleText;
    @BindView(R.id.iv_poster) ImageView mPoster;
    @BindView(R.id.tv_overview) TextView mOverviewText;
    @BindView(R.id.tv_vote_average) TextView mVoteAverageText;
    @BindView(R.id.tv_release_date) TextView mReleaseDateText;

    private RecyclerView mTrailerListRecyclerView;
    private MovieTrailerAdapter mMovieTrailerAdapter;
    private RecyclerView mReviewListRecyclerView;
    private MovieReviewAdapter mMovieReviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity.hasExtra("movieObject") ) {
            Movie movie = (Movie) intentThatStartedThisActivity.getSerializableExtra("movieObject");

            mOriginalTitleText.setText(movie.originalTitle);

            Context context = DetailActivity.this;
            Picasso.with(context).load(NetworkUtils.TMDB_IMAGE_BASE_URL + NetworkUtils.TMDB_IMAGE_THUMB_SIZE + movie.posterPath).into(mPoster);

            mOverviewText.setText(movie.overview);
            mVoteAverageText.setText(String.valueOf(movie.voteAverage));
            mReleaseDateText.setText(movie.releaseDate);

            mTrailerListRecyclerView = (RecyclerView) findViewById(R.id.rv_trailer_list);

            mMovieTrailerAdapter = new MovieTrailerAdapter(this);
            mTrailerListRecyclerView.setAdapter(mMovieTrailerAdapter);

            mReviewListRecyclerView = (RecyclerView) findViewById(R.id.rv_review_list);
            mMovieReviewAdapter = new MovieReviewAdapter(this);
            mReviewListRecyclerView.setAdapter(mMovieReviewAdapter);

            LinearLayoutManager trailerListLayoutManager = new LinearLayoutManager(this);
            mTrailerListRecyclerView.setLayoutManager(trailerListLayoutManager);

            LinearLayoutManager reviewListLayoutManager = new LinearLayoutManager(this);
            mReviewListRecyclerView.setLayoutManager(reviewListLayoutManager);

            loadMovieTrailerData(movie.id);
            loadMovieReviewData(movie.id);
        }
    }

    @Override
    public void onListItemClick(MovieTrailer clickedItem) {

        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + clickedItem.key));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + clickedItem.key));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }

    }

    @Override
    public void onListItemClick(MovieReview clickedItem) {

    }

    private void loadMovieTrailerData(int movieId) {
        new FetchMovieTrailerTask(this, new FetchMovieTrailerTaskListener()).execute(movieId);
    }

    public class FetchMovieTrailerTaskListener implements AsyncTaskListener<MovieTrailer[]> {
        @Override
        public void onTaskStarted() {

        }

        @Override
        public void onTaskComplete(MovieTrailer[] movieData) {

            if (movieData != null) {
                //showMovieDataView();
                mMovieTrailerAdapter.setMovieData(movieData);
            } else {
                //showErrorMessage();
            }
        }
    }

    private void loadMovieReviewData(int movieId) {
        new FetchMovieReviewTask(this, new FetchMovieReviewTaskListener()).execute(movieId);
    }

    public class FetchMovieReviewTaskListener implements AsyncTaskListener<MovieReview[]> {
        @Override
        public void onTaskStarted() {

        }

        @Override
        public void onTaskComplete(MovieReview[] movieData) {

            if (movieData != null) {
                //showMovieDataView();
                mMovieReviewAdapter.setMovieData(movieData);
            } else {
                //showErrorMessage();
            }
        }
    }
}
