package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AdminDashboardController extends Controller implements Initializable {

    @FXML
    private AnchorPane mainForm;
    @FXML
    private AnchorPane screeningsPage;
    @FXML
    private AnchorPane theatersPage;
    @FXML
    private AnchorPane moviesPage;
    @FXML
    private AnchorPane editInfoPage;

    @FXML
    private TextField emailField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label username;

    @FXML
    private ImageView poster;

    @FXML
    private TextField movieID;
    @FXML
    private TextField movieTitle;
    @FXML
    private TextField movieDirector;
    @FXML
    private TextField movieGenre;
    @FXML
    private TextArea movieCast;
    @FXML
    private TextArea movieDescription;
    @FXML
    private DatePicker movieReleaseDate;
    @FXML
    private TextField movieTicketPrice;
    @FXML
    private TableView<Movie> moviesTable;
    @FXML
    private TableColumn<Movie, Integer> movieIDColumn;
    @FXML
    private TableColumn<Movie, String> movieTitleColumn;
    @FXML
    private TableColumn<Movie, String> movieGenreColumn;
    @FXML
    private TableColumn<Movie, String> movieDirectorColumn;
    @FXML
    private TableColumn<Movie, Date> movieReleaseDateColumn;
    @FXML
    private TableColumn<Movie, String> movieCastColumn;
    @FXML
    private TableColumn<Movie, String> movieDescriptionColumn;
    @FXML
    private TableColumn<Movie, Double> movieTicketPriceColumn;

    @FXML
    private TextField theaterID;
    @FXML
    private TextField theaterTitle;
    @FXML
    private TextField theaterScreenCount;
    @FXML
    private TextField theaterPhoneNumber;
    @FXML
    private TextArea theaterLocation;
    @FXML
    private TableView<Theater> theatersTable;
    @FXML
    private TableColumn<Theater, Integer> theaterIDColumn;
    @FXML
    private TableColumn<Theater, String> theaterTitleColumn;
    @FXML
    private TableColumn<Theater, Integer> theaterScreenCountColumn;
    @FXML
    private TableColumn<Theater, String> theaterPhoneNumberColumn;
    @FXML
    private TableColumn<Theater, String> theaterLocationColumn;

    @FXML
    private ComboBox<String> movieChooser;
    @FXML
    private ComboBox<String> theaterChooser;
    @FXML
    private ComboBox<Integer> screenChooser;
    @FXML
    private DatePicker screenDateChooser;
    @FXML
    private TextField screenTimeSetter;
    @FXML
    private TableView<Screening> screeningsTable;
    @FXML
    private TableColumn<Screening, String> screenTheaterColumn;
    @FXML
    private TableColumn<Screening, Integer> screenNumberColumn;
    @FXML
    private TableColumn<Screening, String> screenMovieColumn;
    @FXML
    private TableColumn<Screening, Date> screenDateColumn;
    @FXML
    private TableColumn<Screening, Time> screenTimeColumn;

    private String path;
    private Movie movie;
    private Theater theater;
    private Screening screening;
    private ObservableList<Screening> screeningsList;

    private ActionEvent event;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateMovieTable();
        populateTheaterTable();
        populateScreeningsTable();
        populateMovieChooser();
        populateMTheaterChooser();
    }

    public void setEvent(ActionEvent event) {
        this.event = event;
    }

    public void showScreenings() {
        theatersPage.setVisible(false);
        moviesPage.setVisible(false);
        editInfoPage.setVisible(false);
        screeningsPage.setVisible(true);
    }
    public void showTheaters() {
        moviesPage.setVisible(false);
        editInfoPage.setVisible(false);
        screeningsPage.setVisible(false);
        theatersPage.setVisible(true);
    }
    public void showMovies() {
        theatersPage.setVisible(false);
        editInfoPage.setVisible(false);
        screeningsPage.setVisible(false);
        moviesPage.setVisible(true);
    }
    public void showEditInfo() {
        theatersPage.setVisible(false);
        screeningsPage.setVisible(false);
        moviesPage.setVisible(false);
        editInfoPage.setVisible(true);
    }

    public void importImage() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg", "*jpeg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        path = file.getName();
        System.out.println(path);
        Image image = new Image(file.toURI().toString(), 124, 170, false, true);
        poster.setImage(image);
    }
    public void addMovie() {
        try {
            LocalDate localDate = movieReleaseDate.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.sql.Date date = new java.sql.Date(Date.from(instant).getTime());
            Movie movie = new Movie(Integer.parseInt(movieID.getText()), movieTitle.getText(), movieGenre.getText(),
                    movieDirector.getText(), date,
                    movieCast.getText(), movieDescription.getText(),
                    Double.parseDouble(movieTicketPrice.getText()), path);
            addMovieToDB(movie);
            moviesTable.getItems().clear();
            populateMovieTable();
            showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "The movie has been successfully added.");
        } catch(Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
        }
    }
    public void editMovie() {
        try {
            LocalDate localDate = movieReleaseDate.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.sql.Date date = new java.sql.Date(Date.from(instant).getTime());

            path = movie.getImagePath();
            Movie movie = new Movie(Integer.parseInt(movieID.getText()), movieTitle.getText(), movieGenre.getText(),
                    movieDirector.getText(), date,
                    movieCast.getText(), movieDescription.getText(),
                    Double.parseDouble(movieTicketPrice.getText()), path);
            editMovieDB(movie);
            moviesTable.getItems().clear();
            populateMovieTable();
            showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "The movie has been successfully edited.");
        } catch(Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
        }
    }
    public void removeMovie() {
        int id = movie.getId();
        removeMovieFromDB(id);
        moviesTable.getItems().clear();
        populateMovieTable();
        showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "The movie has been successfully removed.");
    }
    public void clearMovieFields() {
        movieID.setDisable(false);
        movieID.clear();
        movieTitle.clear();
        movieGenre.clear();
        movieDirector.clear();
        movieReleaseDate.setValue(null);
        movieCast.clear();
        movieDescription.clear();
        movieTicketPrice.clear();
        poster.setImage(null);
    }

    public void addTheater() {
        try {
            Theater theater = new Theater(Integer.parseInt(theaterID.getText()), theaterTitle.getText(),
                    Integer.parseInt(theaterScreenCount.getText()), theaterPhoneNumber.getText(),
                    theaterLocation.getText());
            addTheaterToDB(theater);
            theatersTable.getItems().clear();
            populateTheaterTable();
            showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "The theater has been successfully added.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled");
        }
    }
    public void removeTheater() {
        int id = theater.getId();
        removeTheaterFromDB(id);
        theatersTable.getItems().clear();
        populateTheaterTable();
        showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "The theater has been successfully removed.");
    }
    public void clearTheaterFields() {
        theaterID.setDisable(false);
        theaterID.clear();
        theaterTitle.clear();
        theaterScreenCount.clear();
        theaterPhoneNumber.clear();
        theaterLocation.clear();
    }

    public void updateInfo() {
        String newEmail = emailField.getText();
        String newUsername = nameField.getText();
        String newPassword = passField.getText();

        Admin newAdmin = new Admin(newUsername, newPassword, newEmail, admin.getId());
        updateAdminProfile(newAdmin);

        username.setText(nameField.getText());

        admin = newAdmin;
        showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Your information has been successfully updated");
    }

    private void populateMovieTable() {
        getMovies();
        ObservableList<Movie> moviesList = FXCollections.observableArrayList();
        try {
            while (getResultSet().next()) {
                moviesList.add(new Movie(getResultSet().getInt(1), getResultSet().getString(2),
                            getResultSet().getString(3), getResultSet().getString(4),
                            getResultSet().getDate(5), getResultSet().getString(6),
                            getResultSet().getString(7), getResultSet().getDouble(8),
                            getResultSet().getString(9)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        movieIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieGenreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        movieDirectorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        movieReleaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        movieCastColumn.setCellValueFactory(new PropertyValueFactory<>("cast"));
        movieDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        movieTicketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        moviesTable.setItems(moviesList);

        moviesTable.setRowFactory(tv -> {
            TableRow<Movie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                movie = row.getItem();

                movieID.setText(String.valueOf(movie.getId()));
                movieID.setDisable(true);
                movieTitle.setText(movie.getTitle());
                movieGenre.setText(movie.getGenre());
                movieDirector.setText(movie.getDirector());
                movieReleaseDate.setValue(movie.getReleaseDate().toLocalDate());
                movieCast.setText(movie.getCast());
                movieDescription.setText(movie.getDescription());
                movieTicketPrice.setText(String.valueOf(movie.getTicketPrice()));
                poster.setImage(new Image(movie.getImagePath()));
            });
            return row;
        });
    }
    private void populateTheaterTable() {
        getTheaters();
        ObservableList<Theater> theatersList = FXCollections.observableArrayList();
        try {
            while (getResultSet().next()) {
                theatersList.add(new Theater(getResultSet().getInt(1), getResultSet().getString(2),
                                            getResultSet().getInt(3), getResultSet().getString(4),
                                            getResultSet().getString(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        theaterIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        theaterTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        theaterScreenCountColumn.setCellValueFactory(new PropertyValueFactory<>("screenCount"));
        theaterPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        theaterLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        theatersTable.setItems(theatersList);

        theatersTable.setRowFactory(tv -> {
            TableRow<Theater> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                theater = row.getItem();

                theaterID.setText(String.valueOf(theater.getId()));
                theaterID.setDisable(true);
                theaterTitle.setText(theater.getTitle());
                theaterScreenCount.setText(String.valueOf(theater.getScreenCount()));
                theaterPhoneNumber.setText(theater.getPhoneNumber());
                theaterLocation.setText(theater.getLocation());
            });
            return row;
        });
    }
    private void populateScreeningsTable() {
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
        ResultSet resultSet = getScreening(theaterTitle);
        try {
            while (resultSet.next()) {
                screeningsList.add(new Screening(theaterTitle, resultSet.getString(2),
                        resultSet.getInt(1), resultSet.getDate(3),
                        resultSet.getTime(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        screenTheaterColumn.setCellValueFactory(new PropertyValueFactory<>("theaterTitle"));
        screenNumberColumn.setCellValueFactory(new PropertyValueFactory<>("screenNumber"));
        screenMovieColumn.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        screenDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        screenTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        screeningsTable.setItems(screeningsList);

        screeningsTable.setRowFactory(tv -> {
            TableRow<Screening> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                screening = row.getItem();

                movieChooser.setValue(screening.getMovieTitle());
                theaterChooser.setValue(screening.getTheaterTitle());
                screenChooser.setValue(screening.getScreenNumber());
                screenDateChooser.setValue(screening.getDate().toLocalDate());
                screenTimeSetter.setText(String.valueOf(screening.getTime()));
            });
            return row;
        });
    }

    public void populateMovieChooser() {
        getMovies();
        ObservableList<String> movieOptions = FXCollections.observableArrayList();
        try {
            while (getResultSet().next()) {
                movieOptions.add(getResultSet().getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        movieChooser.setItems(movieOptions);
    }
    public void populateMTheaterChooser() {
        getTheaters();
        ObservableList<String> theaterOptions = FXCollections.observableArrayList();
        try {
            while (getResultSet().next()) {
                theaterOptions.add(getResultSet().getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        theaterChooser.setItems(theaterOptions);
    }
    public void getTheaterScreens() {
        ObservableList<Integer> screens = FXCollections.observableArrayList();
        theaterChooser.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String theaterTitle) {
                getTheaters();
                int screenCount = 1;
                try {
                    while (getResultSet().next()) {
                        if (getResultSet().getString(2).equals(theaterTitle))
                            screenCount = getResultSet().getInt(3);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (int i = screenCount; i >= 1; i--) {
                    screens.add(i);
                }
                screenChooser.setItems(screens);
            }
        });
    }

    public void addScreening() {
        LocalDate date = screenDateChooser.getValue();
        LocalTime time = LocalTime.parse(screenTimeSetter.getText());
        Screening screening = new Screening(theaterChooser.getValue(), movieChooser.getValue(),
                screenChooser.getValue(), Date.valueOf(date),
                Time.valueOf(time), "");
        addScreeningToDB(screening);
        populateScreeningsTable();
        showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "The screening has been successfully added.");
    }
    public void removeScreening() {
        LocalDate date = screenDateChooser.getValue();
        LocalTime time = LocalTime.parse(screenTimeSetter.getText());
        Screening screening = new Screening(theaterChooser.getValue(), movieChooser.getValue(),
                screenChooser.getValue(), Date.valueOf(date),
                Time.valueOf(time), "");
        removeScreeningFromDB(screening);
        populateScreeningsTable();
        showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "The screening has been successfully removed.");
    }

    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        loadPage(root, event, "TiGet");
    }
}
