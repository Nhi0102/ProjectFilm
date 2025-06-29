package com.example.projectfilm.ui.user.booking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
<<<<<<< HEAD
import com.example.projectfilm.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
=======
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfilm.R;
import com.example.projectfilm.ui.user.booking.Booking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
<<<<<<< HEAD
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
=======
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.tvCinema.setText("Rạp: " + booking.getCinema());
        holder.tvSeats.setText("Ghế: " + booking.getSeats());
        holder.tvPrice.setText("Giá: " + booking.getPrice() + "đ");
        holder.tvTime.setText("Giờ: " + booking.getTime());
        holder.tvPayment.setText("Thanh toán: " + booking.getPaymentMethod());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String date = sdf.format(new Date(booking.getTimestamp()));
        holder.tvDate.setText("Ngày: " + date);
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

<<<<<<< HEAD
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

=======
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCinema, tvSeats, tvPrice, tvTime, tvPayment, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCinema = itemView.findViewById(R.id.tvCinema);
            tvSeats = itemView.findViewById(R.id.tvSeats);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPayment = itemView.findViewById(R.id.tvPaymentMethod);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
