package com.example.ajisaputrars.madesubmission2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ArrayList<Movie> movies = new ArrayList<>();

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
        if (movies.isEmpty()) {
            movies.addAll(MovieData.getMovieListData());
        }
        RecyclerView rvFragmentMovies = view.findViewById(R.id.rv_fragment_movie);
        rvFragmentMovies.setHasFixedSize(true);
        rvFragmentMovies.setLayoutManager(new LinearLayoutManager(view.getContext()));

        MovieAdapter adapter = new MovieAdapter(view.getContext());
        adapter.setMovies(movies);
        rvFragmentMovies.setAdapter(adapter);

//        rvFragmentMovies.addOnItemTouchListener(new RecyclerViewClick);
    }
}
