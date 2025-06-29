package com.example.projectfilm.data.model;

<<<<<<< HEAD
import java.util.List;

public class Movie {
=======
import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    private String movieId;
    private String title;
    private String description;
    private String genre;
    private String posterUrl;
<<<<<<< HEAD
=======
    private String prices;
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    private String status;
    private String actors;
    private String producer;
    private String year;
    private String country;
<<<<<<< HEAD
=======
    private String director; // ✅ Thêm trường này
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8

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
<<<<<<< HEAD
=======

    public String getPrices() { return prices; }
    public void setPrices(String prices) { this.prices = prices; }

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
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

<<<<<<< HEAD
=======
    public String getDirector() { return director; } // ✅ Thêm getter
    public void setDirector(String director) { this.director = director; } // ✅ Thêm setter

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    public List<Showtime> getShowtimes() { return showtimes; }
    public void setShowtimes(List<Showtime> showtimes) { this.showtimes = showtimes; }
}
