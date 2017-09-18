package com.example.android.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.models.MovieTrailer;
import com.example.android.popularmovies.R;

/**
 * Created by Haven on 09-07-2017.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MovieDetailAdapterViewHolder> {
    private static final String TAG = "MovieTrailerAdapter";

    private MovieTrailer[] mMovieData;
    private final MovieDetailAdapterOnClickHandler mClickHandler;

    public interface MovieDetailAdapterOnClickHandler {
        void onListItemClick(MovieTrailer clickedItem);
    }

    public MovieTrailerAdapter(MovieDetailAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @Override
    public MovieDetailAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.trailer_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieDetailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieDetailAdapterViewHolder movieDetailAdapterViewHolder, int position) {
        MovieTrailer movieTrailer = mMovieData[position];
        movieDetailAdapterViewHolder.mMovieTrailer.setText(movieTrailer.name);

    }

    @Override
    public int getItemCount() {

        if (null == mMovieData) return 0;
        return mMovieData.length;
    }

    public class MovieDetailAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        public final TextView mMovieTrailer;

        public MovieDetailAdapterViewHolder(View view) {
            super(view);

            mMovieTrailer = view.findViewById(R.id.tv_movie_trailer);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mClickHandler.onListItemClick(mMovieData[clickedPosition]);
        }

    }

    public void setMovieData(MovieTrailer[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
