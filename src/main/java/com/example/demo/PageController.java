package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class PageController extends Controller {
    @FXML
    private Label movieTitle;
    @FXML
    private Label movieGenre;
    @FXML
    private Label directorName;
    @FXML
    private Label ticketPrice;
    @FXML
    private Label releaseDate;
    @FXML
    private Text castList;
    @FXML
    private Text movieDescription;

    @FXML
    private ImageView moviePoster;

    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
        movieTitle.setText(movie.getTitle());
        movieGenre.setText(movie.getGenre());
        directorName.setText(movie.getDirector());
        releaseDate.setText(String.valueOf(movie.getReleaseDate()));
        castList.setText(movie.getCast());
        movieDescription.setText(movie.getDescription());
        ticketPrice.setText("$" + movie.getTicketPrice());
        moviePoster.setImage(new Image(movie.getImagePath()));
    }

    public void buy(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase-page.fxml"));
        root = loader.load();

        PurchaseController purchaseController = loader.getController();
        purchaseController.setMovie(movie);
        purchaseController.setUserBalance(user);
        purchaseController.setListener();
        loadPage(root, e, "TiGet");
    }
    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();

        loadPage(root, event, "TiGet");
    }
}
