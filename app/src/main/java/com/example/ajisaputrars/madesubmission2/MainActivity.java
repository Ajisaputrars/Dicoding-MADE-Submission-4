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
    private FavoriteMovieFragment favoriteMovieFragment;
    private FavoriteTvShowFragment favoriteTvShowFragment;
    private String title;
    private final String STATE_TITLE = "state_string";
    private final String STATE_MODE = "state_mode";


    private int mode = R.id.navigation_movie ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = getResources().getString(R.string.title_movie);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        movieFragment = new MovieFragment();
        tvShowFragment = new TvShowFragment();

        favoriteMovieFragment = new FavoriteMovieFragment();
        favoriteTvShowFragment = new FavoriteTvShowFragment();

        if (savedInstanceState != null){
            mode = savedInstanceState.getInt(STATE_MODE);
            title = savedInstanceState.getString(STATE_TITLE);

            if (mode == R.id.navigation_movie){
                setToMovieFragment();
            } else if (mode == R.id.navigation_tv_show){
                setToTvShowFragment();
            } else if (mode == R.id.navigation_favorite_movie){
                setToFavoriteMovieFragment();
            }
            else {
                setToFavoriteTvShowFragment();
            }
        } else {
            setToMovieFragment();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MODE, mode);
        outState.putString(STATE_TITLE, title);
    }

    private void setToMovieFragment() {
        setTitle(title);

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
        setTitle(title);

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

    private void setToFavoriteTvShowFragment() {
        setTitle(title);

        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FavoriteTvShowFragment.class.getSimpleName());

        if (!(fragment instanceof FavoriteTvShowFragment)) {
            fragmentTransaction.replace(
                    R.id.linear_layout_container,
                    favoriteTvShowFragment,
                    FavoriteTvShowFragment.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }

    private void setToFavoriteMovieFragment() {
        setTitle(title);

        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FavoriteMovieFragment.class.getSimpleName());

        if (!(fragment instanceof FavoriteMovieFragment)) {
            fragmentTransaction.replace(
                    R.id.linear_layout_container,
                    favoriteMovieFragment,
                    FavoriteMovieFragment.class.getSimpleName()
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
                    title = getResources().getString(R.string.title_movie);
                    mode = item.getItemId();

                    setToMovieFragment();
                    return true;
                case R.id.navigation_tv_show:
                    title = getResources().getString(R.string.title_tv_show);
                    mode = item.getItemId();

                    setToTvShowFragment();
                    return true;

                case R.id.navigation_favorite_movie:
                    title = getResources().getString(R.string.title_favorite_movie);
                    mode = item.getItemId();

                    setToFavoriteMovieFragment();
                    return true;

                case R.id.navigation_favorite_tv_show:
                    title = getResources().getString(R.string.title_favorite_tv_show);
                    mode = item.getItemId();

                    setToFavoriteTvShowFragment();
                    return true;
            }
            return false;
        }
    };
}
