package com.example.projectfilm.ui.user.booking;

import android.os.Bundle;
import android.widget.Toast;
<<<<<<< HEAD

=======
import android.util.Log;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfilm.R;
import com.google.firebase.auth.FirebaseAuth;
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseUser;
=======
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
<<<<<<< HEAD
    private BookingHistoryAdapter adapter;
    private List<Booking> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        recyclerView = findViewById(R.id.bookingRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingList = new ArrayList<>();
        adapter = new BookingHistoryAdapter(this, bookingList);
        recyclerView.setAdapter(adapter);
=======
    private BookingAdapter bookingAdapter;
    private List<Booking> bookingList;

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ACTIVITY_LIFECYCLE", "BookingHistoryActivity started");
        setContentView(R.layout.activity_booking_history);

        recyclerView = findViewById(R.id.recyclerViewBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookingList = new ArrayList<>();
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8

        loadBookingHistory();
    }

    private void loadBookingHistory() {
<<<<<<< HEAD
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return;

        FirebaseFirestore.getInstance()
                .collection("bookings")
                .whereEqualTo("userId", user.getUid()) // 🔍 Chỉ lấy vé của người dùng này
=======
        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "Bạn chưa đăng nhập!", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(this, "Bạn chưa đăng nhập!", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại trước đó an toàn
            return;
        }
        String userId = currentUser.getUid();

        db.collection("bookings")
                .whereEqualTo("userId", userId)
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    bookingList.clear();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
<<<<<<< HEAD
                        Booking booking = doc.toObject(Booking.class);
                        bookingList.add(booking);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Lỗi tải lịch sử: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
=======
                        android.util.Log.d("BOOKING_DATA", "Document: " + doc.getData());
                        Booking booking = doc.toObject(Booking.class);
                        if (booking != null) {
                            bookingList.add(booking);
                        }
                        bookingList.add(booking);
                    }
                    bookingAdapter.notifyDataSetChanged();
                    android.util.Log.d("BOOKING_COUNT", "Tìm thấy " + bookingList.size() + " booking.");
                })
                .addOnFailureListener(e -> {
                    Log.e("FIRESTORE_ERROR", "Lỗi khi tải dữ liệu từ Firestore", e);
                    Toast.makeText(this, "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    }
}
