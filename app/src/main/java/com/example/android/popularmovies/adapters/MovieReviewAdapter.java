package com.example.android.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.models.MovieReview;
import com.example.android.popularmovies.models.MovieTrailer;

/**
 * Created by Haven on 09-07-2017.
 */

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieDetailAdapterViewHolder> {
    private static final String TAG = "MovieTrailerAdapter";

    private MovieReview[] mMovieData;
    private final MovieDetailAdapterOnClickHandler mClickHandler;

    public interface MovieDetailAdapterOnClickHandler {
        void onListItemClick(MovieReview clickedItem);
    }

    public MovieReviewAdapter(MovieDetailAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @Override
    public MovieDetailAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.review_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieDetailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieDetailAdapterViewHolder movieDetailAdapterViewHolder, int position) {
        MovieReview movieReview = mMovieData[position];
        movieDetailAdapterViewHolder.mMovieReviewAuthor.setText(movieReview.author);
        movieDetailAdapterViewHolder.mMovieReview.setText(movieReview.content);

    }

    @Override
    public int getItemCount() {

        if (null == mMovieData) return 0;
        return mMovieData.length;
    }

    public class MovieDetailAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        public final TextView mMovieReviewAuthor;
        public final TextView mMovieReview;

        public MovieDetailAdapterViewHolder(View view) {
            super(view);

            mMovieReviewAuthor = view.findViewById(R.id.tv_movie_review_author);
            mMovieReview = view.findViewById(R.id.tv_movie_review);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mClickHandler.onListItemClick(mMovieData[clickedPosition]);
        }

    }

    public void setMovieData(MovieReview[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
