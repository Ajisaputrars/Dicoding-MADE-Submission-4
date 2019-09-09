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
import com.example.ajisaputrars.madesubmission2.R;
import com.example.ajisaputrars.madesubmission2.TvShowDetailActivity;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;

import java.util.ArrayList;

public class FavoriteTvShowAdapter extends RecyclerView.Adapter<FavoriteTvShowAdapter.RecycleViewHolder> {

    private ArrayList<TvShow> tvShows;
    private Context context;

    public ArrayList<TvShow> getMovies() {
        return tvShows;
    }

    public void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    public FavoriteTvShowAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<TvShow> items) {
        tvShows.clear();
        tvShows.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favorite_tv_show, viewGroup, false);
        return new FavoriteTvShowAdapter.RecycleViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, final int i) {
        recycleViewHolder.tvTitle.setText(tvShows.get(i).getName());
        recycleViewHolder.tvOverview.setText(tvShows.get(i).getOverview());
        Glide.with(context).load(tvShows.get(i).getPoster_path_string())
                .into(recycleViewHolder.imgPhoto);

        recycleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TvShowDetailActivity.class);
                intent.putExtra(TvShowDetailActivity.DETAIL_TV_SHOW_EXTRA, getMovies().get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView imgPhoto;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.txt_favorite_tv_show_name);
            tvOverview = itemView.findViewById(R.id.txt_favorite_tv_show_description);
            imgPhoto = itemView.findViewById(R.id.img_favorite_tv_show_photo);
        }
    }
}
