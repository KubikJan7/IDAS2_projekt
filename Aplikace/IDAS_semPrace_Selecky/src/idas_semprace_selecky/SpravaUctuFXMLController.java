/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idas_semprace_selecky;

import data.Role;
import data.Ucet;
import data.Uzivatel;
import database.databaseHelper;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Libor Selecky
 */
public class SpravaUctuFXMLController implements Initializable {

    AppFXMLController puvodniOkno;
    private databaseHelper dh;
    private Uzivatel uziv;

    @FXML
    private TextField nickField;
    @FXML
    private PasswordField hesloField;
    @FXML
    private ComboBox<Role> comboRole;
    @FXML
    private Button btnPridat;
    @FXML
    private Button btnUpravit;
    @FXML
    private Button btnSmazat;

    ArrayList<Role> role;

    SpravaUctuFXMLController(AppFXMLController puvodniOkno, databaseHelper dh, Uzivatel uziv) {
        this.puvodniOkno = puvodniOkno;
        this.dh = dh;
        this.uziv = uziv;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            role = dh.dejRole();
            comboRole.getItems().addAll(role.get(0), role.get(1));

            if (uziv.getRole().getId() != 3) {
                nickField.setText(uziv.getUcet().getNickname());
                hesloField.setText(uziv.getUcet().getHeslo());
                comboRole.getSelectionModel().select(uziv.getRole().getId() - 1);
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void pridejUcet(ActionEvent event) {
        if (!nickField.getText().isEmpty() && !hesloField.getText().isEmpty() && comboRole.getSelectionModel().getSelectedItem() != null) {
            Ucet puvodni = uziv.getUcet();
            Role puvodniRole = uziv.getRole();
            uziv.setUcet(new Ucet(nickField.getText(), hesloField.getText()));
            uziv.setRole(comboRole.getSelectionModel().getSelectedItem());
            try {
                dh.insertDataUcet(uziv);
                Stage stage = (Stage) btnPridat.getScene().getWindow();
                stage.close();
                puvodniOkno.aktualizujVyucujici();
            } catch (SQLException ex) {
                uziv.setUcet(puvodni);
                uziv.setRole(puvodniRole);
                if (ex.getErrorCode() == 20001) {
                    zobrazChybu("Uživatel už účet má!");
                } else if (ex.getErrorCode() == 20002) {
                    zobrazChybu("Uživatel s tímto jménem již existuje!");
                } else {
                    zobrazChybu(ex);
                }
            }
        } else {
            zobrazChybu("Nejsou vyplněna všechna pole!");
        }
    }

    @FXML
    private void upravUcet(ActionEvent event) {
        if (!nickField.getText().isEmpty() && !hesloField.getText().isEmpty() && comboRole.getSelectionModel().getSelectedItem() != null) {
            Ucet puvodni = uziv.getUcet();
            Role puvodniRole = uziv.getRole();
            uziv.setUcet(new Ucet(nickField.getText(), hesloField.getText()));
            uziv.setRole(comboRole.getSelectionModel().getSelectedItem());
            try {
                dh.updateDataUcet(uziv);
                Stage stage = (Stage) btnUpravit.getScene().getWindow();
                stage.close();
                puvodniOkno.aktualizujVyucujici();
            } catch (SQLException ex) {
                uziv.setUcet(puvodni);
                uziv.setRole(puvodniRole);
                if (ex.getErrorCode() == 20001) {
                    zobrazChybu("Uživatel s tímto jménem již existuje!");
                } else {
                    zobrazChybu(ex);
                }
            }
        } else {
            zobrazChybu("Nejsou vyplněna všechna pole!");
        }
    }

    @FXML
    private void smazUcet(ActionEvent event) {
        try {
            dh.deleteDataUcet(uziv);
            Stage stage = (Stage) btnSmazat.getScene().getWindow();
            stage.close();
            puvodniOkno.aktualizujVyucujici();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 20001) {
                zobrazChybu("Uživatel nemá účet!");
            } else {
                zobrazChybu(ex);
            }
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
