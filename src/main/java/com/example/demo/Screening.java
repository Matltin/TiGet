package com.example.demo;

import java.sql.Date;
import java.sql.Time;

public class Screening {
    private String theaterTitle;
    private String movieTitle;
    private int screenNumber;
    private Date date;
    private Time time;
    private String bookedSeats;

    public Screening(String theaterTitle, String movieTitle, int screenNumber, Date date, Time time, String bookedSeats) {
        this.theaterTitle = theaterTitle;
        this.movieTitle = movieTitle;
        this.screenNumber = screenNumber;
        this.date = date;
        this.time =time;
        this.bookedSeats = bookedSeats;
    }

    public String getTheaterTitle() {
        return theaterTitle;
    }

    public void setTheaterTitle(String theaterTitle) {
        this.theaterTitle = theaterTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(String bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
