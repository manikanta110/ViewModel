package com.svmexample.viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.svmexample.viewmodel.Models.MovieResponse;
import com.svmexample.viewmodel.Models.Result;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    MovieAdapter adapter;
    List<Result> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieModel model = ViewModelProviders.of(this).get(MovieModel.class);


        model.getHeroes().observe(this, new Observer<MovieResponse>() {
            @Override
            public void onChanged(@Nullable MovieResponse movieResponse) {
                adapter = new MovieAdapter(MainActivity.this,movieResponse.getResults());
                Log.d(TAG, "onChanged: "+movieResponse);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}
