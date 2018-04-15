package com.dicoding.associate.movieproject.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dicoding.associate.movieproject.DetailActivity;
import com.dicoding.associate.movieproject.R;
import com.dicoding.associate.movieproject.model.MovieItem;

import java.util.ArrayList;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<MovieItem> mMovie = new ArrayList<>();

    public ArrayList<MovieItem> getmMovie() {
        return mMovie;
    }

    public void setmMovie(ArrayList<MovieItem> mMovie) {
        this.mMovie = mMovie;
        notifyDataSetChanged();
    }

    public NowPlayingAdapter(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playnow, parent, false);
        return new MovieViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        holder.tv_title.setText(getmMovie().get(position).getTitle());
        holder.tv_vote_average.setText(getmMovie().get(position).getVote());
        holder.tv_release_date.setText(getmMovie().get(position).getRelease_date());
        holder.tv_overview.setText(getmMovie().get(position).getOverview());
        Glide.with(context)
                .load(getmMovie().get(position).getImg_poster())
                .into(holder.img_poster);
        holder.btn_shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),v.getResources().getString(R.string.shared),Toast.LENGTH_SHORT).show();

            }
        });
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_vote_average;
        TextView tv_release_date;
        TextView tv_overview;
        Button btn_shared;
        Button btn_detail;
        public MovieViewHolder(View itemView) {
            super(itemView);
            img_poster = (ImageView)itemView.findViewById(R.id.img_poster);
            tv_title = (TextView)itemView.findViewById(R.id.list_title);
            tv_vote_average = (TextView)itemView.findViewById(R.id.list_vote_average);
            tv_release_date = (TextView)itemView.findViewById(R.id.list_release_date);
            tv_overview = (TextView)itemView.findViewById(R.id.list_overview);
            btn_shared = (Button)itemView.findViewById(R.id.btn_set_share);
            btn_detail = (Button)itemView.findViewById(R.id.btn_detail);
        }
    }
}
