/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idas_semprace_selecky;

import data.Akce;
import data.Fakulta;
import data.Forma;
import data.Katedra;
import data.Obor;
import data.Pracoviste;
import data.Pracoviste2;
import data.Predmet;
import data.Semestr;
import data.Uzivatel;
import data.Zakonceni;
import data.Zpusob;
import database.databaseHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Libor Selecky
 */
public class AppFXMLController implements Initializable {

    private databaseHelper dh;

    //Tabulka vyucujicich
    @FXML
    private TableView<Uzivatel> twVyucujici;
    @FXML
    private TableColumn<Uzivatel, String> vyucTitPredCol;
    @FXML
    private TableColumn<Uzivatel, String> vyucJmenoCol;
    @FXML
    private TableColumn<Uzivatel, String> vyucPrijmeniCol;
    @FXML
    private TableColumn<Uzivatel, String> vyucTitZaCol;
    @FXML
    private TableColumn<Uzivatel, Pracoviste> vyucPracCol;
    @FXML
    private TableColumn<Uzivatel, String> vyucEmailCol;
    @FXML
    private TableColumn<Uzivatel, Integer> vyucMobCol;
    @FXML
    private TableColumn<Uzivatel, Integer> vyucTelCol;

    // Formular vyucujiciho
    // TODO Bude potřeba dodělat pole pro správu admina, uziv_jmeno a heslo .. 
    // Možná také tlačítko a dialog pro zobrazení akcí vyučujícího
    @FXML
    private TextField titulPredField;
    @FXML
    private TextField jmenoField;
    @FXML
    private TextField prijmeniField;
    @FXML
    private TextField titulZaField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField mobilField;
    @FXML
    private TextField telField;
    @FXML
    private Button pridatVyucBtn;
    @FXML
    private Button upravitVyucBtn;
    @FXML
    private Button smazatVyucBtn;

    // Tabulka pracovist
    @FXML
    private TableView<Pracoviste2> twPracoviste;
    @FXML
    private TableColumn<Pracoviste2, String> zkratKatCol;
    @FXML
    private TableColumn<Pracoviste2, String> nazevKatCol;
    @FXML
    private TableColumn<Pracoviste2, String> zkratFakulCol;
    @FXML
    private TableColumn<Pracoviste2, String> nazevFakulCol;

    // Formular pracoviste
    @FXML
    private Button pridatPracBtn;
    @FXML
    private Button upravitPracBtn;
    @FXML
    private Button odebratPracBtn;
    @FXML
    private ComboBox<Katedra> comboPracovist;
    @FXML
    private Button vycistiVyucFormBtn;
    @FXML
    private TextField zkrKatField;
    @FXML
    private TextField nazKatField;
    @FXML
    private Button vycistiPracFormBtn;
    @FXML
    private ComboBox<Fakulta> comboFakulta;

    // Tabulka oboru
    // TODO nutno dodělat správnou posloupnost dialogů => Obor-->Studijní plán (výpis včetně přidávání)-->Předměty v plánu (výpis včetně přidávání)
    @FXML
    private TableView<Obor> twStudObory;
    @FXML
    private TableColumn<Obor, Integer> idStudOborCol;
    @FXML
    private TableColumn<Obor, String> zkrStudOborCol;
    @FXML
    private TableColumn<Obor, String> nazStudOborCol;
    @FXML
    private TableColumn<Obor, String> infoStudOborCol;

    // Formular oboru
    @FXML
    private TextField kodStObField;
    @FXML
    private TextField nazevStObField;
    @FXML
    private TextArea infoStObArea;
    @FXML
    private Button pridatStObBtn;
    @FXML
    private Button detOboruBtn;

    // Formular predmetu
    // TODO Předmět samotný nese nyní informaci pouze o názvu a zkatce, další info bude mít pouze v rámci studijního plánu
    @FXML
    private TextField zkrPredField;
    @FXML
    private ComboBox<Zakonceni> comboZakonPred;
    @FXML
    private Button pridatPredBtn;
    @FXML
    private TextField nazPredField;
    @FXML
    private TextField kredPredField;
    @FXML
    private ComboBox<Semestr> comboSemPred;
    @FXML
    private ComboBox<Forma> comboFormPred;

    // Tabulka predmetu
    @FXML
    private TableView<Predmet> twPredmety;
    @FXML
    private TableColumn<Predmet, String> zkrPredCol;
    @FXML
    private TableColumn<Predmet, String> nazPredCol;
    @FXML
    private TableColumn<Predmet, Integer> kredPredCol;
    @FXML
    private TableColumn<Predmet, Semestr> semPredCol;
    @FXML
    private TableColumn<Predmet, Zakonceni> zakonPredCol;
    @FXML
    private TableColumn<Predmet, Forma> formaPredCol;

    // Tabulka rozvrhovych akci
    // TODO Karta rozvrhových akcí bude vypadat úplně jinak, pro uživatele pouze jeho akce a pro admina všechny akce s filtrem dle vyučujícího
    @FXML
    private TableView<Akce> twRA;
    @FXML
    private TableColumn<Akce, Integer> idAkceCol;
    @FXML
    private TableColumn<Akce, Predmet> predAkceCol;
    @FXML
    private TableColumn<Akce, Zpusob> typAkceCol;
    @FXML
    private TableColumn<Akce, Uzivatel> vyucAkceCol;
    @FXML
    private TableColumn<Akce, Integer> rozsahAkceCol;
    @FXML
    private TableColumn<Akce, Integer> kapacitaAkceCol;

    // Formular rozvrhove akce
    @FXML
    private ComboBox<Uzivatel> comboVyucujici;
    @FXML
    private ComboBox<Predmet> comboPredmet;
    @FXML
    private Button pridatRABtn;
    @FXML
    private ComboBox<Zpusob> comboZpusAkce;
    @FXML
    private TextField rozsahRAField;
    @FXML
    private TextField kapacitaRAField;

    // Kolekce dat pro jednotlive karty
    ArrayList<Fakulta> fakulty;
    ObservableList<Uzivatel> vyucujici;
    ObservableList<Pracoviste2> pracoviste;
    ObservableList<Obor> obory;
    ObservableList<Predmet> predmety;
    ObservableList<Akce> akce;

    public AppFXMLController(databaseHelper dh) {
        this.dh = dh;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Nastavení spřažení pro tabulku karty vyučujících
        twVyucujici.setItems(vyucujici);
        vyucTitPredCol.setCellValueFactory(new PropertyValueFactory("titulPred"));
        vyucJmenoCol.setCellValueFactory(new PropertyValueFactory("jmeno"));
        vyucPrijmeniCol.setCellValueFactory(new PropertyValueFactory("prijmeni"));
        vyucTitZaCol.setCellValueFactory(new PropertyValueFactory("titulZa"));
        vyucEmailCol.setCellValueFactory(new PropertyValueFactory("email"));
        vyucMobCol.setCellValueFactory(new PropertyValueFactory("mobil"));
        vyucTelCol.setCellValueFactory(new PropertyValueFactory("telefon"));
        vyucPracCol.setCellValueFactory(new PropertyValueFactory("pracoviste"));

        // Doplneni informaci do formulare pri vyberu z tabulky Vyucujicich
        twVyucujici.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Uzivatel>() {
            @Override
            public void changed(ObservableValue<? extends Uzivatel> observable, Uzivatel oldValue, Uzivatel newValue) {
                if (newValue != null) {
                    pridatVyucBtn.setDisable(true);
                    try {
                        comboPracovist.getItems().setAll(dh.dejKatedry());
                        Uzivatel pom = twVyucujici.getSelectionModel().getSelectedItem();
                        titulPredField.setText(pom.getTitulPred());
                        titulZaField.setText(pom.getTitulZa());
                        jmenoField.setText(pom.getJmeno());
                        prijmeniField.setText(pom.getPrijmeni());
                        mobilField.setText(Integer.toString(pom.getMobil()));
                        telField.setText(Integer.toString(pom.getTelefon()));
                        emailField.setText(pom.getEmail());

                        Iterator<Katedra> it = comboPracovist.getItems().iterator();
                        Katedra kat = null;
                        while (it.hasNext()) {
                            Katedra akt = it.next();
                            if (akt.getZkratka().compareTo(pom.getPracoviste().getKatedra().getZkratka()) == 0) {
                                kat = akt;
                                break;
                            }
                        }
                        comboPracovist.getSelectionModel().select(kat);
                    } catch (SQLException ex) {
                        zobrazChybu(ex);
                    }
                }
            }
        });

        //Nastavení spřažení pro tabulku karty pracovišť
        twPracoviste.setItems(pracoviste);
        zkratFakulCol.setCellValueFactory(new PropertyValueFactory("zkratkaFak"));
        nazevFakulCol.setCellValueFactory(new PropertyValueFactory("fakulta"));
        zkratKatCol.setCellValueFactory(new PropertyValueFactory("zkratkaKat"));
        nazevKatCol.setCellValueFactory(new PropertyValueFactory("katedra"));

        // Doplneni informaci do formulare pri vyberu z tabulky Pracovist
        twPracoviste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pracoviste2>() {
            @Override
            public void changed(ObservableValue<? extends Pracoviste2> observable, Pracoviste2 oldValue, Pracoviste2 newValue) {
                if (newValue != null) {
                    pridatPracBtn.setDisable(true);
                    zkrKatField.setDisable(true);
                    try {
                        comboFakulta.getItems().setAll(dh.dejFakulty());
                        Pracoviste2 prac = twPracoviste.getSelectionModel().getSelectedItem();
                        zkrKatField.setText(prac.getZkratkaKat());
                        nazKatField.setText(prac.getKatedra());
                        Iterator<Fakulta> it = comboFakulta.getItems().iterator();
                        Fakulta fak = null;
                        while (it.hasNext()) {
                            Fakulta akt = it.next();
                            if (akt.getZkratka().compareTo(prac.getZkratkaFak()) == 0) {
                                fak = akt;
                                break;
                            }
                        }
                        comboFakulta.getSelectionModel().select(fak);
                    } catch (SQLException ex) {
                        zobrazChybu(ex);
                    }
                }
            }
        });

        //Nastavení spřažení pro tabulku karty oborů
        twStudObory.setItems(obory);
        zkrStudOborCol.setCellValueFactory(new PropertyValueFactory("zkratka"));
        nazStudOborCol.setCellValueFactory(new PropertyValueFactory("nazev"));
        infoStudOborCol.setCellValueFactory(new PropertyValueFactory("info"));
        idStudOborCol.setCellValueFactory(new PropertyValueFactory("id"));

        // Doplneni informaci do formulare pri vyberu z tabulky Oborů
        twStudObory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Obor>() {
            @Override
            public void changed(ObservableValue<? extends Obor> observable, Obor oldValue, Obor newValue) {
                if (newValue != null) {
                    detOboruBtn.setDisable(false);
                    pridatStObBtn.setDisable(true);
                    Obor obor = twStudObory.getSelectionModel().getSelectedItem();
                    kodStObField.setText(obor.getZkratka());
                    nazevStObField.setText(obor.getNazev());
                    infoStObArea.setText(obor.getInfo());
                } else {
                    detOboruBtn.setDisable(true);
                }
            }
        });

        //Nastavení spřažení pro tabulku karty předmětů
        twPredmety.setItems(predmety);
        zkrPredCol.setCellValueFactory(new PropertyValueFactory<>("zkratka"));
        nazPredCol.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        kredPredCol.setCellValueFactory(new PropertyValueFactory<>("kredity"));
        semPredCol.setCellValueFactory(new PropertyValueFactory<>("semestr"));
        zakonPredCol.setCellValueFactory(new PropertyValueFactory<>("zakonceni"));
        formaPredCol.setCellValueFactory(new PropertyValueFactory<>("forma"));

        // Doplnění informací do formuláře při výběru z tabulky Předměty
        twPredmety.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Predmet>() {
            @Override
            public void changed(ObservableValue<? extends Predmet> observable, Predmet oldValue, Predmet newValue) {
                if (newValue != null) {
                    pridatPredBtn.setDisable(true);
                    Predmet pred = twPredmety.getSelectionModel().getSelectedItem();
                    zkrPredField.setText(pred.getZkratka());
                    nazPredField.setText(pred.getNazev());
                    kredPredField.setText(Integer.toString(pred.getKredity()));
                    comboSemPred.getSelectionModel().select(pred.getSemestr());
                    comboFormPred.getSelectionModel().select(pred.getForma());
                    comboZakonPred.getSelectionModel().select(pred.getZakonceni());
                }
            }
        });

        //Nastavení spřažení pro tabulku karty rozvrhových akcí
        twRA.setItems(akce);
        idAkceCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        predAkceCol.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        typAkceCol.setCellValueFactory(new PropertyValueFactory<>("zpusob"));
        vyucAkceCol.setCellValueFactory(new PropertyValueFactory<>("vyucujici"));
        rozsahAkceCol.setCellValueFactory(new PropertyValueFactory<>("rozsah"));
        kapacitaAkceCol.setCellValueFactory(new PropertyValueFactory<>("kapacita"));

        // Doplnění informací do formuláře při výběru z tabulky Rozvrhove akce
        twRA.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Akce>() {
            @Override
            public void changed(ObservableValue<? extends Akce> observable, Akce oldValue, Akce newValue) {
                if (newValue != null) {
                    pridatRABtn.setDisable(true);
                    Akce akce = twRA.getSelectionModel().getSelectedItem();
                    comboPredmet.getSelectionModel().select(akce.getPredmet());
                    comboVyucujici.getSelectionModel().select(akce.getVyucujici());
                    comboZpusAkce.getSelectionModel().select(akce.getZpusob());
                    rozsahRAField.setText(Integer.toString(akce.getRozsah()));
                    kapacitaRAField.setText(Integer.toString(akce.getKapacita()));
                }
            }
        });

    }

    // Obnoví data v tabulce Pracovišť
    @FXML
    private void nactiPracoviste(MouseEvent event) {
        try {
            comboPracovist.getItems().setAll(dh.dejKatedry());
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    // Obnoví data v tabulce Vyučujících
    private void aktualizujVyucujici() {
        try {
            vyucujici = FXCollections.observableArrayList(dh.dejKartaVyucujici());
            twVyucujici.setItems(vyucujici);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    // Obnoví data v tabulce Předmětů
    private void aktualizujPredmety() {
        try {
            predmety = FXCollections.observableArrayList(dh.dejKartaPredmety());
            twPredmety.setItems(predmety);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    // Obnoví data v tabulce Rorvhových akcí
    private void aktualizujRA() {
        try {
            akce = FXCollections.observableArrayList(dh.dejKartaAkce());
            twRA.setItems(akce);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    // Obnoví data v tabulce Pracovišť
    private void aktualizujPracoviste() {
        try {
            pracoviste = FXCollections.observableArrayList(dh.dejKartaPracoviste());
            twPracoviste.setItems(pracoviste);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    // Obnoví data v tabulce Oborů
    private void aktualizujObory() {
        try {
            obory = FXCollections.observableArrayList(dh.dejKartaObory());
            twStudObory.setItems(obory);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    // Následují DML metody jednotlivých tabulek figurujících na kartách
    @FXML
    private void pridejVyucujiciho(ActionEvent event) {
        try {
            //comboPracovist.getItems().setAll(dh.dejKatedry());
            if (comboPracovist.getSelectionModel().getSelectedItem() != null && !jmenoField.getText().isEmpty()
                    && !prijmeniField.getText().isEmpty()) {
                String titulPred = titulPredField.getText();
                String titulZa = titulZaField.getText();
                String jmeno = jmenoField.getText();
                String prijmeni = prijmeniField.getText();
                String email = emailField.getText();
                Katedra katedra = comboPracovist.getSelectionModel().getSelectedItem();
                int mobil = 0;
                int telefon = 0;

                Uzivatel novy = new Uzivatel(titulPred, jmeno, prijmeni, titulZa, email, mobil, telefon, new Pracoviste(null, katedra));
                dh.insertDataVyuc(novy);
                aktualizujVyucujici();
                vycistiFormularVyucujici();
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void upravVyucujiciho(ActionEvent event) {
        try {
            if (comboPracovist.getSelectionModel().getSelectedItem() != null && !jmenoField.getText().isEmpty()
                    && !prijmeniField.getText().isEmpty() && twVyucujici.getSelectionModel().getSelectedItem() != null) {
                Uzivatel vybrany = twVyucujici.getSelectionModel().getSelectedItem();
                vybrany.setTitulPred(titulPredField.getText());
                vybrany.setTitulZa(titulZaField.getText());
                vybrany.setJmeno(jmenoField.getText());
                vybrany.setPrijmeni(prijmeniField.getText());
                vybrany.setEmail(emailField.getText());
                vybrany.getPracoviste().setKatedra(comboPracovist.getSelectionModel().getSelectedItem());
                vybrany.setMobil(Integer.parseInt(mobilField.getText()));
                vybrany.setTelefon(Integer.parseInt(telField.getText()));
                dh.updateDataVyuc(vybrany);
                vycistiFormularVyucujici();
                aktualizujVyucujici();
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void odeberVyucujiciho(ActionEvent event) {
        Uzivatel vybranyVyuc = twVyucujici.getSelectionModel().getSelectedItem();
        if (vybranyVyuc != null) {
            try {
                dh.deleteDataVyuc(vybranyVyuc);
                vyucujici.remove(vybranyVyuc);
                vycistiFormularVyucujici();
            } catch (SQLException ex) {
                zobrazChybu(ex);
            }
        }
    }

    @FXML
    private void pridejPracoviste(ActionEvent event) {
        try {
            if (comboFakulta.getSelectionModel().getSelectedItem() != null && !nazKatField.getText().isEmpty()
                    && !zkrKatField.getText().isEmpty()) {
                String zkratkaKat = zkrKatField.getText();
                String nazevKat = nazKatField.getText();
                Fakulta fak = comboFakulta.getSelectionModel().getSelectedItem();

                Pracoviste2 novy = new Pracoviste2(fak.getZkratka(), fak.getNazev(), zkratkaKat, nazevKat);
                dh.insertDataPrac(novy);
                aktualizujPracoviste();
                vycistiFormularPracoviste();
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void upravPracoviste(ActionEvent event) {
        try {
            if (comboFakulta.getSelectionModel().getSelectedItem() != null && !nazKatField.getText().isEmpty()
                    && !zkrKatField.getText().isEmpty()) {
                Pracoviste2 vybrane = twPracoviste.getSelectionModel().getSelectedItem();
                vybrane.setKatedra(nazKatField.getText());
                Fakulta fak = comboFakulta.getSelectionModel().getSelectedItem();
                vybrane.setFakulta(fak.getNazev());
                vybrane.setZkratkaFak(fak.getZkratka());
                dh.updateDataPrac(vybrane);
                vycistiFormularPracoviste();
                aktualizujPracoviste();
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void odeberPracoviste(ActionEvent event) {
        Pracoviste2 prac = twPracoviste.getSelectionModel().getSelectedItem();
        if (prac != null) {
            try {
                dh.deleteDataPrac(prac);
                pracoviste.remove(prac);
                vycistiFormularPracoviste();
            } catch (SQLException ex) {
                zobrazChybu(ex);
            }
        }
    }

    @FXML
    private void pridejStudObor(ActionEvent event) {
        try {
            if (!nazevStObField.getText().isEmpty()
                    && !kodStObField.getText().isEmpty()) {
                String zkratkaOboru = kodStObField.getText();
                String nazevOboru = nazevStObField.getText();
                String info = infoStObArea.getText();

                Obor novy = new Obor(zkratkaOboru, nazevOboru, info);
                dh.insertDataObor(novy);
                aktualizujObory();
                vycistiFormStudObor(event);
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void upravStudObor(ActionEvent event) {
        try {
            if (!nazevStObField.getText().isEmpty()
                    && !kodStObField.getText().isEmpty()) {
                Obor vybrany = twStudObory.getSelectionModel().getSelectedItem();
                vybrany.setNazev(nazevStObField.getText());
                vybrany.setZkratka(kodStObField.getText());
                vybrany.setInfo(infoStObArea.getText());
                dh.updateDataObor(vybrany);
                vycistiFormularOboru();
                aktualizujObory();
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void odeberStudObor(ActionEvent event) {
        Obor obor = twStudObory.getSelectionModel().getSelectedItem();
        if (obor != null) {
            try {
                dh.deleteDataObor(obor);
                obory.remove(obor);
                vycistiFormularOboru();
            } catch (SQLException ex) {
                zobrazChybu(ex);
            }
        }
    }

    @FXML
    private void pridejPredmet(ActionEvent event) {
        try {
            if (comboZakonPred.getSelectionModel().getSelectedItem() != null && comboSemPred.getSelectionModel().getSelectedItem() != null
                    && comboFormPred.getSelectionModel().getSelectedItem() != null && !nazPredField.getText().isEmpty()
                    && !zkrPredField.getText().isEmpty() && !kredPredField.getText().isEmpty()) {
                Predmet novy = new Predmet(zkrPredField.getText(),
                        nazPredField.getText(),
                        Integer.parseInt(kredPredField.getText()),
                        comboSemPred.getValue(), comboZakonPred.getValue(), comboFormPred.getValue());
                dh.insertDataPredmet(novy);
                vycistiFormularPredmetu();
                aktualizujPredmety();
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void upravPredmet(ActionEvent event) {
        try {
            if (comboZakonPred.getSelectionModel().getSelectedItem() != null && comboSemPred.getSelectionModel().getSelectedItem() != null
                    && comboFormPred.getSelectionModel().getSelectedItem() != null && !nazPredField.getText().isEmpty()
                    && !zkrPredField.getText().isEmpty() && !kredPredField.getText().isEmpty()) {
                Predmet vybrany = twPredmety.getSelectionModel().getSelectedItem();
                vybrany.setZkratka(zkrPredField.getText());
                vybrany.setNazev(nazPredField.getText());
                vybrany.setKredity(Integer.parseInt(kredPredField.getText()));
                vybrany.setSemestr(comboSemPred.getValue());
                vybrany.setForma(comboFormPred.getValue());
                vybrany.setZakonceni(comboZakonPred.getValue());
                dh.updateDataPredmet(vybrany);
                vycistiFormularPredmetu();
                twPredmety.refresh();
                //aktualizujPredmety();
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void odeberPredmet(ActionEvent event) {
        Predmet pred = twPredmety.getSelectionModel().getSelectedItem();
        if (pred != null) {
            try {
                dh.deleteDataPredmet(pred);
                predmety.remove(pred);
                vycistiFormularPredmetu();
            } catch (SQLException ex) {
                zobrazChybu(ex);
            }
        }
    }

    @FXML
    private void pridejRA(ActionEvent event) {
        try {
            if (comboPredmet.getValue() != null && comboVyucujici.getValue() != null
                    && comboZpusAkce.getValue() != null
                    && !rozsahRAField.getText().isEmpty() && !kapacitaRAField.getText().isEmpty()) {
                if (isNumeric(kapacitaRAField.getText()) && isNumeric(rozsahRAField.getText())) {
                    Akce nova = new Akce(Integer.parseInt(rozsahRAField.getText()),
                            Integer.parseInt(kapacitaRAField.getText()),
                            comboVyucujici.getValue(),
                            comboPredmet.getValue(),
                            comboZpusAkce.getValue());
                    dh.insertDataAkce(nova);
                    vycistiFormularAkce();
                    aktualizujRA();
                }
            } else {
                zobrazChybu("Vyplňte všechny údaje!");
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void upravRA(ActionEvent event) {
        try {
            if (comboPredmet.getSelectionModel().getSelectedItem() != null && comboVyucujici.getSelectionModel().getSelectedItem() != null
                    && comboZpusAkce.getSelectionModel().getSelectedItem() != null
                    && !rozsahRAField.getText().isEmpty() && !kapacitaRAField.getText().isEmpty()) {
                if (isNumeric(kapacitaRAField.getText()) && isNumeric(rozsahRAField.getText())) {
                    Akce vybrana = twRA.getSelectionModel().getSelectedItem();
                    vybrana.setKapacita(Integer.parseInt(kapacitaRAField.getText()));
                    vybrana.setRozsah(Integer.parseInt(rozsahRAField.getText()));
                    vybrana.setPredmet(comboPredmet.getValue());
                    vybrana.setVyucujici(comboVyucujici.getValue());
                    vybrana.setZpusob(comboZpusAkce.getValue());
                    dh.updateDataAkce(vybrana);
                    vycistiFormularAkce();
                    twRA.refresh();
                }
            } else {
                zobrazChybu("Vyplňte všechny údaje!");
            }
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void odeberRA(ActionEvent event) {
        Akce delAkce = twRA.getSelectionModel().getSelectedItem();
        if (delAkce != null) {
            try {
                dh.deleteDataAkce(delAkce);
                akce.remove(delAkce);
                vycistiFormularAkce();
            } catch (SQLException ex) {
                zobrazChybu(ex);
            }
        }
    }

    // V podstatě listenery reagující na přepnutí karty
    @FXML
    private void prepniKartaVyucujici(Event event) {
        aktualizujVyucujici();
    }

    @FXML
    private void prepniKartaPredmety(Event event) {
        aktualizujPredmety();
    }

    @FXML
    private void prepniKartaRA(Event event) {
        aktualizujRA();
    }

    @FXML
    private void prepniKartaPracoviste(Event event) {
        try {
            fakulty = dh.dejFakulty();
            aktualizujPracoviste();
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void prepniKartaStudObory(Event event) {
        aktualizujObory();
    }

    // Metody pro naplnění comboboxů z databáze
    @FXML
    private void nactiComboFakulty(MouseEvent event) {
        try {
            comboFakulta.getItems().setAll(dh.dejFakulty());
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void nactiZpZakonceni(MouseEvent event) {
        try {
            comboZakonPred.getItems().setAll(dh.dejZpusobyZakon());
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void nactiSemestry(MouseEvent event) {
        try {
            comboSemPred.getItems().setAll(dh.dejSemestry());
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void nactiFormyVuky(MouseEvent event) {
        try {
            comboFormPred.getItems().setAll(dh.dejFormyVyuky());
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void nactiVyucujici(MouseEvent event) {
        try {
            ArrayList<Uzivatel> vyuc = dh.dejKartaVyucujici();
            vyuc.sort((Uzivatel o1, Uzivatel o2) -> o1.getPrijmeni().compareToIgnoreCase(o2.getPrijmeni()));
            comboVyucujici.getItems().setAll(vyuc);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void nactiTypAkce(MouseEvent event) {
        try {
            comboZpusAkce.getItems().setAll(dh.dejZpusobVyuky());
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    @FXML
    private void nactiPredmety(MouseEvent event) {
        try {
            ArrayList<Predmet> predm = dh.dejKartaPredmety();
            predm.sort((Predmet o1, Predmet o2) -> o1.getNazev().compareToIgnoreCase(o2.getNazev()));
            comboPredmet.getItems().setAll(predm);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    // Metody pro vyčištění polí formulářů
    @FXML
    private void vycistiPracForm(ActionEvent event) {
        vycistiFormularPracoviste();
    }

    @FXML
    private void vycistiFormularRA(ActionEvent event) {
        vycistiFormularAkce();
    }

    private void vycistiFormularPredmetu(ActionEvent event) {
        vycistiFormularPredmetu();
    }

    @FXML
    private void vycistiFormStudObor(ActionEvent event) {
        vycistiFormularOboru();
    }

    private void vycistiFormularVyucujici() {
        try {
            pridatVyucBtn.setDisable(false);
            comboPracovist.getItems().setAll(dh.dejKatedry());
            titulPredField.setText("");
            titulZaField.setText("");
            jmenoField.setText("");
            prijmeniField.setText("");
            mobilField.setText("");
            telField.setText("");
            emailField.setText("");
            comboPracovist.getSelectionModel().select(0);
            twVyucujici.getSelectionModel().select(null);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    private void vycistiFormularPracoviste() {
        try {
            pridatPracBtn.setDisable(false);
            zkrKatField.setDisable(false);
            comboFakulta.getItems().setAll(dh.dejFakulty());
            zkrKatField.setText("");
            nazKatField.setText("");
            comboPracovist.getSelectionModel().select(0);
            twPracoviste.getSelectionModel().select(null);
        } catch (SQLException ex) {
            zobrazChybu(ex);
        }
    }

    private void vycistiFormularOboru() {
        pridatStObBtn.setDisable(false);
        nazevStObField.setText("");
        kodStObField.setText("");
        infoStObArea.setText("");
        twStudObory.getSelectionModel().select(null);
    }

    @FXML
    private void vycistiFormularPredmetu() {
        pridatPredBtn.setDisable(false);
        nazPredField.setText("");
        zkrPredField.setText("");
        kredPredField.setText("");
        comboSemPred.getSelectionModel().select(null);
        comboZakonPred.getSelectionModel().select(null);
        comboFormPred.getSelectionModel().select(null);
        twPredmety.getSelectionModel().select(null);
    }

    @FXML
    private void vycistiVyucForm(ActionEvent event) {
        vycistiFormularVyucujici();
    }

    private void vycistiFormularAkce() {
        pridatRABtn.setDisable(false);
        rozsahRAField.setText("");
        kapacitaRAField.setText("");
        comboPredmet.getSelectionModel().select(null);
        comboVyucujici.getSelectionModel().select(null);
        comboZpusAkce.getSelectionModel().select(null);
    }

    // Otevření okna detailu oboru
    @FXML
    private void ukazDetailOboru(ActionEvent event) {
        //Stage stage = (Stage) detOboruBtn.getScene().getWindow();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailOboruFXML.fxml"));
        Obor obor = twStudObory.getSelectionModel().getSelectedItem();
        DetailOboruFXMLController contr = new DetailOboruFXMLController(dh, obor);
        loader.setController(contr);
        final Parent root;
        try {
            root = loader.load();
            final Scene scene = new Scene(root);

            Stage stage2 = new Stage();
            stage2.setTitle(obor.getNazev());
            stage2.setResizable(false);
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(detOboruBtn.getScene().getWindow());
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            zobrazChybu(ex);
        }
    }

    // Ostatní metody
    private boolean isNumeric(String text) throws NumberFormatException {
        try {
            int num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            zobrazChybu(new NumberFormatException("Vstupní údaj musí být číslo!\n" + e.getLocalizedMessage()));
            return false;
        }
        return true;
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
}
