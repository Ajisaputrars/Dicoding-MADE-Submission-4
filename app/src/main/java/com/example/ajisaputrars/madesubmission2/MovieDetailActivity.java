package com.example.ajisaputrars.madesubmission2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    public static String DETAIL_MOVIE_EXTRA = "detail tv show extra";

    private Movie movie;
    private TextView txtTitle;
    private TextView txtVoteAverage;
    private TextView txtDate;
    private TextView txtOverview;
    private ImageView imgPoster;

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
    }

    private void setDetailMovieView() {
        setTitle(R.string.title_detail_movie);
        txtTitle.setText(movie.getTitle());
        txtVoteAverage.setText(String.valueOf(movie.getVote_average()));
        txtDate.setText(movie.getRelease_date());
        txtOverview.setText(movie.getOverview());
        Glide.with(getApplicationContext()).load(movie.getPoster_path()).into(imgPoster);
    }
}
