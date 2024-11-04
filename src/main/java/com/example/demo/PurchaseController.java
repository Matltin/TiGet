package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class PurchaseController extends Controller {

    @FXML
    private Label movieTitle;
    @FXML
    private Label userBalance;
    @FXML
    private Label ticketCount;
    @FXML
    private Label totalPrice;
    @FXML
    private Label theaterTitle;
    @FXML
    private Label theaterPhoneNumber;
    @FXML
    private Text theaterLocation;
    @FXML
    private ImageView moviePoster;

    @FXML
    private TableView<Screening> movieScreeningTable;
    @FXML
    private TableColumn<Screening, String> theaterTitleColumn;
    @FXML
    private TableColumn<Screening, Integer> screenNumberColumn;
    @FXML
    private TableColumn<Screening, Date> dateColumn;
    @FXML
    private TableColumn<Screening, Time> timeColumn;

    @FXML
    private AnchorPane seatArrangement;

    @FXML
    private RadioButton A1;
    @FXML
    private RadioButton A2;
    @FXML
    private RadioButton A3;
    @FXML
    private RadioButton A4;
    @FXML
    private RadioButton A5;
    @FXML
    private RadioButton A6;
    @FXML
    private RadioButton A7;
    @FXML
    private RadioButton A8;
    @FXML
    private RadioButton A9;
    @FXML
    private RadioButton A10;
    @FXML
    private RadioButton A11;
    @FXML
    private RadioButton A12;

    @FXML
    private RadioButton B1;
    @FXML
    private RadioButton B2;
    @FXML
    private RadioButton B3;
    @FXML
    private RadioButton B4;
    @FXML
    private RadioButton B5;
    @FXML
    private RadioButton B6;
    @FXML
    private RadioButton B7;
    @FXML
    private RadioButton B8;
    @FXML
    private RadioButton B9;
    @FXML
    private RadioButton B10;
    @FXML
    private RadioButton B11;
    @FXML
    private RadioButton B12;

    @FXML
    private RadioButton C1;
    @FXML
    private RadioButton C2;
    @FXML
    private RadioButton C3;
    @FXML
    private RadioButton C4;
    @FXML
    private RadioButton C5;
    @FXML
    private RadioButton C6;
    @FXML
    private RadioButton C7;
    @FXML
    private RadioButton C8;
    @FXML
    private RadioButton C9;
    @FXML
    private RadioButton C10;
    @FXML
    private RadioButton C11;
    @FXML
    private RadioButton C12;

    @FXML
    private RadioButton D1;
    @FXML
    private RadioButton D2;
    @FXML
    private RadioButton D3;
    @FXML
    private RadioButton D4;
    @FXML
    private RadioButton D5;
    @FXML
    private RadioButton D6;
    @FXML
    private RadioButton D7;
    @FXML
    private RadioButton D8;
    @FXML
    private RadioButton D9;
    @FXML
    private RadioButton D10;
    @FXML
    private RadioButton D11;
    @FXML
    private RadioButton D12;

    @FXML
    private RadioButton E1;
    @FXML
    private RadioButton E2;
    @FXML
    private RadioButton E3;
    @FXML
    private RadioButton E4;
    @FXML
    private RadioButton E5;
    @FXML
    private RadioButton E6;
    @FXML
    private RadioButton E7;
    @FXML
    private RadioButton E8;
    @FXML
    private RadioButton E9;
    @FXML
    private RadioButton E10;
    @FXML
    private RadioButton E11;
    @FXML
    private RadioButton E12;

    @FXML
    private RadioButton F1;
    @FXML
    private RadioButton F2;
    @FXML
    private RadioButton F3;
    @FXML
    private RadioButton F4;
    @FXML
    private RadioButton F5;
    @FXML
    private RadioButton F6;
    @FXML
    private RadioButton F7;
    @FXML
    private RadioButton F8;
    @FXML
    private RadioButton F9;
    @FXML
    private RadioButton F10;
    @FXML
    private RadioButton F11;
    @FXML
    private RadioButton F12;

    @FXML
    private RadioButton G1;
    @FXML
    private RadioButton G2;
    @FXML
    private RadioButton G3;
    @FXML
    private RadioButton G4;
    @FXML
    private RadioButton G5;
    @FXML
    private RadioButton G6;
    @FXML
    private RadioButton G7;
    @FXML
    private RadioButton G8;
    @FXML
    private RadioButton G9;
    @FXML
    private RadioButton G10;
    @FXML
    private RadioButton G11;
    @FXML
    private RadioButton G12;

    @FXML
    private Label label;

    private Movie movie;
    private Screening screening;
    private ObservableList<Screening> screeningsList;
    private String selectedSeats = "";
    private int tickets = 0;


    public void setMovie(Movie movie) {
        this.movie = movie;
        movieTitle.setText(movie.getTitle());
        moviePoster.setImage(new Image(movie.getImagePath()));
        populateMovieScreening();
    }
    public void setUserBalance(NormalUser normalUser) {
        userBalance.setText("$" + normalUser.getAccountBalance());
    }
    public void setListener() {
        addSelectionListener(A1, "A1");
        addSelectionListener(A2, "A2");
        addSelectionListener(A3, "A3");
        addSelectionListener(A4, "A4");
        addSelectionListener(A5, "A5");
        addSelectionListener(A6, "A6");
        addSelectionListener(A7, "A7");
        addSelectionListener(A8, "A8");
        addSelectionListener(A9, "A9");
        addSelectionListener(A10, "A10");
        addSelectionListener(A11, "A11");
        addSelectionListener(A12, "A12");

        addSelectionListener(B1, "B1");
        addSelectionListener(B2, "B2");
        addSelectionListener(B3, "B3");
        addSelectionListener(B4, "B4");
        addSelectionListener(B5, "B5");
        addSelectionListener(B6, "B6");
        addSelectionListener(B7, "B7");
        addSelectionListener(B8, "B8");
        addSelectionListener(B9, "B9");
        addSelectionListener(B10, "B10");
        addSelectionListener(B11, "B11");
        addSelectionListener(B12, "B12");

        addSelectionListener(C1, "C1");
        addSelectionListener(C2, "C2");
        addSelectionListener(C3, "C3");
        addSelectionListener(C4, "C4");
        addSelectionListener(C5, "C5");
        addSelectionListener(C6, "C6");
        addSelectionListener(C7, "C7");
        addSelectionListener(C8, "C8");
        addSelectionListener(C9, "C9");
        addSelectionListener(C10, "C10");
        addSelectionListener(C11, "C11");
        addSelectionListener(C12, "C12");

        addSelectionListener(D1, "D1");
        addSelectionListener(D2, "D2");
        addSelectionListener(D3, "D3");
        addSelectionListener(D4, "D4");
        addSelectionListener(D5, "D5");
        addSelectionListener(D6, "D6");
        addSelectionListener(D7, "D7");
        addSelectionListener(D8, "D8");
        addSelectionListener(D9, "D9");
        addSelectionListener(D10, "D10");
        addSelectionListener(D11, "D11");
        addSelectionListener(D12, "D12");

        addSelectionListener(E1, "E1");
        addSelectionListener(E2, "E2");
        addSelectionListener(E3, "E3");
        addSelectionListener(E4, "E4");
        addSelectionListener(E5, "E5");
        addSelectionListener(E6, "E6");
        addSelectionListener(E7, "E7");
        addSelectionListener(E8, "E8");
        addSelectionListener(E9, "E9");
        addSelectionListener(E10, "E10");
        addSelectionListener(E11, "E11");
        addSelectionListener(E12, "E12");

        addSelectionListener(F1, "F1");
        addSelectionListener(F2, "F2");
        addSelectionListener(F3, "F3");
        addSelectionListener(F4, "F4");
        addSelectionListener(F5, "F5");
        addSelectionListener(F6, "F6");
        addSelectionListener(F7, "F7");
        addSelectionListener(F8, "F8");
        addSelectionListener(F9, "F9");
        addSelectionListener(F10, "F10");
        addSelectionListener(F11, "F11");
        addSelectionListener(F12, "F12");

        addSelectionListener(G1, "G1");
        addSelectionListener(G2, "G2");
        addSelectionListener(G3, "G3");
        addSelectionListener(G4, "G4");
        addSelectionListener(G5, "G5");
        addSelectionListener(G6, "G6");
        addSelectionListener(G7, "G7");
        addSelectionListener(G8, "G8");
        addSelectionListener(G9, "G9");
        addSelectionListener(G10, "G10");
        addSelectionListener(G11, "G11");
        addSelectionListener(G12, "G12");
    }

    private void populateMovieScreening() {
        getTheaters();
        screeningsList = FXCollections.observableArrayList();
        try {
            while (getResultSet().next()) {
                findScreening(getResultSet().getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    private void findScreening(String theaterTitle) {
        ResultSet resultSet = getMovieScreening(theaterTitle, movie.getTitle());
        try {
            while (resultSet.next()) {
                screening = new Screening(theaterTitle, resultSet.getString(2),
                        resultSet.getInt(1), resultSet.getDate(3),
                        resultSet.getTime(4), resultSet.getString(5));
                screeningsList.add(screening);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        theaterTitleColumn.setCellValueFactory(new PropertyValueFactory<>("theaterTitle"));
        screenNumberColumn.setCellValueFactory(new PropertyValueFactory<>("screenNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        movieScreeningTable.setItems(screeningsList);

        movieScreeningTable.setRowFactory(tv -> {
            TableRow<Screening> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                screening = row.getItem();

                seatArrangement.setVisible(true);
                label.setVisible(true);
                getBookedSeats(screening.getBookedSeats());
                this.theaterTitle.setText(screening.getTheaterTitle());
                Theater theater = findTheater(screening.getTheaterTitle());
                theaterPhoneNumber.setText(theater.getPhoneNumber());
                theaterLocation.setText(theater.getLocation());
            });
            return row;
        });
    }
    public Theater findTheater(String title) {
        getTheaters();
        Theater theater = null;
        try {
            while (getResultSet().next()) {
                if(getResultSet().getString(2).equals(title)) {
                    theater = new Theater(getResultSet().getInt(1), getResultSet().getString(2),
                            getResultSet().getInt(3), getResultSet().getString(4),
                            getResultSet().getString(5));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return theater;
    }

    public void getBookedSeats(String bookedSeats) {
        String[] seats = bookedSeats.split(", ");
        for (String seat : seats) {
            visibility(seat);
        }
    }

    public void visibility(String radiobutton) {
        switch (radiobutton) {
            case "A1" -> A1.setDisable(true);
            case "A2" -> A2.setDisable(true);
            case "A3" -> A3.setDisable(true);
            case "A4" -> A4.setDisable(true);
            case "A5" -> A5.setDisable(true);
            case "A6" -> A6.setDisable(true);
            case "A7" -> A7.setDisable(true);
            case "A8" -> A8.setDisable(true);
            case "A9" -> A9.setDisable(true);
            case "A10" -> A10.setDisable(true);
            case "A11" -> A11.setDisable(true);
            case "A12" -> A12.setDisable(true);
        }

        switch (radiobutton) {
            case "B1" -> B1.setDisable(true);
            case "B2" -> B2.setDisable(true);
            case "B3" -> B3.setDisable(true);
            case "B4" -> B4.setDisable(true);
            case "B5" -> B5.setDisable(true);
            case "B6" -> B6.setDisable(true);
            case "B7" -> B7.setDisable(true);
            case "B8" -> B8.setDisable(true);
            case "B9" -> B9.setDisable(true);
            case "B10" -> B10.setDisable(true);
            case "B11" -> B11.setDisable(true);
            case "B12" -> B12.setDisable(true);
        }

        switch (radiobutton) {
            case "C1" -> C1.setDisable(true);
            case "C2" -> C2.setDisable(true);
            case "C3" -> C3.setDisable(true);
            case "C4" -> C4.setDisable(true);
            case "C5" -> C5.setDisable(true);
            case "C6" -> C6.setDisable(true);
            case "C7" -> C7.setDisable(true);
            case "C8" -> C8.setDisable(true);
            case "C9" -> C9.setDisable(true);
            case "C10" -> C10.setDisable(true);
            case "C11" -> C11.setDisable(true);
            case "C12" -> C12.setDisable(true);
        }

        switch (radiobutton) {
            case "D1" -> D1.setDisable(true);
            case "D2" -> D2.setDisable(true);
            case "D3" -> D3.setDisable(true);
            case "D4" -> D4.setDisable(true);
            case "D5" -> D5.setDisable(true);
            case "D6" -> D6.setDisable(true);
            case "D7" -> D7.setDisable(true);
            case "D8" -> D8.setDisable(true);
            case "D9" -> D9.setDisable(true);
            case "D10" -> D10.setDisable(true);
            case "D11" -> D11.setDisable(true);
            case "D12" -> D12.setDisable(true);
        }

        switch (radiobutton) {
            case "E1" -> E1.setDisable(true);
            case "E2" -> E2.setDisable(true);
            case "E3" -> E3.setDisable(true);
            case "E4" -> E4.setDisable(true);
            case "E5" -> E5.setDisable(true);
            case "E6" -> E6.setDisable(true);
            case "E7" -> E7.setDisable(true);
            case "E8" -> E8.setDisable(true);
            case "E9" -> E9.setDisable(true);
            case "E10" -> E10.setDisable(true);
            case "E11" -> E11.setDisable(true);
            case "E12" -> E12.setDisable(true);
        }

        switch (radiobutton) {
            case "F1" -> F1.setDisable(true);
            case "F2" -> F2.setDisable(true);
            case "F3" -> F3.setDisable(true);
            case "F4" -> F4.setDisable(true);
            case "F5" -> F5.setDisable(true);
            case "F6" -> F6.setDisable(true);
            case "F7" -> F7.setDisable(true);
            case "F8" -> F8.setDisable(true);
            case "F9" -> F9.setDisable(true);
            case "F10" -> F10.setDisable(true);
            case "F11" -> F11.setDisable(true);
            case "F12" -> F12.setDisable(true);
        }

        switch (radiobutton) {
            case "G1" -> G1.setDisable(true);
            case "G2" -> G2.setDisable(true);
            case "G3" -> G3.setDisable(true);
            case "G4" -> G4.setDisable(true);
            case "G5" -> G5.setDisable(true);
            case "G6" -> G6.setDisable(true);
            case "G7" -> G7.setDisable(true);
            case "G8" -> G8.setDisable(true);
            case "G9" -> G9.setDisable(true);
            case "G10" -> G10.setDisable(true);
            case "G11" -> G11.setDisable(true);
            case "G12" -> G12.setDisable(true);
        }
    }

    public void pay(ActionEvent event) throws IOException {
        double price = tickets * movie.getTicketPrice();
        if (user.getAccountBalance() < price) {
            showAlert(Alert.AlertType.ERROR, "Error", "Insufficient funds.");
        }
        else {
            Bookings newBooking = new Bookings(movie.getTitle(), screening.getTheaterTitle(),
                    screening.getScreenNumber(), screening.getTime(),
                    screening.getDate(), selectedSeats, price, "active");
            addBookingToDB(user.getUserName(), newBooking);
            screening.setBookedSeats(screening.getBookedSeats() + selectedSeats);
            setScreeningSeats(screening);

            setAccountBalance(user.getAccountBalance() - price, user.getId());

            showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Booking successful.");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ticket.fxml"));
            root = loader.load();

            TicketController ticketController = loader.getController();
            ticketController.setTicket(newBooking);
            loadPage(root, event, "Ticket");
        }
    }

    private void addSelectionListener(RadioButton radioButton, String seatId) {
        radioButton.setOnAction(event -> {
            if (radioButton.isSelected()) {
                selectedSeats += seatId + ", ";
                tickets++;
                ticketCount.setText(tickets + " Ticket(s)");
                totalPrice.setText("$" + tickets * movie.getTicketPrice());
            } else {
                selectedSeats = selectedSeats.replace(seatId + ", ", "");
                tickets--;
                ticketCount.setText(tickets + " Ticket(s)");
                totalPrice.setText("$" + tickets * movie.getTicketPrice());
            }
        });
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();

        loadPage(root, event, "TiGet");
    }
}
