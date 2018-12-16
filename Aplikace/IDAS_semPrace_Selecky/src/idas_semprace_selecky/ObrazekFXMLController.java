/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idas_semprace_selecky;

import data.Obrazek;
import data.Uzivatel;
import database.databaseHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Libor Selecky
 */
public class ObrazekFXMLController implements Initializable {

    AppFXMLController puvodniOkno;
    private databaseHelper dh;
    private Uzivatel uziv, prihlaseny;

    @FXML
    private Label textUzivatel;
    @FXML
    private ImageView iwObrazek;
    @FXML
    private Button btnNahrat;
    @FXML
    private Button btnSmazat;

    ObrazekFXMLController(AppFXMLController puvodniOkno, databaseHelper dh, Uzivatel uziv, Uzivatel prihlaseny) {
        this.puvodniOkno = puvodniOkno;
        this.dh = dh;
        this.uziv = uziv;
        this.prihlaseny = prihlaseny;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String titul = "";
        if (uziv.getTitulPred() != null) {
            titul = uziv.getTitulPred();
        }
        textUzivatel.setText(titul + " " + uziv.getJmeno() + " " + uziv.getPrijmeni());
        Image image = new Image(getClass().getResourceAsStream("noImage.png"));
        iwObrazek.setImage(image);
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

    private void zobrazChybu(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText("Chyba");
        alert.setContentText(e);
        DialogPane dp = alert.getDialogPane();
        dp.getStylesheets().add(getClass().getResource("styl.css").toExternalForm());
        alert.showAndWait();
    }

    private void obnovObrazek() {
        // TODO dopsat načtení obrázku při otevření okna
        Obrazek obr = null;
        try {
            obr = dh.dejObrazek(uziv.getId());
            iwObrazek.setImage(new Image(obr.getObsah()));
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void nahrajObrazek(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("images (*.png, *.jpg)", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(btnNahrat.getScene().getWindow());
        if (file != null) {
            try {
                InputStream is = new FileInputStream(file);
                String[] nazev = file.getName().split("\\.");
                dh.insertDataObrazek(uziv.getId(), new Obrazek(nazev[0], nazev[1], is));
                obnovObrazek();
            } catch (FileNotFoundException | SQLException ex) {
                zobrazChybu(ex);
            }
        }
    }

    // TODO dopsat mazani obrazku
    @FXML
    private void smazObrazek(ActionEvent event) {
    }
}
