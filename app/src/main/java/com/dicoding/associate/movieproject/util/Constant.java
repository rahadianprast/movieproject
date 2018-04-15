package com.dicoding.associate.movieproject.util;

import com.dicoding.associate.movieproject.BuildConfig;

public class Constant {
    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String IMG_URL = BuildConfig.IMG_URL;
    public static final String API_KEY = BuildConfig.MOVIE_API_KEY;
    //https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en-US
    public static final String NOWPLAYING_URL = BASE_URL + "/movie/now_playing?api_key=" + API_KEY + "&language=en-US";
    //https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language=en-US
    public static final String UPCOMMING_URL = BASE_URL + "/movie/upcoming?api_key=" + API_KEY + "&language=en-US";
    //https://api.themoviedb.org/3/search/movie?api_key=<API KEY ANDA>&language=en-US&query=<INPUTAN NAMA MOVIE>
    public static final String SEARCH_URL = BASE_URL + "/search/movie?api_key=" + API_KEY + "&language=en-US&query=";
}
