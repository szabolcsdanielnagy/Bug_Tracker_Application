package controllers;

import core.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Controller class for the login panel.
 *
 * @author szabolcsdnagy
 * @version 27.03.2021
 */
public class LoginController implements Initializable {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    /**
     * Method called upon pressing the login button.
     */
    @FXML
    public void loginButtonHandler() {
        if (!userName.getText().isBlank() && !password.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Login credential(s) missing.");
        }
    }

    /**
     * Initialize everything.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    /**
     * Validates the login phase. If the user/password combination can be found in the database,
     * it logs the user in, otherwise an error message is shown.
     */
    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnectionLink();
        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + userName.getText() + "' AND password ='" + password.getText() + "'";
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Successfully logged in.");
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
