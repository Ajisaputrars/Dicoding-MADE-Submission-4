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
import com.example.ajisaputrars.madesubmission2.adapter.TvShowAdapter;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;
import com.example.ajisaputrars.madesubmission2.viewmodel.TvShowViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private ArrayList<TvShow> tvShows = new ArrayList<>();
    TvShowAdapter adapter;
    ProgressDialog progressDialog;

    TvShowViewModel tvShowViewModel;

    public TvShowFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loading_text));

        if (tvShows.size() <= 0) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvShows().observe(this, getTvShow);
        tvShowViewModel.setListTvShows("");

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
            if (tvShowItems != null) {
                tvShows = tvShowItems;
                adapter.setData(tvShows);
            }
            progressDialog.dismiss();
        }
    };
}
