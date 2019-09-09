package com.example.ajisaputrars.madesubmission2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ajisaputrars.madesubmission2.MovieDetailActivity;
import com.example.ajisaputrars.madesubmission2.R;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.RecycleViewHolder> {

    private ArrayList<Movie> movies;
    private Context context;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public FavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Movie> items) {
        movies.clear();
        movies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favorite_movie, viewGroup, false);
        return new FavoriteMovieAdapter.RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, final int i) {
        recycleViewHolder.tvTitle.setText(movies.get(i).getTitle());
        recycleViewHolder.tvOverview.setText(movies.get(i).getOverview());
        Glide.with(context).load(movies.get(i).getPoster_path_string())
                .into(recycleViewHolder.imgPhoto);

        recycleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.DETAIL_MOVIE_EXTRA, getMovies().get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView imgPhoto;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.txt_favorite_movie_name);
            tvOverview = itemView.findViewById(R.id.txt_favorite_movie_description);
            imgPhoto = itemView.findViewById(R.id.img_favorite_movie_photo);
        }
    }
}
