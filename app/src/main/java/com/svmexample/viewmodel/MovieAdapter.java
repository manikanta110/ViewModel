package com.svmexample.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.svmexample.viewmodel.Models.MovieResponse;
import com.svmexample.viewmodel.Models.Result;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private static final String TAG = "MovieAdapter";
    public  static  final  String image_url = "https://image.tmdb.org/t/p/w500/";

    Context mcontext;
    List<Result> resultList ;

    public MovieAdapter(Context mcontext, List<Result> resultList) {
        this.mcontext = mcontext;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.movie_item,parent,false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        Result result = (Result) resultList.get(position);

        holder.title_text.setText(result.getOriginalTitle());
        String url = image_url + result.getPosterPath();
        Glide.with(mcontext)
                .load(url)
                .into(holder.imageView);

        Log.d(TAG, "onBindViewHolder: "+url);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title_text;

        public MovieHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.image_poster);
            title_text = itemView.findViewById(R.id.title_text);

        }
    }
}
