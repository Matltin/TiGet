package com.example.demo;

import java.sql.Date;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private String director;
    private Date releaseDate;
    private String cast;
    private String description;
    private double ticketPrice;
    private String imagePath;

    public Movie(int id, String title, String genre, String director, Date releaseDate, String cast, String description, double ticketPrice, String imagePath) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.releaseDate = releaseDate;
        this.cast = cast;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
