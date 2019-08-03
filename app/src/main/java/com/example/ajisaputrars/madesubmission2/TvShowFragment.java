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
import android.widget.ProgressBar;

import com.example.ajisaputrars.madesubmission2.adapter.TvShowAdapter;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;
import com.example.ajisaputrars.madesubmission2.viewmodel.MovieViewModel;
import com.example.ajisaputrars.madesubmission2.viewmodel.TvShowViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private TvShowViewModel tvShowViewModel;
    TvShowAdapter adapter;
    ProgressDialog progressDialog;


    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        if (tvShows.isEmpty()) {
//            tvShows.addAll(TvShowData.getTvShowListData());
//        }

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading dulu bos");

        if (tvShows.size() <= 0) {
            progressDialog.show();
        }

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        Log.d("ViewModelProviders", "TvShowFragment Jalan");

        tvShowViewModel.getTvShows().observe(this, getTvShow);
        Log.d("getTvShows().observe", "TvShowFragment Jalan");

        tvShowViewModel.setListTvShows("");
        Log.d("setMovies", "TvShowFragment Jalan");

        RecyclerView rvFragmentTvShows = view.findViewById(R.id.rv_fragment_tv_show);
        rvFragmentTvShows.setHasFixedSize(true);
        rvFragmentTvShows.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new TvShowAdapter(view.getContext());
        adapter.setTvShows(tvShows);
        rvFragmentTvShows.setAdapter(adapter);
    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShowItems) {
            progressDialog.show();

            Log.d("onChange Observer", "TvShowFragment Jalan");
            if (tvShowItems != null) {
                tvShows = tvShowItems;
                adapter.setData(tvShows);
            }
            progressDialog.dismiss();
        }
    };
}
