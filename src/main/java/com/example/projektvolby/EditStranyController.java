package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.KandidatDao;
import com.example.projektvolby.storage.StranaDao;
import com.mysql.cj.conf.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EditStranyController {
    @FXML
    private Button spatButton;

    @FXML
    private ComboBox<Strana> stranaComboBox;

    @FXML
    private Button aktualizovatButton;

    @FXML
    private Button extrakciaButton;

    @FXML
    private ListView<Kandidat> kandidatiListView;

    @FXML
    private TextField menoTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField vekTextField;

    @FXML
    private TextArea volebnyPlanTextArea;

    @FXML
    private Button vymazStranuButton;

    @FXML
    private Button vymazatButton;
    private StranaDao stranaDao= DaoFactory.INSTANCE.getStranyDao();
    private KandidatDao kandidatDao= DaoFactory.INSTANCE.getKandidatiDao();
    private ObservableList<Strana> stranyModel;
    private StranyFxModel stranyFxModel= new StranyFxModel();
    private ObservableList<Kandidat> kandidatiModel;
    private Strana aktualizovanaStrana;


    public void initialize() {
        List<Strana> strany=stranaDao.getAll();
        stranyModel= FXCollections.observableArrayList(strany);
        kandidatiListView.setItems(stranyFxModel.kandidatiModel());
        stranaComboBox.setItems(stranyModel);
        stranaComboBox.getSelectionModel().selectFirst();
        stranaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                update();
            }
        });

        update();
    }


    private void update() {
        Strana vybranaStrana=stranaComboBox.getSelectionModel().getSelectedItem();
        List<Kandidat> kandidati=kandidatDao.getAllByStranaId(vybranaStrana.getId());
        kandidatiModel=FXCollections.observableArrayList(kandidati);
        kandidatiListView.setItems(kandidatiModel);
        String volebnyPlan=vybranaStrana.getVolebny_plan();
        volebnyPlanTextArea.textProperty().setValue(volebnyPlan);
    }



    @FXML
    void aktualizujeStranu(ActionEvent event) {

            Strana vybranaStrana = stranaComboBox.getSelectionModel().getSelectedItem();
            stranyFxModel.setNazov(vybranaStrana.getNazov());
            stranyFxModel.setVolebnyPlan(volebnyPlanTextArea.getText());
            stranyFxModel.kandidatiModel().addAll(kandidatiModel);
            Strana updatedStrana = stranyFxModel.getStrana();
            updatedStrana.setId(vybranaStrana.getId());
            aktualizovanaStrana = stranaDao.save(updatedStrana);
            List<Kandidat> kandidati=kandidatiModel;
            for (Kandidat kandidat : kandidati){
            kandidatDao.save(kandidat, vybranaStrana.getId());
            }


           // System.out.println(aktualizovanaStrana);
        }



    @FXML
    void extrahujeKandidatov(ActionEvent event) {
        if(! stranyFxModel.kandidatiModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Nahradenie kandidatov z exportu");
            alert.setHeaderText("Nahradenie kandidatov z exportu");
            alert.setContentText("Kandidáti, ktori sú doteraz na zozname, "
                    + "budú nahradeni kandidatmi  z CSV súboru.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() != ButtonType.OK){
                return;
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(kandidatiListView.getScene().getWindow());

        if (selectedFile != null) {
            System.out.println("Vybratý súbor: " + selectedFile);
            try {
                if (nacitajzCSV(selectedFile) != null) {
                    List<Kandidat> kandidati = nacitajzCSV(selectedFile);
                    stranyFxModel.kandidatiModel().setAll(kandidati);
                    kandidatiListView.setItems(stranyFxModel.kandidatiModel());
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Upozornenie");
                    alert.setHeaderText("Chyba");
                    alert.setContentText("Skontrolujte udaje v subore");

                    alert.showAndWait();
                }
            } catch (FileNotFoundException e) {
                // nenastane
                e.printStackTrace();
            }
        }
    }

    private List<Kandidat> nacitajzCSV(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file, "utf-8");
        List<Kandidat> kandidati = new ArrayList<>();
        String[] udaje = new String[3];
        boolean nenaslaSaChyba = true;
        int chybaNaRiadku = 0;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            chybaNaRiadku++;
            String vcelku = scanner.nextLine();
            udaje = vcelku.split(";");

            if (udaje.length == 3) {

                Kandidat kandidat = new Kandidat(udaje[0], udaje[1], Integer.parseInt(udaje[2]));
                kandidati.add(kandidat);
            }else {
                nenaslaSaChyba = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Upozornenie");
                alert.setHeaderText("Chyba na riadku: " + chybaNaRiadku);
                alert.showAndWait();
                break;
            }
        }

        if (!nenaslaSaChyba) {
            scanner.close();
            return null;

        } else {
            scanner.close();
            return kandidati;
        }
    }

    @FXML
    void pridaKandidata(ActionEvent event) {
        String celeMeno= menoTextField.getText().trim();
        int age= Integer.parseInt(vekTextField.getText().trim());
        int index = celeMeno.lastIndexOf(' ');
        String krstne="";
        String priezvisko="";
        if (index == -1) {
            priezvisko = celeMeno;
        }else {
            krstne = celeMeno.substring(0, index).trim();
            priezvisko = celeMeno.substring(index + 1).trim();
        }
        Kandidat kandidat = new Kandidat(krstne,priezvisko,age);
        kandidatiModel.add(kandidat);
        menoTextField.clear();
        vekTextField.clear();

    }

    @FXML
    void vymazeKandidata(ActionEvent event) {
        if(UliceSceneController.ConfirmationDialog.show("naozaj chcete vymazat tohto kandidata?Tato operacia sa neda vratit")){
            Kandidat kandidat=kandidatiListView.getSelectionModel().getSelectedItem();
            if (kandidat != null){
                kandidatiModel.remove(kandidat);
            }
        }


    }

    @FXML
    void vymazeStranu(ActionEvent event) {
        if(UliceSceneController.ConfirmationDialog.show("naozaj chcete vymazat tuto stranu?Tato operacia sa neda vratit")){
            Strana vybranaStrana=stranaComboBox.getSelectionModel().getSelectedItem();
            stranaDao.delete(vybranaStrana.getId());
            List<Strana> strany=stranaDao.getAll();
            stranyModel= FXCollections.observableArrayList(strany);
            stranaComboBox.setItems(stranyModel);
            stranaComboBox.getSelectionModel().selectFirst();
        }


    }

    @FXML
    void spatButton(ActionEvent event) {
        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
    }
    public class ConfirmationDialog {

        public static boolean show(String message) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText(message);


            ButtonType buttonTypeYes = new ButtonType("Ano");
            ButtonType buttonTypeNo = new ButtonType("Nie");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);


            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.setAlwaysOnTop(true);


            boolean result = alert.showAndWait().orElse(buttonTypeNo) == buttonTypeYes;

            return result;
        }
    }

}