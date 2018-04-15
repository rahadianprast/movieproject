package com.dicoding.associate.movieproject.fragment;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.associate.movieproject.R;
import com.dicoding.associate.movieproject.adapter.UpCommingAdapter;
import com.dicoding.associate.movieproject.model.MovieItem;
import com.dicoding.associate.movieproject.network.UpCommTaskLoader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpCommingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {
    private ArrayList<MovieItem> list;
    private Context context;
    private UpCommingAdapter adapter;

    public UpCommingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_up_comming, container, false);
        adapter = new UpCommingAdapter(getActivity());
        adapter.notifyDataSetChanged();
        RecyclerView rv_upcomm = (RecyclerView)view.findViewById(R.id.rv_upcomm);
        rv_upcomm.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new GridLayoutManager(getActivity(),2);
        rv_upcomm.setLayoutManager(lm);
        rv_upcomm.setHasFixedSize(true);
        Bundle bundle = new Bundle();
        getLoaderManager().initLoader(0,bundle, this);

        return view;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, @Nullable Bundle args) {
        return new UpCommTaskLoader(getContext());
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
