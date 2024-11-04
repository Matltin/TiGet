package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {
    private String url = "jdbc:mysql://127.0.0.1:3306/myDB";
    private ResultSet resultSet;

    Stage stage;
    Parent root;
    Scene scene;

    protected static NormalUser user;
    protected static Admin admin;

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void getUsers() {
        loadTable("users");
    }

    public void getAdmins() {
        loadTable("admins");
    }

    public void getMovies() {
        loadTable("movies");
    }

    public void getTheaters() {loadTable("theaters");}

    public void getBookings() {
        loadTable("bookings");
    }

    public void loadPage(Parent root, ActionEvent event, String title) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle(title);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    public void addUserToDB(NormalUser normalUser) {
        int id = normalUser.getId();
        String username = normalUser.getUserName();
        String email = normalUser.getEmail();
        String password = normalUser.getPassword();
        double balance = normalUser.getAccountBalance();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "INSERT INTO users VALUES(?, ?, ?, ?, ?);";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setDouble(5, balance);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMovieToDB(Movie newMovie) {
        int id = newMovie.getId();
        String title = newMovie.getTitle();
        String genre = newMovie.getGenre();
        String director = newMovie.getDirector();
        Date releaseDate = newMovie.getReleaseDate();
        String cast = newMovie.getCast();
        String description = newMovie.getDescription();
        double ticketPrice = newMovie.getTicketPrice();
        String imagePath = newMovie.getImagePath();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "INSERT INTO movies VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, director);
            preparedStatement.setDate(5, releaseDate);
            preparedStatement.setString(6, cast);
            preparedStatement.setString(7, description);
            preparedStatement.setDouble(8, ticketPrice);
            preparedStatement.setString(9, imagePath);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void editMovieDB(Movie newMovie) {
        int id = newMovie.getId();
        String title = newMovie.getTitle();
        String genre = newMovie.getGenre();
        String director = newMovie.getDirector();
        Date releaseDate = newMovie.getReleaseDate();
        String cast = newMovie.getCast();
        String description = newMovie.getDescription();
        double ticketPrice = newMovie.getTicketPrice();
        String imagePath = newMovie.getImagePath();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "UPDATE movies SET title = ?, genre = ?, " +
                    "director = ?, release_date = ?, cast = ?, " +
                    "movie_description = ?, ticket_price = ?, image_path = ? " +
                    "WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, genre);
            preparedStatement.setString(3, director);
            preparedStatement.setDate(4, releaseDate);
            preparedStatement.setString(5, cast);
            preparedStatement.setString(6, description);
            preparedStatement.setDouble(7, ticketPrice);
            preparedStatement.setString(8, imagePath);
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeMovieFromDB(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "DELETE FROM movies WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTheaterToDB(Theater theater) {
        int id = theater.getId();
        String title = theater.getTitle();
        int screenCount = theater.getScreenCount();
        String phoneNumber = theater.getPhoneNumber();
        String location = theater.getLocation();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "INSERT INTO theaters VALUES(?, ?, ?, ?, ?);";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, screenCount);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, location);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

        createScreenings(title);
    }
    public void removeTheaterFromDB(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "DELETE FROM theaters WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createScreenings(String title) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "CREATE TABLE ? (screen_number INT, displayed_movie VARCHAR(50), date DATE, time TIME, booked_seats VARCHAR(5000));";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, title.toLowerCase());
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setAccountBalance(double balance, int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "UPDATE users SET balance = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void cancelTicket(NormalUser normalUser, Bookings ticket) {
        String username = normalUser.getUserName();
        String movieTitle = ticket.getMovieTitle();
        String bookedSeats = ticket.getBookedSeats();
        double refund = refundMoney(ticket.getTotalPrice(), ticket.getScreenDate());
        if(refund == 0) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "UPDATE bookings SET ticket_status = ? WHERE username = ? AND movie_title = ? AND booked_seats = ?";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "refunded");
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, movieTitle);
            preparedStatement.setString(4, bookedSeats);
            refund += normalUser.getAccountBalance();
            setAccountBalance(refund, normalUser.getId());
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private double refundMoney(double ticketPrice, Date ticketDate) {
        long nowMillis = System.currentTimeMillis();
        long diff = ticketDate.getTime() - nowMillis;
        long oneHour = 1000 * 60 * 60;
        if(diff / (24 * oneHour) > 1) {
            showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Your ticket has been successfully canceled. Funds will be transferred to you balance shortly.");

            return ticketPrice;
        } else if (diff / (4 * oneHour) > 1) {
            showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Your ticket has been successfully canceled. Funds will be transferred to you balance shortly.");
            return ticketPrice * .6;
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Ticket not eligible for refund.");
            return 0;
        }
    }

    public void updateUserProfile(NormalUser normalUser) {
        int id = normalUser.getId();
        String username = normalUser.getUserName();
        String email = normalUser.getEmail();
        String password = normalUser.getPassword();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "UPDATE users SET username = ?, pass = ?, email = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAdminProfile(Admin admin) {
        int id = admin.getId();
        String username = admin.getUserName();
        String email = admin.getEmail();
        String password = admin.getPassword();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "UPDATE admins SET username = ?, pass = ?, email = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTable(String table) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, "root", "1384519im");

            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM " + table);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getScreening(String table) {
        ResultSet screeningSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, "root", "1384519im");

            Statement statement = connection.createStatement();

             screeningSet = statement.executeQuery("SELECT * FROM " + table.toLowerCase());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return screeningSet;
    }
    public ResultSet getMovieScreening(String table, String movieTitle) {
        ResultSet screeningSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String query = "SELECT * FROM " + table.toLowerCase() + " WHERE displayed_movie = ?;";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movieTitle);

            screeningSet = preparedStatement.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return screeningSet;
    }
    public void addScreeningToDB(Screening screening) {
        String theaterTitle = screening.getTheaterTitle();
        String movieTitle = screening.getMovieTitle();
        int screenNumber = screening.getScreenNumber();
        Date date = screening.getDate();
        Time time = screening.getTime();
        String bookedSeats = screening.getBookedSeats();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "INSERT INTO " + theaterTitle.toLowerCase() + " VALUES(?, ?, ?, ?, ?);";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, screenNumber);
            preparedStatement.setString(2, movieTitle);
            preparedStatement.setDate(3, date);
            preparedStatement.setTime(4, time);
            preparedStatement.setString(5, bookedSeats);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeScreeningFromDB(Screening screening) {
        String theaterTitle = screening.getTheaterTitle();
        String movieTitle = screening.getMovieTitle();
        int screenNumber = screening.getScreenNumber();
        Date date = screening.getDate();
        Time time = screening.getTime();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "DELETE FROM " + theaterTitle.toLowerCase() + " WHERE screen_number = ? AND displayed_movie = ? AND date = ? AND time = ?;";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, screenNumber);
            preparedStatement.setString(2, movieTitle);
            preparedStatement.setDate(3, date);
            preparedStatement.setTime(4, time);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setScreeningSeats(Screening screening) {
        String theaterTitle = screening.getTheaterTitle();
        String bookedSeats = screening.getBookedSeats();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "UPDATE " + theaterTitle.toLowerCase() + " SET booked_seats = ?;";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, bookedSeats);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBookingToDB(String username, Bookings booking) {
        String movieTitle = booking.getMovieTitle();
        String theaterTitle = booking.getTheaterTitle();
        int screenNumber = booking.getScreenNumber();
        Date date = booking.getScreenDate();
        Time time = booking.getScreenTime();
        String bookedSeats = booking.getBookedSeats();
        double totalPrice = booking.getTotalPrice();
        String ticketStatus = booking.getTicketStatus();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String insertQuery = "INSERT INTO bookings VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connection connection = DriverManager.getConnection(url, "root", "1384519im");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, movieTitle);
            preparedStatement.setString(3, theaterTitle);
            preparedStatement.setInt(4, screenNumber);
            preparedStatement.setTime(5, time);
            preparedStatement.setDate(6, date);
            preparedStatement.setString(7, bookedSeats);
            preparedStatement.setDouble(8, totalPrice);
            preparedStatement.setString(9, ticketStatus);

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
