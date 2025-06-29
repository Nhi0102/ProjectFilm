package com.example.projectfilm.ui.user.booking;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.projectfilm.R;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SeatListActivity extends AppCompatActivity {

    private GridLayout gridSeats;
    private TextView textSelectedSeats, textTotalPrice;
    private Button btnConfirm;

    private List<String> selectedSeats = new ArrayList<>();
    private static final int SEAT_PRICE = 100000;

    private String cinemaName = "";
    private String timeSlot = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_list);

        gridSeats = findViewById(R.id.gridSeats);
        textSelectedSeats = findViewById(R.id.textSelectedSeats);
        textTotalPrice = findViewById(R.id.textTotalPrice);
        btnConfirm = findViewById(R.id.btnConfirm);

        // Nhận dữ liệu từ TicketBooking
        Intent intent = getIntent();
        cinemaName = intent.getStringExtra("cinema");
        timeSlot = intent.getStringExtra("time");

        // Tạo danh sách ghế: A1–A4, B1–B4, ..., E1–E4
        generateSeatButtons();

        btnConfirm.setOnClickListener(v -> {
            if (selectedSeats.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 ghế", Toast.LENGTH_SHORT).show();
                return;
            }

            // Chuyển sang ThanhToanActivity
            Intent payIntent = new Intent(SeatListActivity.this, ThanhToanActivity.class);
            payIntent.putExtra("cinema", cinemaName);
            payIntent.putExtra("time", timeSlot);
            payIntent.putExtra("seats", TextUtils.join(", ", selectedSeats));
            payIntent.putExtra("price", String.valueOf(selectedSeats.size() * SEAT_PRICE));
            startActivity(payIntent);
        });
    }

    private void generateSeatButtons() {
        String[] rows = {"A", "B", "C", "D", "E"};
        int cols = 4;

        gridSeats.setColumnCount(cols); // cấu hình số cột của GridLayout

        for (String row : rows) {
            for (int col = 1; col <= cols; col++) {
                String seatId = row + col;
                Button seatButton = new Button(this);
                seatButton.setText(seatId);
                seatButton.setBackgroundResource(R.drawable.bg_seat_available);
                seatButton.setPadding(8, 8, 8, 8);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(12, 12, 12, 12);
                seatButton.setLayoutParams(params);

                seatButton.setOnClickListener(v -> {
                    if (selectedSeats.contains(seatId)) {
                        selectedSeats.remove(seatId);
                        seatButton.setBackgroundResource(R.drawable.bg_seat_available);
                    } else {
                        selectedSeats.add(seatId);
                        seatButton.setBackgroundResource(R.drawable.bg_seat_selected);
                    }
                    updateSeatInfo();
                });

                gridSeats.addView(seatButton);
            }
        }
    }

    private void updateSeatInfo() {
        textSelectedSeats.setText(selectedSeats.size() + " Ghế: " + TextUtils.join(", ", selectedSeats));
        int total = selectedSeats.size() * SEAT_PRICE;
        textTotalPrice.setText(" VND"+total);
    }
}
