package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;

public class SignupController extends Controller {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField emailAddress;

    public void sinUp(ActionEvent event) throws SQLException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        String email = emailAddress.getText();

        if (!userExist(username, email)) {
            return;
        }

        if (!adminExist(username, email)) {
            return;
        }

        if(!checkEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "ERROR" , "invalid email format");
            return;
        }
        addUser(username, password, email);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        loadPage(root, event, "TiGet");
    }

    private boolean userExist(String username, String email) throws SQLException {

        getUsers();
        ResultSet resultSetUser = getResultSet();
        while (resultSetUser.next()) {
            String usernameExist = resultSetUser.getString(2);
            String emailExist = resultSetUser.getString(4);
            if (usernameExist.equals(username)) {
                this.username.clear();
                showAlert(Alert.AlertType.ERROR, "ERROR", "username already exist");
                return false;
            }
            if (email.equals(emailExist)) {
                this.emailAddress.clear();
                showAlert(Alert.AlertType.ERROR, "ERROR", "email address already exist");
                return false;
            }
        }
        return true;
    }

    private boolean adminExist(String username, String email) throws SQLException {
        getAdmins();
        ResultSet resultSetAdmin = getResultSet();
        while (resultSetAdmin.next()) {
            String usernameExist = resultSetAdmin.getString(2);
            String emailExist = resultSetAdmin.getString(3);
            if (usernameExist.equals(username)) {
                this.username.clear();
                showAlert(Alert.AlertType.ERROR, "ERROR", "username already exist");
                return false;
            }
            if (email.equals(emailExist)) {
                this.emailAddress.clear();
                showAlert(Alert.AlertType.ERROR, "ERROR", "email address already exist");
                return false;
            }
        }
        return true;
    }

    private void addUser(String username, String password, String email) throws SQLException {
        int id = getMaxID();
        NormalUser normalUser = new NormalUser(username, password, email, id, 0.0);
        addUserToDB(normalUser);
    }

    private boolean checkEmail(String email) {return Pattern.matches("[a-zA-Z0-9.]+@gmail.com", email);}

    private int getMaxID() throws SQLException {
        int maxID = 0;
        getUsers();
        ResultSet resultSetUser = getResultSet();
        while (resultSetUser.next()) {
            int id = resultSetUser.getInt(1);
            if(maxID < id) {
                maxID = id;
            }
        }
        maxID++;
        return maxID;
    }
}
