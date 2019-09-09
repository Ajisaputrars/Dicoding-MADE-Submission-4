package com.example.ajisaputrars.madesubmission2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ajisaputrars.madesubmission2.db.TvShowHelper;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;

import java.util.ArrayList;

public class TvShowDetailActivity extends AppCompatActivity {

    public static String DETAIL_TV_SHOW_EXTRA = "detail tv show extra";
    private TvShow tvShow;
    private TextView txtTitle;
    private TextView txtVoteAverage;
    private TextView txtDate;
    private TextView txtOverview;
    private ImageView imgPoster;
    private Menu menu;
    private boolean isFavorite;
    private TvShowHelper tvShowHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        txtTitle = findViewById(R.id.text_detail_title_tv_show);
        txtVoteAverage = findViewById(R.id.text_detail_rating_tv_show);
        txtDate = findViewById(R.id.text_detail_calendar_tv_show);
        txtOverview = findViewById(R.id.text_detail_description_tv_show);
        imgPoster = findViewById(R.id.image_detail_tv_show);

        tvShow = getIntent().getParcelableExtra(DETAIL_TV_SHOW_EXTRA);
        setDetailMovieView();

        tvShowHelper = TvShowHelper.getInstance(getApplicationContext());
        tvShowHelper.open();

        isFavorite = false;
        checkFavorite();
    }

    private void setDetailMovieView() {
        if (tvShow != null){
            setTitle(R.string.title_detail_tv_show);
            txtTitle.setText(tvShow.getName());
            txtVoteAverage.setText(String.valueOf(tvShow.getVote_average()));
            txtDate.setText(tvShow.getFirst_air_date());
            txtOverview.setText(tvShow.getOverview());
            Glide.with(getApplicationContext()).load(tvShow.getPoster_path_string()).into(imgPoster);
        }
        else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;

        setIconFavorite();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_favorite_menu_detail) {
            favoriteButtonPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkFavorite() {
        ArrayList<TvShow> tvShowsInDatabase = tvShowHelper.getAllTvShows();

        for (TvShow tvShow: tvShowsInDatabase){

            if (this.tvShow.getId() == tvShow.getId()){
                isFavorite = true;
            }

            if (isFavorite == true) {
                break;
            }
        }
    }

    private void setIconFavorite(){
        if (isFavorite) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_added_to_favorites));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_add_to_favorites));
        }
    }

    private void favoriteButtonPressed(){
        if (isFavorite) {
            tvShowHelper.deleteTvShow(tvShow.getId());

        } else {
            tvShowHelper.insertTvShow(tvShow);
        }
        isFavorite = !isFavorite;
        setIconFavorite();
    }
}