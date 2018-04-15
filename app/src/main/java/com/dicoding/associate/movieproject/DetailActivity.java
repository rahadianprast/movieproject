package com.dicoding.associate.movieproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //recieve data

        String title = getIntent().getExtras().getString("movie_title");
        String vote = getIntent().getExtras().getString("movie_vote");
        String release = getIntent().getExtras().getString("movie_release");
        String overview = getIntent().getExtras().getString("movie_overview");
        String poster = getIntent().getExtras().getString("movie_img");
        // to activity
        ImageView img_poster = findViewById(R.id.aa_thumbnail);
        TextView tv_title = findViewById(R.id.aa_title);
        TextView tv_vote_average = findViewById(R.id.aa_vote);
        TextView tv_release_date= findViewById(R.id.aa_releseasedate);
        TextView tv_overview = findViewById(R.id.aa_overview);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbardetail);
        setSupportActionBar(toolbar);
        // join
        tv_title.setText(title);
        tv_vote_average.setText(vote);
        tv_release_date.setText(release);
        tv_overview.setText(overview);
        Glide.with(this)
                .load(poster)
                .into(img_poster);
    }
}
