package com.example.android.popularmovies.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_original_title) TextView mOriginalTitleText;
    @BindView(R.id.iv_poster) ImageView mPoster;
    @BindView(R.id.tv_overview) TextView mOverviewText;
    @BindView(R.id.tv_vote_average) TextView mVoteAverageText;
    @BindView(R.id.tv_release_date) TextView mReleaseDateText;

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
            Picasso.with(context).load(NetworkUtils.TMDB_IMAGE_BASE_URL + "w185" + movie.posterPath).into(mPoster);

            mOverviewText.setText(movie.overview);
            mVoteAverageText.setText(String.valueOf(movie.voteAverage));
            mReleaseDateText.setText(movie.releaseDate);

        }
    }
}
