package com.example.ajisaputrars.madesubmission2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MovieFragment movieFragment;
    private TvShowFragment tvShowFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        movieFragment = new MovieFragment();
        tvShowFragment = new TvShowFragment();

        setToMovieFragment();
    }

    private void setToMovieFragment() {
        setTitle(getResources().getString(R.string.title_movie));

        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());

        if (!(fragment instanceof MovieFragment)) {
            fragmentTransaction.replace(
                    R.id.linear_layout_container,
                    movieFragment,
                    MovieFragment.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }

    private void setToTvShowFragment() {
        setTitle(getResources().getString(R.string.title_tv_show));

        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(TvShowFragment.class.getSimpleName());

        if (!(fragment instanceof TvShowFragment)) {
            fragmentTransaction.replace(
                    R.id.linear_layout_container,
                    tvShowFragment,
                    TvShowFragment.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    setToMovieFragment();
                    return true;
                case R.id.navigation_tv_show:
                    setToTvShowFragment();
                    return true;
            }
            return false;
        }
    };
}
