package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.StranaDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


public class PridanieStranySceneController {

    @FXML
    private ListView<Kandidat> kandidatiListView;

    @FXML
    private TextField menoTextField;
    @FXML
    private Button spatButton;

    @FXML
    private TextField nazovTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private Button ulozitButton;

    @FXML
    private TextField vekTextField;

    @FXML
    private TextArea volebnyPlanTextArea;

    @FXML
    private Button vymazatButton;

    @FXML
    private Button extrahovatButton;

    private StranyFxModel stranyFxModel;


    private Strana novaStrana;

    public PridanieStranySceneController(){
        stranyFxModel=new StranyFxModel();
    }
    public  PridanieStranySceneController(Strana strana){
        stranyFxModel=new StranyFxModel(strana);
    }

    @FXML
    public void initialize() {//nazov je bindnuty na fxmodel nastavenie listu a listener ak nie su values vo veku aj mene naraz neda sa ulozit
        nazovTextField.textProperty().bindBidirectional(stranyFxModel.nazovProperty());
        volebnyPlanTextArea.textProperty().bindBidirectional(stranyFxModel.volebnyPlanProperty());
        kandidatiListView.setItems(stranyFxModel.kandidatiModel());
        pridatButton.setDisable(true);
        ChangeListener<String> listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                boolean isNazovEmpty = menoTextField.getText().isEmpty();
                boolean isVekEmpty = vekTextField.getText().isEmpty();
                pridatButton.setDisable(isNazovEmpty || isVekEmpty);
            }
        };

        menoTextField.textProperty().addListener(listener);
        vekTextField.textProperty().addListener(listener);


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
    void novyKandidat(ActionEvent event) {
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
        stranyFxModel.kandidatiModel().add(kandidat);
        menoTextField.clear();
        vekTextField.clear();
    }

    @FXML
    void ulozStranu(ActionEvent event) {
        Strana strana= stranyFxModel.getStrana();
        StranaDao stranyDao = DaoFactory.INSTANCE.getStranyDao();

        novaStrana=stranyDao.save(strana);
        ulozitButton.getScene().getWindow().hide();
    }

    @FXML
    void vymazKanidata(ActionEvent event) {
            Kandidat kandidat= kandidatiListView.getSelectionModel().getSelectedItem();
            System.out.print(kandidat);
            if(kandidat !=null){
                stranyFxModel.kandidatiModel().remove(kandidat);

            }


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

    @FXML
    void spatButton(ActionEvent event) {
        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
    }

}
