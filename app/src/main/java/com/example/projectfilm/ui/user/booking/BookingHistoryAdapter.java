package com.example.projectfilm.ui.user.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfilm.R;

import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {
    private Context context;
    private List<Booking> bookingList;

    public BookingHistoryAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_booking_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);

        holder.textCinema.setText("Rạp: " + booking.getCinema());
        holder.textTime.setText("Giờ chiếu: " + booking.getTime());
        holder.textSeats.setText("Ghế: " + booking.getSeats());
        holder.textPrice.setText("Tổng tiền: " + booking.getPrice());
        holder.textName.setText("Họ tên: " + booking.getName());
        holder.textEmail.setText("Email: " + booking.getEmail());
        holder.textPaymentMethod.setText("Thanh toán: " + booking.getPaymentMethod());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCinema, textTime, textSeats, textPrice, textName, textEmail, textPaymentMethod;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCinema = itemView.findViewById(R.id.textCinema);
            textTime = itemView.findViewById(R.id.textTime);
            textSeats = itemView.findViewById(R.id.textSeats);
            textPrice = itemView.findViewById(R.id.textPrice);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            textPaymentMethod = itemView.findViewById(R.id.textPaymentMethod);
        }
    }
}
