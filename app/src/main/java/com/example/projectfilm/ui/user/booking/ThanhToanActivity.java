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
        String cinema = intent.getStringExtra("cinema");
        String time = intent.getStringExtra("time");
        String seats = intent.getStringExtra("seats");
        String price = intent.getStringExtra("price");
        // Hiển thị dữ liệu
        textCinema.setText("Rạp: " + cinema);
        textTime.setText("Giờ chiếu: " + time);
        textSeats.setText("Ghế: " + seats);
        textPrice.setText("Tổng tiền: " + price);


        if (currentUser != null) {
            String uid = currentUser.getUid();
            firestore.collection("users").document(uid)
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot.exists()) {
                            String name = snapshot.getString("name");
                            String email = snapshot.getString("email");
                            editName.setText(name != null ? name : "");
                            editEmail.setText(email != null ? email : "");

                            editName.setEnabled(false);  // Khóa chỉnh sửa
                            editEmail.setEnabled(false);
                        }
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Lỗi tải thông tin người dùng", Toast.LENGTH_SHORT).show()
                    );

        } else {
            Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        // Sự kiện xác nhận
        btnConfirmPayment.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            int checkedId = radioGroup.getCheckedRadioButtonId();

            if (name.isEmpty() || email.isEmpty() || checkedId == -1) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            String paymentMethod = (checkedId == R.id.radioMomo) ? "Ví MoMo" : "Ngân hàng";
            Intent confirmIntent = new Intent(ThanhToanActivity.this, ThanhToanThanhCongActivity.class);
            confirmIntent.putExtra("cinema", cinema);
            confirmIntent.putExtra("time", time);
            confirmIntent.putExtra("seats", seats);
            confirmIntent.putExtra("price", price);
            confirmIntent.putExtra("name", name);
            confirmIntent.putExtra("email", email);
            confirmIntent.putExtra("paymentMethod", paymentMethod);
            startActivity(confirmIntent);
        });
    }
}
