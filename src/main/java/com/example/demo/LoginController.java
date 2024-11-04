package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;


public class LoginController extends Controller {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ActionEvent event;

    public void login(ActionEvent event){
        this.event = event;
        boolean flag = false;
        getUsers();
        try {
            while (getResultSet().next()) {
                String userName = getResultSet().getString(2);
                String passwordUser = getResultSet().getString(3);

                if (userName.equals(this.userName.getText()) && passwordUser.equals(this.password.getText())) {
                    String email = getResultSet().getString(4);
                    int id = getResultSet().getInt(1);
                    double accountBalance = getResultSet().getDouble(5);

                    NormalUser normalUser = new NormalUser(userName, passwordUser, email, id, accountBalance);
                    System.out.println(normalUser);
                    showUserDashboard(normalUser);
                    flag = true;
                }
            }
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }

        getAdmins();
        try {
            while (getResultSet().next()) {
                String userName = getResultSet().getString(2);
                String passwordUser = getResultSet().getString(3);

                if (userName.equals(this.userName.getText()) && passwordUser.equals(this.password.getText())) {
                    String email = getResultSet().getString(4);
                    int id = getResultSet().getInt(1);

                    Admin admin = new Admin(userName, passwordUser, email, id);
                    showAdminDashboard(admin);
                    flag = true;
                }

            }
        } catch (SQLException | IOException exception) {
            System.out.println(exception.getMessage());
        }
        if (!flag)
            showAlert(Alert.AlertType.ERROR, "Error", "Username or password incorrect.");

    }

    public void signup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        root = loader.load();

        loadPage(root, event, "TiGet");
    }

    private void showAdminDashboard(Admin admin) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-dashboard.fxml"));
        root = loader.load();

        loadPage(root, event, "TiGet");
    }

    private void showUserDashboard(NormalUser normalUser) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();

        HelloController helloController = loader.getController();
        user = normalUser;
        helloController.setEvent(event);

        loadPage(root, event, "TiGet");
    }
}
