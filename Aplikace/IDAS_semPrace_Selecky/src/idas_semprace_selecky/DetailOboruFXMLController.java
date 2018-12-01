/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idas_semprace_selecky;

import data.Kategorie;
import data.Obor;
import data.PredOboru;
import data.Predmet;
import database.databaseHelper;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Libor Selecky
 */
public class DetailOboruFXMLController implements Initializable {

    @FXML
    private TableView<PredOboru> twPredOboru;
    @FXML
    private TableColumn<PredOboru, Predmet> predObCol;
    @FXML
    private TableColumn<PredOboru, Kategorie> katPredObCol;
    @FXML
    private TableColumn<PredOboru, Integer> rocPredObCol;
    @FXML
    private TableColumn<PredOboru, Integer> odhadPredObCol;
    @FXML
    private Label labelObor;
    @FXML
    private TextField rocnikPredObField;
    @FXML
    private ComboBox<Kategorie> comboKatPred;
    @FXML
    private ComboBox<Predmet> comboPredOboru;
    @FXML
    private Button pridatPredObBtn;

    ObservableList<PredOboru> listPred;

    private databaseHelper dh;
    private Obor obor;

    public DetailOboruFXMLController(databaseHelper dh, Obor obor) {
        this.dh = dh;
        this.obor = obor;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        labelObor.setText(obor.getNazev());
        try {
            comboKatPred.getItems().setAll(dh.dejKategorie());
            comboPredOboru.getItems().setAll(dh.dejKartaPredmety());
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }

        //Nastavení spřažení pro tabulku předmětů oboru
        twPredOboru.setItems(listPred);
        predObCol.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        katPredObCol.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        rocPredObCol.setCellValueFactory(new PropertyValueFactory<>("rocnik"));
        odhadPredObCol.setCellValueFactory(new PropertyValueFactory<>("odhad"));

        twPredOboru.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PredOboru>() {
            @Override
            public void changed(ObservableValue<? extends PredOboru> observable, PredOboru oldValue, PredOboru newValue) {
                if (newValue != null) {
                    pridatPredObBtn.setDisable(true);
                    PredOboru pred = newValue;
                    comboPredOboru.getSelectionModel().select(pred.getPredmet());
                    comboKatPred.getSelectionModel().select(pred.getKategorie());
                    rocnikPredObField.setText(Integer.toString(pred.getRocnik()));
                }
            }
        });
        aktualizujPredOboru();
    }

    private void aktualizujPredOboru() {
        try {
            listPred = FXCollections.observableArrayList(dh.dejKartaPredOboru(obor.getId()));
            twPredOboru.setItems(listPred);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void pridejPredOboru(ActionEvent event) {
        try {
            if (comboPredOboru.getValue() != null && comboKatPred.getValue() != null) {
                if (isNumeric(rocnikPredObField.getText())) {
                    PredOboru novy = new PredOboru(Integer.parseInt(rocnikPredObField.getText()), comboKatPred.getValue(), comboPredOboru.getValue());
                    dh.insertDataPredOboru(novy, obor);
                    vycistiFormularPredOboru();
                    aktualizujPredOboru();
                }
            } else {
                zobrazChybu("Vyplňte všechny údaje!");
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void odeberPredOboru(ActionEvent event) {
        PredOboru predOb = twPredOboru.getSelectionModel().getSelectedItem();
        if (predOb != null) {
            try {
                dh.deleteDataPredOboru(predOb);
                listPred.remove(predOb);
                vycistiFormularPredOboru();
            } catch (SQLException ex) {
                zobrazChybu(ex);
            }
        }
    }

    @FXML
    private void upravPredOboru(ActionEvent event) {
        try {
            if (comboPredOboru.getSelectionModel().getSelectedItem() != null && comboKatPred.getSelectionModel().getSelectedItem() != null) {
                if (isNumeric(rocnikPredObField.getText())) {
                    PredOboru vybrany = twPredOboru.getSelectionModel().getSelectedItem();
                    vybrany.setRocnik(Integer.parseInt(rocnikPredObField.getText()));
                    vybrany.setPredmet(comboPredOboru.getValue());
                    vybrany.setKategorie(comboKatPred.getValue());
                    dh.updateDataPredOboru(vybrany, obor);
                    vycistiFormularPredOboru();
                    twPredOboru.refresh();
                }
            } else {
                zobrazChybu("Vyplňte všechny údaje!");
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    private void vycistiFormularPredOboru() {
        pridatPredObBtn.setDisable(false);
        comboPredOboru.getSelectionModel().select(null);
        comboKatPred.getSelectionModel().select(null);
        rocnikPredObField.setText("");
    }

    @FXML
    private void vycistiFormPredOboru(ActionEvent event) {
        vycistiFormularPredOboru();
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

    private boolean isNumeric(String text) throws NumberFormatException {
        try {
            int num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            zobrazChybu(new NumberFormatException("Vstupní údaj musí být číslo!\n" + e.getLocalizedMessage()));
            return false;
        }
        return true;
    }

}
