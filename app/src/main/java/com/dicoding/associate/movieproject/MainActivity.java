package com.dicoding.associate.movieproject;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dicoding.associate.movieproject.fragment.FragmentAdapter;
import com.dicoding.associate.movieproject.fragment.NowPlayingFragment;
import com.dicoding.associate.movieproject.fragment.SearchFragment;
import com.dicoding.associate.movieproject.fragment.UpCommingFragment;

public class MainActivity extends AppCompatActivity {
     static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout_id);
        ViewPager viewPager = (ViewPager)findViewById(R.id.vp_main);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.AddFragment(new NowPlayingFragment(),getResources().getString(R.string.nowplaying));
        adapter.AddFragment(new UpCommingFragment(), getResources().getString(R.string.upcomming));
        adapter.AddFragment(new SearchFragment(), getResources().getString(R.string.search));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
