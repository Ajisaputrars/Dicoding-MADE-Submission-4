package com.example.ajisaputrars.madesubmission2.network;

import com.example.ajisaputrars.madesubmission2.Constant;
import com.example.ajisaputrars.madesubmission2.model.movie.ResponseMovie;
import com.example.ajisaputrars.madesubmission2.model.tvShow.ResponseTvShow;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("discover/movie?api_key=" + Constant.API_KEY + "&language=en-US")
    Call<ResponseMovie> getDataMovie();

    @GET("discover/tv?api_key=" + Constant.API_KEY + "&language=en-US")
    Call<ResponseTvShow> getTvShowData();
}
