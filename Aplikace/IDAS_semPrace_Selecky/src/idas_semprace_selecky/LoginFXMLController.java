/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idas_semprace_selecky;

import database.databaseHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Libor Selecky
 */
public class LoginFXMLController implements Initializable {

    private databaseHelper dh;
    
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button vymazBtn;
    @FXML
    private Button guestBtn;

    /**
     * Initializes the controller class.
     */
    
    public LoginFXMLController(databaseHelper dh) {
        this.dh = dh;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameField.setText("admin");
        passwordField.setText("admin");
    }

    @FXML
    private void inputCheck(KeyEvent event) {
        if (usernameField.getText().length() != 10 || passwordField.getText().isEmpty()) {
            loginBtn.setDisable(true);
        } else {
            loginBtn.setDisable(false);
        }
    }

    @FXML
    private void prihlas(ActionEvent event) {
    }

    @FXML
    private void vycistiUdaje(ActionEvent event) {
        usernameField.setText("");
        passwordField.setText("");
    }

    @FXML
    private void prihlasHosta(ActionEvent event) {
        otevriOknoAplikace();
    }

    private void otevriOknoAplikace() {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("AppFXML.fxml"));
        AppFXMLController contr = new AppFXMLController(dh);
        loader.setController(contr);
        final Parent root;
        try {
            root = loader.load();
            final Scene scene = new Scene(root);

            Stage stage2 = new Stage();
            stage2.setTitle("Vysoká škola");
            stage2.setResizable(false);
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            zobrazChybu(ex);
        }
    }

    private void zobrazChybu(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText("Chyba");
        alert.setContentText(e);
        DialogPane dp = alert.getDialogPane();
        dp.getStylesheets().add(getClass().getResource("styl.css").toExternalForm());
        alert.showAndWait();
    }

    private void zobrazChybu(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText("Chyba");
        alert.setContentText(e.getLocalizedMessage());
        DialogPane dp = alert.getDialogPane();
        dp.getStylesheets().add(getClass().getResource("styl.css").toExternalForm());
        alert.showAndWait();
    }
}
