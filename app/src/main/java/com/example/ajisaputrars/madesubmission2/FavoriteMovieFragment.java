package com.example.ajisaputrars.madesubmission2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ajisaputrars.madesubmission2.adapter.FavoriteMovieAdapter;
import com.example.ajisaputrars.madesubmission2.db.MovieHelper;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;

import java.util.ArrayList;

public class FavoriteMovieFragment extends Fragment {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieHelper movieHelper;
    private FavoriteMovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieHelper = MovieHelper.getInstance(view.getContext());
        movieHelper.open();

        RecyclerView rvFavoriteFragmentMovies = view.findViewById(R.id.rv_fragment_favorite_movie);
        rvFavoriteFragmentMovies.setHasFixedSize(true);
        rvFavoriteFragmentMovies.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new FavoriteMovieAdapter(view.getContext());
        adapter.setMovies(movies);
        rvFavoriteFragmentMovies.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        movies = movieHelper.getAllMovies();
        adapter.setData(movies);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
