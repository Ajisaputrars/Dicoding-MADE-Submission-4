package com.example.ajisaputrars.madesubmission2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import com.example.ajisaputrars.madesubmission2.adapter.MovieAdapter;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;
import com.example.ajisaputrars.madesubmission2.viewmodel.MovieViewModel;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private ProgressDialog progressDialog;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieViewModel movieViewModel;
    private MovieAdapter adapter;

    public MovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loading_text));

        if (movies.size() <= 0) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovie);
        movieViewModel.setMovies();

        RecyclerView rvFragmentMovies = view.findViewById(R.id.rv_fragment_movie);
        rvFragmentMovies.setHasFixedSize(true);
        rvFragmentMovies.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MovieAdapter(view.getContext());
        adapter.setMovies(movies);
        rvFragmentMovies.setAdapter(adapter);
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movieItems) {
            if (movieItems != null) {
                movies = movieItems;
                adapter.setData(movieItems);
            }
            progressDialog.dismiss();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }
}
