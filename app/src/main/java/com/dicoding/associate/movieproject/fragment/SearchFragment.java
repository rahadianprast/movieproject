package com.dicoding.associate.movieproject.fragment;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.dicoding.associate.movieproject.R;
import com.dicoding.associate.movieproject.adapter.SearchAdapter;
import com.dicoding.associate.movieproject.model.MovieItem;
import com.dicoding.associate.movieproject.network.SearchTaskLoader;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {
    private ArrayList<MovieItem> list;
    private SearchAdapter adapter;
    EditText editMovie;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        editMovie = (EditText)view.findViewById(R.id.edit_movie);
        addTextListener();
        String film = editMovie.getText().toString();
        adapter = new SearchAdapter(getActivity());
        adapter.notifyDataSetChanged();
        RecyclerView rv_search = (RecyclerView)view.findViewById(R.id.rv_search);
        rv_search.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rv_search.setLayoutManager(lm);
        rv_search.setHasFixedSize(true);

        return view;
    }

    private void addTextListener() {
        editMovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String film = editMovie.getText().toString();
                if (TextUtils.isEmpty(film))return;
                Bundle bundle = new Bundle();
                bundle.putString(EXTRAS_MOVIE,film);
                getLoaderManager().restartLoader(0, bundle, SearchFragment.this);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String film = editMovie.getText().toString();
            if (TextUtils.isEmpty(film))return;
            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE,film);
            getLoaderManager().restartLoader(0, bundle, SearchFragment.this);

        }
    };

    @NonNull
    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, @Nullable Bundle args) {
        String judulFilm = "";
        if (args !=null){
            judulFilm = args.getString(EXTRAS_MOVIE);
        }
        return new SearchTaskLoader(getActivity(), judulFilm);
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
