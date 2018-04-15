package com.dicoding.associate.movieproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.associate.movieproject.DetailActivity;
import com.dicoding.associate.movieproject.R;
import com.dicoding.associate.movieproject.model.MovieItem;

import java.util.ArrayList;

public class UpCommingAdapter extends RecyclerView.Adapter<UpCommingAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<MovieItem> mMovie = new ArrayList<>();

    public UpCommingAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<MovieItem> getmMovie() {
        return mMovie;
    }

    public void setmMovie(ArrayList<MovieItem> mMovie) {
        this.mMovie = mMovie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcomming, parent,false);
        return new MovieViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        holder.tv_title.setText(getmMovie().get(position).getTitle());
        Glide.with(context)
                .load(getmMovie().get(position).getImg_poster())
                .into(holder.img_poster);
        holder.frame_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("movie_title", mMovie.get(position).getTitle());
                i.putExtra("movie_vote", mMovie.get(position).getVote());
                i.putExtra("movie_release", mMovie.get(position).getRelease_date());
                i.putExtra("movie_overview", mMovie.get(position).getOverview());
                i.putExtra("movie_img", mMovie.get(position).getImg_poster());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getmMovie().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView tv_title;
        CardView frame_container;

        public MovieViewHolder(View itemView) {
            super(itemView);
            img_poster = (ImageView)itemView.findViewById(R.id.thumbnail_up);
            tv_title = (TextView)itemView.findViewById(R.id.title_up);
            frame_container = (CardView) itemView.findViewById(R.id.card_view);

        }
    }
}
