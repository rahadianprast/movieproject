package com.dicoding.associate.movieproject.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.associate.movieproject.R;
import com.dicoding.associate.movieproject.adapter.NowPlayingAdapter;
import com.dicoding.associate.movieproject.model.MovieItem;
import com.dicoding.associate.movieproject.network.NowPlayTaskLoader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {
    private ArrayList<MovieItem> list;
    private Context context;
    private NowPlayingAdapter adapter;


    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_now_playing, container, false);
        adapter = new NowPlayingAdapter(getActivity());
        adapter.notifyDataSetChanged();
        // setup recycleview
        RecyclerView rv_nowplay = (RecyclerView)view.findViewById(R.id.rv_nowplay);
        rv_nowplay.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rv_nowplay.setLayoutManager(lm);
        rv_nowplay.setHasFixedSize(true);
        Bundle bundle = new Bundle();
        getLoaderManager().initLoader(0, bundle, this);
        return view;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NowPlayTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {
        adapter.setmMovie(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItem>> loader) {
        adapter.setmMovie(null);
    }
}
