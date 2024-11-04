package com.example.demo;

import java.sql.Date;
import java.sql.Time;

public class Bookings {

    private String movieTitle;
    private String theaterTitle;
    private int screenNumber;
    private Time screenTime;
    private Date screenDate;
    private String bookedSeats;
    private double totalPrice;
    private String ticketStatus;

    public Bookings(String movieTitle, String theaterTitle, int screenNumber, Time screenTime, Date screenDate, String bookedSeats, double totalPrice, String ticketStatus) {
        this.movieTitle = movieTitle;
        this.theaterTitle = theaterTitle;
        this.screenNumber = screenNumber;
        this.screenTime = screenTime;
        this.screenDate = screenDate;
        this.bookedSeats = bookedSeats;
        this.totalPrice = totalPrice;
        this.ticketStatus = ticketStatus;
        check(ticketStatus);
    }

    public void check(String ticketStatus) {
        long millis = System.currentTimeMillis();
        long diff = screenDate.getTime() - millis;
        if("refunded".equals(ticketStatus)) {
            this.ticketStatus = ticketStatus;
        } else if(diff < 0) {
            this.ticketStatus = "expired";
        } else {
            this.ticketStatus = ticketStatus;
        }
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getTheaterTitle() {
        return theaterTitle;
    }

    public void setTheaterTitle(String theaterTitle) {
        this.theaterTitle = theaterTitle;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }

    public Time getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(Time screenTime) {
        this.screenTime = screenTime;
    }

    public Date getScreenDate() {
        return screenDate;
    }

    public void setScreenDate(Date screenDate) {
        this.screenDate = screenDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(String bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
