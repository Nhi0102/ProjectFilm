package com.example.projectfilm.data.model;

import java.util.List;

public class Movie {
    private String movieId;
    private String title;
    private String description;
    private String genre;
    private String posterUrl;
    private String status;
    private String actors;
    private String producer;
    private String year;
    private String country;

    private List<Showtime> showtimes; // Nếu có sử dụng showtimes

    public Movie() {
        // Constructor rỗng bắt buộc cho Firebase
    }

    // Getters & Setters
    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getActors() { return actors; }
    public void setActors(String actors) { this.actors = actors; }

    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public List<Showtime> getShowtimes() { return showtimes; }
    public void setShowtimes(List<Showtime> showtimes) { this.showtimes = showtimes; }
}
