package com.example.projectfilm.ui.user.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfilm.MainActivity;
import com.example.projectfilm.R;
import com.example.projectfilm.ui.auth.LoginActivity;
import com.example.projectfilm.ui.user.booking.BookingHistoryActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

public class ProfileActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etAddress;
    private Button btnOrderHistory, btnHotline, btnInviteFriends, btnLogout;
    private ProgressBar progressBar;

    private FirebaseUser currentUser;
    private FirebaseFirestore firestore;
    private DocumentReference userDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Ánh xạ view
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        btnOrderHistory = findViewById(R.id.btnOrderHistory);
        btnHotline = findViewById(R.id.btnHotline);
        btnInviteFriends = findViewById(R.id.btnInviteFriends);
        Button btnSave = findViewById(R.id.btnSave);
        btnLogout = findViewById(R.id.btnLogout);
        progressBar = findViewById(R.id.profileLoading);

        // Disable chỉnh sửa trong khi đang tải
        setEditingEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        // Firebase
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        if (currentUser != null) {
            String uid = currentUser.getUid();
            userDoc = firestore.collection("users").document(uid);

            // Lấy dữ liệu từ Firestore
            userDoc.get().addOnSuccessListener(snapshot -> {
                progressBar.setVisibility(View.GONE);
                if (snapshot.exists()) {
                    String name = snapshot.getString("name");
                    String email = snapshot.getString("email");
                    String address = snapshot.getString("address");

                    etFullName.setText(name != null ? name : "");
                    etEmail.setText(email != null ? email : "");
                    etAddress.setText(address != null ? address : "");
                } else {
                    Toast.makeText(this, "Không tìm thấy dữ liệu người dùng", Toast.LENGTH_SHORT).show();
                }
                setEditingEnabled(true);
            }).addOnFailureListener(e -> {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Người dùng chưa đăng nhập", Toast.LENGTH_SHORT).show();
        }

        // Các sự kiện nút
        btnOrderHistory.setOnClickListener(v ->
                startActivity(new Intent(ProfileActivity.this, BookingHistoryActivity.class)));

        btnHotline.setOnClickListener(v ->
                Toast.makeText(this, "Hotline: 19001900", Toast.LENGTH_SHORT).show());

        btnInviteFriends.setOnClickListener(v ->
                Toast.makeText(this, "Tính năng đang phát triển", Toast.LENGTH_SHORT).show());

        btnSave.setOnClickListener(v -> saveUserData());

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void saveUserData() {
        if (currentUser != null && userDoc != null) {
            userDoc.update(
                    "name", etFullName.getText().toString().trim(),
                    "email", etEmail.getText().toString().trim(),
                    "address", etAddress.getText().toString().trim()
            ).addOnSuccessListener(unused ->
                    Toast.makeText(this, "Đã lưu thành công", Toast.LENGTH_SHORT).show()
            ).addOnFailureListener(e ->
                    Toast.makeText(this, "Lỗi lưu: " + e.getMessage(), Toast.LENGTH_SHORT).show()
            );
        }
    }

    private void setEditingEnabled(boolean enabled) {
        etFullName.setEnabled(enabled);
        etEmail.setEnabled(enabled);
        etAddress.setEnabled(enabled);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveUserData(); // Tự lưu khi rời màn hình
    }
}
