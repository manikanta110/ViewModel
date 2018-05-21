package com.svmexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.svmexample.viewmodel.Api.Service;
import com.svmexample.viewmodel.Models.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieModel extends ViewModel {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private MutableLiveData<MovieResponse> movielist;
    List<MovieResponse> responseList;

    public LiveData<MovieResponse> getHeroes() {
        //if the list is null
        if (movielist == null) {
            movielist = new MutableLiveData<MovieResponse>();
            //we will load it asynchronously from server in this method
            loadMovies();
        }

        //finally we will return the list
        return movielist;
    }

    private void loadMovies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service api = retrofit.create(Service.class);
        Call<MovieResponse> call = api.getMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {
                movielist.setValue(response.body());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


    }

}
