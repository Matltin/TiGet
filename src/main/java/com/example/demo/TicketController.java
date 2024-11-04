package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class TicketController extends Controller{
    @FXML
    private Label theaterTitle;
    @FXML
    private Label screenNumber;
    @FXML
    private Label movieTitle;
    @FXML
    private Label screenDate;
    @FXML
    private Label screenTime;
    @FXML
    private Label bookedSeats;

    Bookings booking;

    public void setTicket(Bookings booking){
        this.booking = booking;
        theaterTitle.setText(booking.getTheaterTitle());
        screenNumber.setText(String.valueOf(booking.getScreenNumber()));
        movieTitle.setText(booking.getMovieTitle());
        screenDate.setText(String.valueOf(booking.getScreenDate()));
        screenTime.setText(String.valueOf(booking.getScreenTime()));
        bookedSeats.setText(booking.getBookedSeats());
    }

    public void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();

        loadPage(root, event, "TiGet");
    }
}
