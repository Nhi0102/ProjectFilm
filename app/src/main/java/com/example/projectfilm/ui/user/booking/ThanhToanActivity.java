package com.example.projectfilm.ui.user.booking;

import com.example.projectfilm.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
=======
import com.example.projectfilm.ui.user.profile.ProfileActivity;
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ThanhToanActivity extends AppCompatActivity {

    private TextView textCinema, textTime, textSeats, textPrice;
    private EditText editName, editEmail;
    private RadioGroup radioGroup;
    private RadioButton radioMomo, radioBank;
    private Button btnConfirmPayment;

    private FirebaseUser currentUser;
    private FirebaseFirestore firestore;

<<<<<<< HEAD
=======
    private String cinema, time, seats, price;

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        // Ánh xạ view
        textCinema = findViewById(R.id.textCinema);
        textTime = findViewById(R.id.textTime);
        textSeats = findViewById(R.id.textSeats);
        textPrice = findViewById(R.id.textPrice);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        radioGroup = findViewById(R.id.radioGroup);
        radioMomo = findViewById(R.id.radioMomo);
        radioBank = findViewById(R.id.radioBank);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);

        // Firebase
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        // Nhận dữ liệu từ SeatListActivity
        Intent intent = getIntent();
<<<<<<< HEAD
        String cinema = intent.getStringExtra("cinema");
        String time = intent.getStringExtra("time");
        String seats = intent.getStringExtra("seats");
        String price = intent.getStringExtra("price");
=======
        cinema = intent.getStringExtra("cinema");
        time = intent.getStringExtra("time");
        seats = intent.getStringExtra("seats");
        price = intent.getStringExtra("price");
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8

        // Hiển thị dữ liệu
        textCinema.setText("Rạp: " + cinema);
        textTime.setText("Giờ chiếu: " + time);
        textSeats.setText("Ghế: " + seats);
        textPrice.setText("Tổng tiền: " + price);

<<<<<<< HEAD
        // Lấy tên & email từ Firestore
=======
        // Lấy tên, email, address từ Firestore
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
        if (currentUser != null) {
            String uid = currentUser.getUid();
            firestore.collection("users").document(uid)
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot.exists()) {
                            String name = snapshot.getString("name");
                            String email = snapshot.getString("email");
<<<<<<< HEAD

                            editName.setText(name != null ? name : "");
                            editEmail.setText(email != null ? email : "");

                            editName.setEnabled(false);  // Khóa chỉnh sửa
                            editEmail.setEnabled(false);
                        }
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Lỗi tải thông tin người dùng", Toast.LENGTH_SHORT).show()
                    );
=======
                            String address = snapshot.getString("address");

                            // Kiểm tra nếu thiếu thông tin
                            if (name == null || name.isEmpty() ||
                                    email == null || email.isEmpty() ||
                                    address == null || address.isEmpty()) {
                                Toast.makeText(this, "Vui lòng hoàn thiện thông tin cá nhân trước khi thanh toán", Toast.LENGTH_LONG).show();
                                Intent profileIntent = new Intent(this, ProfileActivity.class);
                                profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(profileIntent);
                                finish();
                            } else {
                                // Đổ dữ liệu lên UI và khóa chỉnh sửa
                                editName.setText(name);
                                editEmail.setText(email);
                                editName.setEnabled(false);
                                editEmail.setEnabled(false);
                            }
                        } else {
                            Toast.makeText(this, "Không tìm thấy thông tin người dùng, vui lòng cập nhật hồ sơ.", Toast.LENGTH_LONG).show();
                            Intent profileIntent = new Intent(this, ProfileActivity.class);
                            profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(profileIntent);
                            finish();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Lỗi tải thông tin: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    });
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
        } else {
            Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

<<<<<<< HEAD
        // Sự kiện xác nhận
        btnConfirmPayment.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            int checkedId = radioGroup.getCheckedRadioButtonId();

            if (name.isEmpty() || email.isEmpty() || checkedId == -1) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
=======
        // Xác nhận thanh toán
        btnConfirmPayment.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            if (checkedId == -1) {
                Toast.makeText(this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
                return;
            }

            String paymentMethod = (checkedId == R.id.radioMomo) ? "Ví MoMo" : "Ngân hàng";

<<<<<<< HEAD
            // Chuyển sang màn hình ThanhToanThanhCongActivity
=======
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
            Intent confirmIntent = new Intent(ThanhToanActivity.this, ThanhToanThanhCongActivity.class);
            confirmIntent.putExtra("cinema", cinema);
            confirmIntent.putExtra("time", time);
            confirmIntent.putExtra("seats", seats);
            confirmIntent.putExtra("price", price);
<<<<<<< HEAD
            confirmIntent.putExtra("name", name);
            confirmIntent.putExtra("email", email);
=======
            confirmIntent.putExtra("name", editName.getText().toString().trim());
            confirmIntent.putExtra("email", editEmail.getText().toString().trim());
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
            confirmIntent.putExtra("paymentMethod", paymentMethod);
            startActivity(confirmIntent);
        });
    }
}
