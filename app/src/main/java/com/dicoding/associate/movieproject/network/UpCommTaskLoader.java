package com.dicoding.associate.movieproject.network;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.dicoding.associate.movieproject.model.MovieItem;
import com.dicoding.associate.movieproject.util.Constant;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class UpCommTaskLoader extends AsyncTaskLoader<ArrayList<MovieItem>> {
    private ArrayList<MovieItem> mData;
    private Boolean mHasResult = false;
    public static final String STAT_ASYNC = "AsynTaskLoader";

    public UpCommTaskLoader(@NonNull Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        Log.d(STAT_ASYNC, "STATUS : onStartLoading");
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(ArrayList<MovieItem> data) {
        Log.d(STAT_ASYNC, "STATUS : onDelivery");
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        Log.d(STAT_ASYNC, "STATUS : onReset");
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResource(mData);
            mData = null;
            mHasResult =false;
        }
    }

    private void onReleaseResource(ArrayList<MovieItem> mData) {
    }



    @Override
    public ArrayList<MovieItem> loadInBackground() {
        Log.d(STAT_ASYNC, "STATUS : MeloadBackground");
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItem> movieItemArray = new ArrayList<>();
        //String url = Constant.BASE_URL + "/movie/upcoming?api_key=" + Constant.API_KEY + "&language=en-US";
        client.get(Constant.UPCOMMING_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i =0; i< list.length();i++){
                        JSONObject movie = list.getJSONObject(i);
                        MovieItem movieItems = new MovieItem(movie);
                        movieItemArray.add(movieItems);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemArray;
    }
}
