package com.example.projectfilm.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfilm.R;
import com.example.projectfilm.adapter.adapter_all_film;
import com.example.projectfilm.data.model.Movie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MovieManage extends AppCompatActivity {
    RecyclerView recyclerView;
    AppCompatButton btn_add;
    adapter_all_film adapter;
    ImageView btn_back;
    List<Movie> filmList = new ArrayList<>();
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_manage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        btn_add = findViewById(R.id.btn_add);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(v -> finish());
        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(MovieManage.this, AddFilm.class);
            startActivity(intent);
        });

        adapter = new adapter_all_film(this, filmList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        listenToMovieUpdates();
    }
    private void listenToMovieUpdates() {
        db.collection("movies")
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null) return;
                    if (snapshots != null) {
                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            Movie movie = dc.getDocument().toObject(Movie.class);
                            if (movie != null) {
                                movie.setMovieId(dc.getDocument().getId());
                                switch (dc.getType()) {
                                    case ADDED:
                                        filmList.add(movie);
                                        break;
                                    case MODIFIED:
                                        // Tìm vị trí cũ và thay thế
                                        for (int i = 0; i < filmList.size(); i++) {
                                            if (filmList.get(i).getMovieId().equals(movie.getMovieId())) {
                                                filmList.set(i, movie);
                                                break;
                                            }
                                        }
                                        break;
                                    case REMOVED:
                                        // Xóa khỏi list
                                        for (int i = 0; i < filmList.size(); i++) {
                                            if (filmList.get(i).getMovieId().equals(movie.getMovieId())) {
                                                filmList.remove(i);
                                                break;
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
