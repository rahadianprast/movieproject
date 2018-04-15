package com.dicoding.associate.movieproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.associate.movieproject.DetailActivity;
import com.dicoding.associate.movieproject.R;
import com.dicoding.associate.movieproject.model.MovieItem;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context mContext;
    private ArrayList<MovieItem> mMovie = new ArrayList<>();

    public ArrayList<MovieItem> getmMovie() {
        return mMovie;
    }

    public void setmMovie(ArrayList<MovieItem> mMovie) {
        this.mMovie = mMovie;
        notifyDataSetChanged();
    }

    public SearchAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {
        holder.tv_title.setText(mMovie.get(position).getTitle());
        holder.tv_release_date.setText(mMovie.get(position).getRelease_date());
        Glide.with(mContext)
                .load(mMovie.get(position).getImg_poster())
                .into(holder.img_poster);
        holder.frame_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra("movie_title", mMovie.get(position).getTitle());
                i.putExtra("movie_vote", mMovie.get(position).getVote());
                i.putExtra("movie_release", mMovie.get(position).getRelease_date());
                i.putExtra("movie_overview", mMovie.get(position).getOverview());
                i.putExtra("movie_img", mMovie.get(position).getImg_poster());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mMovie == null) return 0;
        return mMovie.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView tv_title;
        TextView tv_release_date;
        RelativeLayout frame_container;

        public SearchViewHolder(View itemView) {
            super(itemView);
            img_poster = (ImageView)itemView.findViewById(R.id.poster_search);
            tv_title = (TextView)itemView.findViewById(R.id.title_search);
            tv_release_date = (TextView)itemView.findViewById(R.id.release_search);
            frame_container = (RelativeLayout)itemView.findViewById(R.id.container);
        }
    }
}
