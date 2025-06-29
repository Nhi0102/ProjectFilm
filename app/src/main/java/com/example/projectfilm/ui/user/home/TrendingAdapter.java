package com.example.projectfilm.ui.user.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectfilm.R;
import com.example.projectfilm.data.model.Movie;
import com.example.projectfilm.ui.user.movie.MovieDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.MovieViewHolder> {
    private List<Movie> movieList = new ArrayList<>();
    private Context context;

    public TrendingAdapter(Context context) {
        this.context = context;
    }

    public void setMovieList(List<Movie> list) {
        movieList = list;
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            movieTitle = itemView.findViewById(R.id.movieTitle); // ✅ Đã thêm dòng này
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        // ✅ Kiểm tra và log movieId
        String movieId = movie.getMovieId();
        Log.d("TrendingAdapter", "Clicked movieId: " + movieId);

        // ✅ Kiểm tra posterUrl
        String posterUrl = movie.getPosterUrl();
        if (posterUrl != null && !posterUrl.isEmpty()) {
            Glide.with(context)
                    .load(posterUrl)
                    .placeholder(R.drawable.ic_menu_gallery)
                    .into(holder.moviePoster);
        } else {
            Log.e("TrendingAdapter", "posterUrl is null or empty for movieId: " + movieId);
        }

        // ✅ Hiển thị tên phim nếu có
        if (holder.movieTitle != null && movie.getTitle() != null) {
            holder.movieTitle.setText(movie.getTitle());
        }

        holder.itemView.setOnClickListener(v -> {
            MovieDetailFragment fragment = new MovieDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("movieId", movieId); // ✅ Key chính xác

            if (context instanceof AppCompatActivity) {
                fragment.setArguments(bundle);

                if (context instanceof com.example.projectfilm.MainActivity) {
                    ((com.example.projectfilm.MainActivity) context).openFragment(fragment);
                } else {
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
