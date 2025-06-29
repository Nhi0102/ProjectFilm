package com.example.projectfilm.ui.user.booking;

public class Booking {
    private String cinema;
    private String time;
    private String seats;
    private String price;
    private String name;
    private String email;
    private String paymentMethod;
    private String userId;

    public Booking() {} // Required for Firestore

    // Getters & Setters
    public String getCinema() { return cinema; }
    public void setCinema(String cinema) { this.cinema = cinema; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getSeats() { return seats; }
    public void setSeats(String seats) { this.seats = seats; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
