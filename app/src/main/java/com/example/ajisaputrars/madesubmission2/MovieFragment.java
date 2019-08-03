package com.example.ajisaputrars.madesubmission2;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;


import com.example.ajisaputrars.madesubmission2.adapter.MovieAdapter;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;
import com.example.ajisaputrars.madesubmission2.model.movie.ResponseMovie;
import com.example.ajisaputrars.madesubmission2.model.movie.ResultsItemMovie;
import com.example.ajisaputrars.madesubmission2.viewmodel.MainViewModel;
import com.example.ajisaputrars.madesubmission2.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ProgressDialog progressDialog;

    private ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<ResultsItemMovie> resultsItemMovieArrayList = new ArrayList<>();


    MovieViewModel movieViewModel;
    MovieAdapter adapter;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading dulu bos");

        if (movies.size() <= 0) {
            progressDialog.show();
        }
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        Log.d("ViewModelProviders", "Jalan");

        movieViewModel.getMovies().observe(this, getMovie);
        Log.d("getMovies().observe", "Jalan");

        movieViewModel.setMovies("");
        Log.d("setMovies", "Jalan");

        RecyclerView rvFragmentMovies = view.findViewById(R.id.rv_fragment_movie);
        rvFragmentMovies.setHasFixedSize(true);
        rvFragmentMovies.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new MovieAdapter(view.getContext());

        Log.d("MOVIES", "Movienya ada " + movies.size());
        adapter.setMovies(movies);

        rvFragmentMovies.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movieItems) {
            Log.d("onChange Observer", "Jalan");
            if (movieItems != null) {
                movies = movieItems;
                adapter.setData(movieItems);
            }
            progressDialog.dismiss();
        }
    };

    private void showProgressBar(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Test");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void dismissProgressBar(){
        progressDialog.dismiss();
    }
}
