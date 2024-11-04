package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MovieController extends Controller {
    @FXML
    Label movieTitle;
    @FXML
    ImageView image;
    @FXML
    AnchorPane card;

    Movie movie;
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setData(String title, String path) {
        Image img = new Image(path);
        movieTitle.setText(title);
        image.setImage(img);
    }

    public void loadMoviePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-page.fxml"));
        root = loader.load();

        PageController pageController = loader.getController();
        pageController.setMovie(movie);
        loadPage(root, event, "TiGet");
    }
}
