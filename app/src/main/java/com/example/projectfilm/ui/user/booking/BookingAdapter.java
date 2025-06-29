package com.example.projectfilm.ui.user.booking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.projectfilm.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.textCinema.setText("Rạp: " + booking.getCinema());
        holder.textTime.setText("Giờ chiếu: " + booking.getTime());
        holder.textSeats.setText("Ghế: " + booking.getSeats());
        holder.textPrice.setText("Tổng tiền: " + booking.getPrice() + " VND");
        holder.textName.setText("Tên: " + booking.getName());
        holder.textEmail.setText("Email: " + booking.getEmail());
        holder.textPayment.setText("Thanh toán: " + booking.getPaymentMethod());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView textCinema, textTime, textSeats, textPrice, textName, textEmail, textPayment;

        BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            textCinema = itemView.findViewById(R.id.textCinema);
            textTime = itemView.findViewById(R.id.textTime);
            textSeats = itemView.findViewById(R.id.textSeats);
            textPrice = itemView.findViewById(R.id.textPrice);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            textPayment = itemView.findViewById(R.id.textPaymentMethod);
        }
    }
}

