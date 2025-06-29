package com.example.projectfilm.ui.user.booking;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfilm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;
import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

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
        loadBookingHistory();
    }
    private void loadBookingHistory() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return;

        FirebaseFirestore.getInstance()
                .collection("bookings")
                .whereEqualTo("userId", user.getUid()) // 🔍 Chỉ lấy vé của người dùng này
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    bookingList.clear();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Booking booking = doc.toObject(Booking.class);
                        bookingList.add(booking);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Lỗi tải lịch sử: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}
