package com.svmexample.viewmodel.Api;


import com.svmexample.viewmodel.Models.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("movie/popular?api_key=81c4047a8486904dd6cf0787b4b47dc9")
    Call<MovieResponse> getMovies();
}
