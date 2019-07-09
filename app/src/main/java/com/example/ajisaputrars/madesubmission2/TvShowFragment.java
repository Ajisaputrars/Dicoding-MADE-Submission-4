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
public class TvShowFragment extends Fragment {

    private ArrayList<TvShow> tvShows = new ArrayList<>();


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
        if (tvShows.isEmpty()) {
            tvShows.addAll(TvShowData.getTvShowListData());
        }
        RecyclerView rvFragmentTvShows = view.findViewById(R.id.rv_fragment_tv_show);
        rvFragmentTvShows.setHasFixedSize(true);
        rvFragmentTvShows.setLayoutManager(new LinearLayoutManager(view.getContext()));

        TvShowAdapter adapter = new TvShowAdapter(view.getContext());
        adapter.setTvShows(tvShows);
        rvFragmentTvShows.setAdapter(adapter);
    }
}
