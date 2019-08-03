package com.example.ajisaputrars.madesubmission2.model.movie;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.ajisaputrars.madesubmission2.network.ApiConfig;
import com.example.ajisaputrars.madesubmission2.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {

    private static MovieRepo movieRepo;
    private Context context;

    public static MovieRepo getInstance() {
        if (movieRepo == null) {
            movieRepo = new MovieRepo();
        }
        return movieRepo;
    }

    private ApiService apiService;

    public MovieRepo() {
        apiService = ApiConfig.getInitRetrofit();
    }

    public MutableLiveData<ResponseMovie> getMovies() {
        final MutableLiveData<ResponseMovie> moviData = new MutableLiveData<>();
        apiService.getDataMovie().enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                if ((response.body() != null ? response.body().getPage() : 0) > 0) {
                    moviData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                moviData.setValue(null);
            }
        });
        return moviData;
    }
}