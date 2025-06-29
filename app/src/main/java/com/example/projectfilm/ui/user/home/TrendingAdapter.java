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

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.MovieViewHolder> {
    private List<Movie> movieList = new ArrayList<>();
=======
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.MovieViewHolder> {

    private List<Movie> movieList = new ArrayList<>();
    private List<Movie> originalList = new ArrayList<>();
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    private Context context;

    public TrendingAdapter(Context context) {
        this.context = context;
    }

    public void setMovieList(List<Movie> list) {
<<<<<<< HEAD
        movieList = list;
        notifyDataSetChanged();
    }

=======
        this.originalList = list;
        this.movieList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    /**
     * Lọc danh sách phim theo từ khóa (bỏ dấu + không phân biệt hoa thường).
     */
    public void filter(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            movieList = new ArrayList<>(originalList);
        } else {
            String keywordNormalized = normalizeText(keyword);
            List<Movie> filtered = new ArrayList<>();
            for (Movie movie : originalList) {
                String title = movie.getTitle();
                if (title != null) {
                    String titleNormalized = normalizeText(title);
                    if (titleNormalized.contains(keywordNormalized)) {
                        filtered.add(movie);
                    }
                }
            }
            movieList = filtered;
        }
        notifyDataSetChanged();
    }

    /**
     * Bỏ dấu tiếng Việt + chuyển chữ thường
     */
    public static String normalizeText(String str) {
        if (str == null) return "";
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replaceAll("đ", "d").replaceAll("Đ", "D");
        return temp.toLowerCase();
    }

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.moviePoster);
<<<<<<< HEAD
            movieTitle = itemView.findViewById(R.id.movieTitle); // ✅ Đã thêm dòng này
=======
            movieTitle = itemView.findViewById(R.id.movieTitle);
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
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

<<<<<<< HEAD
        // ✅ Kiểm tra và log movieId
        String movieId = movie.getMovieId();
        Log.d("TrendingAdapter", "Clicked movieId: " + movieId);

        // ✅ Kiểm tra posterUrl
=======
        String movieId = movie.getMovieId();
        Log.d("TrendingAdapter", "Clicked movieId: " + movieId);

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
        String posterUrl = movie.getPosterUrl();
        if (posterUrl != null && !posterUrl.isEmpty()) {
            Glide.with(context)
                    .load(posterUrl)
                    .placeholder(R.drawable.ic_menu_gallery)
                    .into(holder.moviePoster);
        } else {
            Log.e("TrendingAdapter", "posterUrl is null or empty for movieId: " + movieId);
        }

<<<<<<< HEAD
        // ✅ Hiển thị tên phim nếu có
=======
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
        if (holder.movieTitle != null && movie.getTitle() != null) {
            holder.movieTitle.setText(movie.getTitle());
        }

        holder.itemView.setOnClickListener(v -> {
            MovieDetailFragment fragment = new MovieDetailFragment();
            Bundle bundle = new Bundle();
<<<<<<< HEAD
            bundle.putString("movieId", movieId); // ✅ Key chính xác
=======
            bundle.putString("movieId", movieId);
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8

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
<<<<<<< HEAD
}
=======
}
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
