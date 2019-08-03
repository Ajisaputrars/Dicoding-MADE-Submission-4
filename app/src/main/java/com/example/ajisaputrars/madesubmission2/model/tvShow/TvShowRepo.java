package com.example.ajisaputrars.madesubmission2.model.tvShow;

import android.arch.lifecycle.MutableLiveData;

import com.example.ajisaputrars.madesubmission2.network.ApiConfig;
import com.example.ajisaputrars.madesubmission2.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRepo {

    private static TvShowRepo movieRepo;

    public static TvShowRepo getInstance() {
        if (movieRepo == null) {
            movieRepo = new TvShowRepo();
        }
        return movieRepo;
    }

    private ApiService apiService;

    public TvShowRepo() {
        apiService = ApiConfig.getInitRetrofit();
    }

    public MutableLiveData<ResponseTvShow> getTVShow() {
        final MutableLiveData<ResponseTvShow> tvShowData = new MutableLiveData<>();
        apiService.getTvShowData().enqueue(new Callback<ResponseTvShow>() {
            @Override
            public void onResponse(Call<ResponseTvShow> call, Response<ResponseTvShow> response) {
                if ((response.body() != null ? response.body().getPage() : 0) > 0) {
                    tvShowData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseTvShow> call, Throwable t) {
                tvShowData.setValue(null);
            }
        });
        return tvShowData;
    }
}
