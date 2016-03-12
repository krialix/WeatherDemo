package com.krialix.weatherdemo.rest;

import com.krialix.weatherdemo.model.OpenWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {

    @GET("weather")
    Call<OpenWeatherResponse> getWeather(@Query("q") String location,
                                         @Query("appid") String appid,
                                         @Query("units") String unit);
}
