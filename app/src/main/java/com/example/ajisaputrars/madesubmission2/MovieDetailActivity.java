package com.example.ajisaputrars.madesubmission2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static String DETAIL_MOVIE_EXTRA = "detail tv show extra";
    public static String DETAIL_FAVORITE_MOVIE_EXTRA = "detail tv show extra";

    private Movie movie;
    private TextView txtTitle;
    private TextView txtVoteAverage;
    private TextView txtDate;
    private TextView txtOverview;
    private ImageView imgPoster;

    private Menu menu;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        txtTitle = findViewById(R.id.text_detail_title_movie);
        txtVoteAverage = findViewById(R.id.text_detail_rating_movie);
        txtDate = findViewById(R.id.text_detail_calendar_movie);
        txtOverview = findViewById(R.id.text_detail_description_movie);
        imgPoster = findViewById(R.id.image_detail_movie);

        movie = getIntent().getParcelableExtra(DETAIL_MOVIE_EXTRA);

        setDetailMovieView();

        isFavorite = false;
    }

    private void setDetailMovieView() {
        if (movie != null){
            setTitle(R.string.title_detail_movie);
            txtTitle.setText(movie.getTitle());
            txtVoteAverage.setText(String.valueOf(movie.getVote_average()));
            txtDate.setText(movie.getRelease_date());
            txtOverview.setText(movie.getOverview());
            Glide.with(getApplicationContext()).load(movie.getPoster_path_string()).into(imgPoster);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_favorite_menu_detail) {
            isFavorite = !isFavorite;
            setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFavorite(){
        if (isFavorite) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_added_to_favorites));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_add_to_favorites));
        }
    }
}
