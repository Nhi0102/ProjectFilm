package com.example.projectfilm.ui.user.booking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfilm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ThanhToanThanhCongActivity extends AppCompatActivity {

    TextView textCinema, textTime, textSeats, textPrice, textName, textEmail, textPaymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_thanh_cong);

        // Ánh xạ View
        textCinema = findViewById(R.id.textCinema);
        textTime = findViewById(R.id.textTime);
        textSeats = findViewById(R.id.textSeats);
        textPrice = findViewById(R.id.textPrice);
        textName = findViewById(R.id.textName);
        textEmail = findViewById(R.id.textEmail);
        textPaymentMethod = findViewById(R.id.textPaymentMethod);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String cinema = intent.getStringExtra("cinema");
        String time = intent.getStringExtra("time");
        String seats = intent.getStringExtra("seats");
        String price = intent.getStringExtra("price");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String paymentMethod = intent.getStringExtra("paymentMethod");

        // Hiển thị dữ liệu lên màn hình
        textCinema.setText("Rạp: " + cinema);
        textTime.setText("Giờ chiếu: " + time);
        textSeats.setText("Ghế: " + seats);
        textPrice.setText("Tổng tiền: " + price + " VND");
        textName.setText("Tên: " + name);
        textEmail.setText("Email: " + email);
        textPaymentMethod.setText("Thanh toán bằng: " + paymentMethod);

        // Tạo bản ghi đặt vé
        Map<String, Object> booking = new HashMap<>();
        booking.put("cinema", cinema);
        booking.put("time", time);
        booking.put("seats", seats);
        booking.put("price", price);
        booking.put("name", name);
        booking.put("email", email);
        booking.put("paymentMethod", paymentMethod);
        booking.put("timestamp", System.currentTimeMillis());

        // Lấy thông tin người dùng đăng nhập
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            booking.put("userId", user.getUid());  // 💡 Rất quan trọng để hiển thị theo người dùng
        } else {
            Toast.makeText(this, "Không tìm thấy người dùng đang đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu vào Firestore
        FirebaseFirestore.getInstance()
                .collection("bookings")
                .add(booking)
                .addOnSuccessListener(docRef ->
                        Toast.makeText(this, "Đã lưu vé thành công", Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Lỗi lưu vé: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("ThanhToan", "Lỗi lưu booking:", e); // Ghi log lỗi
                });
    }
}
