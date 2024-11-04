package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> theaterSelector;
    @FXML
    private ComboBox<String> genreSelector;

    @FXML
    private TextField searchBar;

    @FXML
    private GridPane mainGrid;

    ActionEvent event;

    String genre = "All";

    public void setEvent(ActionEvent e) {
        Button button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button was clicked!");
            }
        });
        event = e;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getMovies();
        populateStore();
        populateGenreChooser();
        populateTheaterChooser();
    }

    private void populateStore() {

        int column = 0, row = 0;

        mainGrid.getRowConstraints().clear();
        mainGrid.getColumnConstraints().clear();

        try {
            while (getResultSet().next()) {
                if ("All".equals(genre) || getResultSet().getString(3).contains(genre)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("test.fxml"));

                    AnchorPane card = fxmlLoader.load();

                    MovieController movieController = fxmlLoader.getController();

                    Movie movie = new Movie(getResultSet().getInt(1), getResultSet().getString(2), getResultSet().getString(3),
                            getResultSet().getString(4), getResultSet().getDate(5),
                            getResultSet().getString(6), getResultSet().getString(7),
                            getResultSet().getDouble(8), getResultSet().getString(9));

                    movieController.setMovie(movie);

                    movieController.setData(getResultSet().getString(2),
                            getResultSet().getString(9));

                    if (column == 9) {
                        column = 0;
                        row++;
                    }

                    mainGrid.add(card, column++, row);

                    mainGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    mainGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    mainGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    mainGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    mainGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    mainGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(card, new Insets(10));
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchMovie() {
        int column = 0, row = 0;

        getMovies();
        String movieName = searchBar.getText();
        try {
            mainGrid.getChildren().clear();
            mainGrid.getRowConstraints().clear();
            mainGrid.getColumnConstraints().clear();
            while (getResultSet().next()) {
                System.out.println(getResultSet().getString(2));

                if (SearchSimilarity.similarity(getResultSet().getString(2), movieName) > .25) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("test.fxml"));

                    AnchorPane card = fxmlLoader.load();

                    MovieController movieController = fxmlLoader.getController();

                    Movie movie = new Movie(getResultSet().getInt(1), getResultSet().getString(2), getResultSet().getString(3),
                            getResultSet().getString(4), getResultSet().getDate(5),
                            getResultSet().getString(6), getResultSet().getString(7),
                            getResultSet().getDouble(8), getResultSet().getString(9));

                    movieController.setMovie(movie);

                    movieController.setData(getResultSet().getString(2),
                            getResultSet().getString(9));

                    if (column == 9) {
                        column = 0;
                        row++;
                    }

                    mainGrid.add(card, column++, row);

                    mainGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    mainGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    mainGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    mainGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    mainGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    mainGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(card, new Insets(10));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void showProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("user-dashboard.fxml"));
        root = loader.load();

        loadPage(root, event, "TiGet");
    }

    private void populateGenreChooser() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Action", "Adventure", "Mystery",
                "Thriller", "Sci-Fi", "Drama", "Horror",
                "Crime", "Comedy", "Romance", "All"
        );
        genreSelector.setItems(options);
    }

    public void selectItemGenre() {
        genreSelector.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                genre = newValue;
                mainGrid.getChildren().clear();
                getMovies();
                populateStore();
            }
        });
    }

    private void populateTheaterChooser() {
        getTheaters();
        ObservableList<String> options = FXCollections.observableArrayList();
        try {
            while (getResultSet().next()) {
                options.add(getResultSet().getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        options.add("All");
        theaterSelector.setItems(options);
    }

    public void selectItemTheater() {
        theaterSelector.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                int column = 0;
                int row = 0;
                if ("All".equals(newValue)) {
                    getMovies();
                    populateStore();
                } else {
                    try {
                        ResultSet resultSet = getScreening(newValue);
                        mainGrid.getChildren().clear();
                        while (resultSet.next()) {
                            findDisplayedMovie(resultSet.getString(2), column, row);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void findDisplayedMovie(String movieTitle, int column, int row) {
        getMovies();
        mainGrid.getRowConstraints().clear();
        mainGrid.getColumnConstraints().clear();

        try {

            while (getResultSet().next()) {
                String currentTitle = getResultSet().getString(2);
                System.out.println("Checking movie: " + currentTitle); // Debug: check current movie title

                if (movieTitle.equals(currentTitle)) {
                    System.out.println("Found matching movie: " + currentTitle); // Debug: matching movie found

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("test.fxml"));

                    AnchorPane card = fxmlLoader.load();

                    MovieController movieController = fxmlLoader.getController();

                    Movie movie = new Movie(getResultSet().getInt(1), getResultSet().getString(2), getResultSet().getString(3),
                            getResultSet().getString(4), getResultSet().getDate(5),
                            getResultSet().getString(6), getResultSet().getString(7),
                            getResultSet().getDouble(8), getResultSet().getString(9));

                    movieController.setMovie(movie);

                    movieController.setData(getResultSet().getString(2),
                            getResultSet().getString(9));

                    if (column == 9) {
                        column = 0;
                        row++;
                    }

                    mainGrid.add(card, column++, row);

                    mainGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    mainGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    mainGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    mainGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    mainGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    mainGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(card, new Insets(10));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}