package com.example.android.popularmovies.utilities;

import android.net.Uri;

import com.example.android.popularmovies.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Haven on 09-07-2017.
 */

public class NetworkUtils {

    final static String TMDB_BASE_URL = "https://api.themoviedb.org/3";

    public final static String TMDB_POPULAR_MOVIE = "/movie/popular";
    public final static String TMDB_TOP_RATED_MOVIE = "/movie/top_rated";

    final static String TMDB_MOVIE_DETAILS = "/movie/";
    public final static String TMDB_MOVIE_VIDEOS_ENDPOINT = "/videos";
    public final static String TMDB_MOVIE_REVIEWS_ENDPOINT = "/reviews";

    final static String PARAM_API_KEY = "api_key";
    private static final String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;

    public final static String TMDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    public final static String TMDB_IMAGE_THUMB_SIZE = "w185";

    public static URL buildUrl(String sort) {

        String uriString = TMDB_BASE_URL + sort;

        return getURL(uriString);
    }

    public static URL buildUrl(int movieId, String endpoint) {

        String uriString = TMDB_BASE_URL + TMDB_MOVIE_DETAILS + Integer.toString(movieId) + endpoint;

        return getURL(uriString);
    }

    private static URL getURL(String uriString) {
        Uri builtUri = Uri.parse( uriString ).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, TMDB_API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
