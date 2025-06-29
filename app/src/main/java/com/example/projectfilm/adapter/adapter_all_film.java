package com.example.projectfilm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfilm.R;
import com.example.projectfilm.data.model.Movie;
import com.example.projectfilm.ui.admin.DetailActivity;

import java.util.List;

public class adapter_all_film extends RecyclerView.Adapter<adapter_all_film.ViewHolder> {
    List<Movie> filmList;
    Context context;

    public adapter_all_film(Context context, List<Movie> filmList) {
        this.context = context;
        this.filmList = filmList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = filmList.get(position);
        holder.tv_name.setText(movie.getTitle());

        holder.tv_name.setOnClickListener(view -> {
            String movieId = movie.getMovieId();
            if (movieId != null && !movieId.isEmpty()) {
                Toast.makeText(context, "Đã ấn: " + movieId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movieId", movieId);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Lỗi: movieId null!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }
}
