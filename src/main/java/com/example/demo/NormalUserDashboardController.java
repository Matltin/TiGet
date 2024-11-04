package com.example.demo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class NormalUserDashboardController extends Controller implements Initializable{

    @FXML
    private Label username;
    @FXML
    private Label balance;

    @FXML
    private AnchorPane bookingsPage;
    @FXML
    private AnchorPane editInfoPage;
    @FXML
    private AnchorPane addFundsPage;

    @FXML
    private TableView<Bookings> bookingList;
    @FXML
    private TableColumn<Bookings, String> movieTitle;
    @FXML
    private TableColumn<Bookings, String> theaterTitle;
    @FXML
    private TableColumn<Bookings, Integer> screenNumber;
    @FXML
    private TableColumn<Bookings, Time> screenTime;
    @FXML
    private TableColumn<Bookings, Date> screenDate;
    @FXML
    private TableColumn<Bookings, String> bookedSeats;
    @FXML
    private TableColumn<Bookings, Double> totalPrice;
    @FXML
    private TableColumn<Bookings, String> ticketStatus;

    @FXML
    private AnchorPane buttons;

    @FXML
    private TextField emailField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;

    @FXML
    private TextField fundAmount;

    ActionEvent event;

    Bookings booking;

    Movie movie;

    public void setEvent(ActionEvent e) {
        event = e;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(user.getUserName());
        balance.setText("$" + user.getAccountBalance());
        populateBookingList();
    }

    private void populateBookingList() {
        getBookings();
        ObservableList<Bookings> observableList = FXCollections.observableArrayList();
        try {
            while (getResultSet().next()) {
                if (getResultSet().getString(1).equals(username.getText())) {
                    observableList.add(new Bookings(getResultSet().getString(2),
                            getResultSet().getString(3), getResultSet().getInt(4),
                            getResultSet().getTime(5), getResultSet().getDate(6),
                            getResultSet().getString(7), getResultSet().getDouble(8),
                            getResultSet().getString(9)));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        theaterTitle.setCellValueFactory(new PropertyValueFactory<>("theaterTitle"));
        screenNumber.setCellValueFactory(new PropertyValueFactory<>("screenNumber"));
        screenTime.setCellValueFactory(new PropertyValueFactory<>("screenTime"));
        screenDate.setCellValueFactory(new PropertyValueFactory<>("screenDate"));
        bookedSeats.setCellValueFactory(new PropertyValueFactory<>("bookedSeats"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        ticketStatus.setCellValueFactory(new PropertyValueFactory<>("ticketStatus"));

        bookingList.setItems(observableList);

        bookingList.setRowFactory(tv -> {
            TableRow<Bookings> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                buttons.setVisible(true);
                booking = row.getItem();
            });
            return row;
        });
    }

    public void showBookings() {
        editInfoPage.setVisible(false);
        addFundsPage.setVisible(false);
        bookingsPage.setVisible(true);
    }
    public void editInfo() {
        emailField.setText(user.getEmail());
        nameField.setText(user.getUserName());
        passField.setText(user.getPassword());

        bookingsPage.setVisible(false);
        addFundsPage.setVisible(false);
        editInfoPage.setVisible(true);
    }
    public void addFunds() {
        editInfoPage.setVisible(false);
        bookingsPage.setVisible(false);
        addFundsPage.setVisible(true);
    }
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        loadPage(root, event, "TiGet");
    }

    public void showTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ticket.fxml"));
        root = loader.load();

        TicketController ticketController = loader.getController();
        ticketController.setTicket(booking);
        loadPage(root, event, "TiGet");
    }

    public void showMoviePage(ActionEvent event) throws IOException {
        findMovie();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-page.fxml"));
        root = loader.load();

        PageController pageController = loader.getController();
        pageController.setMovie(movie);
        loadPage(root, event, "TiGet");
    }

    public void cancelTicket() {
        cancelTicket(user, booking);
        populateBookingList();
    }

    public void updateInfo() {
        String newEmail = emailField.getText();
        String newUsername = nameField.getText();
        String newPassword = passField.getText();

        NormalUser newUser = new NormalUser(newUsername, newPassword, newEmail, user.getId(), user.getAccountBalance());
        updateUserProfile(newUser);

        username.setText(nameField.getText());
        balance.setText("$" + user.getAccountBalance());

        user = newUser;
        showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Your information has been successfully updated");
    }

    public void increaseBalance() {
        try {
            double fund = Double.parseDouble(fundAmount.getText());
            fund += user.getAccountBalance();
            setAccountBalance(fund, user.getId());
            balance.setText("$" + fund);
            showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Funds have been successfully added to your balance.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "invalid input");
        }
    }

    private void findMovie() {
        getMovies();
        try {
            while (getResultSet().next()) {
                if (getResultSet().getString(2).equals(booking.getMovieTitle())) {
                    movie = new Movie(getResultSet().getInt(1), getResultSet().getString(2), getResultSet().getString(3),
                            getResultSet().getString(4), getResultSet().getDate(5),
                            getResultSet().getString(6), getResultSet().getString(7),
                            getResultSet().getDouble(8), getResultSet().getString(9));
                }
            }
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();

        buttons.setVisible(false);
        loadPage(root, event, "TiGet");
    }
}
