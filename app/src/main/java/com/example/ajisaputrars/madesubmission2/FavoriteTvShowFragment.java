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
import com.example.ajisaputrars.madesubmission2.adapter.FavoriteTvShowAdapter;
import com.example.ajisaputrars.madesubmission2.db.TvShowHelper;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;

import java.util.ArrayList;

public class FavoriteTvShowFragment extends Fragment {

    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private TvShowHelper tvShowHelper;
    private FavoriteTvShowAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvShowHelper = TvShowHelper.getInstance(view.getContext());
        tvShowHelper.open();

        RecyclerView rvFavoriteFragmentTvShows = view.findViewById(R.id.rv_fragment_favorite_tv_show);
        rvFavoriteFragmentTvShows.setHasFixedSize(true);
        rvFavoriteFragmentTvShows.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new FavoriteTvShowAdapter(view.getContext());
        adapter.setTvShows(tvShows);
        rvFavoriteFragmentTvShows.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvShows = tvShowHelper.getAllTvShows();
        adapter.setData(tvShows);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvShowHelper.close();
    }
}
