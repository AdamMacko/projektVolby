package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.UliceDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UliceSceneController {

    @FXML
    private Button ExtrakciaButton;

    @FXML
    private TextField nazovTextField;

    @FXML
    private TextField popisneCisloTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField pscTextField;

    @FXML
    private ListView<Ulica> uliceListView;

    @FXML
    private Button ulozitButton;

    @FXML
    private Button vymazatButton;

    @FXML
    private Button vymazvsetkoButton;

    private UliceDao uliceDao= DaoFactory.INSTANCE.getUliceDao();

    private ObservableList<Ulica> ulicaModel;
    public void initialize() {
        List<Ulica> ulice=uliceDao.getAll();
        ulicaModel= FXCollections.observableArrayList(ulice);
        uliceListView.setItems(ulicaModel);

    }

    @FXML
    void extrahujeUlice(ActionEvent event) {
        if(! ulicaModel.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Nahradenie ulic z exportu");
            alert.setHeaderText("Nahradenie ulic z exportu");
            alert.setContentText("Ulice, ktore sú doteraz na zozname, "
                    + "budú nahradene ulicami z CSV súboru.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() != ButtonType.OK){
                return;
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(uliceListView.getScene().getWindow());


        if (selectedFile != null) {
            System.out.println("Vybratý súbor: " + selectedFile);
            try {
                if (nacitajzCSV(selectedFile) != null) {
                    // System.out.println("vypisujem");
                    List<Ulica> ulice = nacitajzCSV(selectedFile);
                    ulicaModel.setAll(ulice);
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

    private List<Ulica> nacitajzCSV(File file)throws FileNotFoundException {
        Scanner scanner = new Scanner(file, "utf-8");
        List<Ulica> ulice = new ArrayList<>();
        String[]udaje = new String[3];
        boolean nenaslaSaChyba = true;
        int chybaNaRiadku = 0;

        scanner.nextLine();

        while (scanner.hasNextLine()) {
            chybaNaRiadku++;
            String vcelku = scanner.nextLine();
            udaje = vcelku.split(";");
            // System.out.println(udaje.length);
            if (udaje.length == 3) {
                    Ulica ulica = new Ulica(udaje[0], Integer.parseInt(udaje[1]), udaje[2]);
                    ulice.add(ulica);

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
            return ulice;
        }

    }






    @FXML
    void pridaUlicu(ActionEvent event) {
        String nazov=nazovTextField.getText().trim();
        int popisne=Integer.parseInt(popisneCisloTextField.getText().trim());
        String PSC=pscTextField.getText().trim();
        Ulica ulica = new Ulica( 0L,nazov,popisne,PSC);
        ulicaModel.add(ulica);
        nazovTextField.clear();
        popisneCisloTextField.clear();
        pscTextField.clear();


    }

    @FXML
    void uloziUlicu(ActionEvent event) {
        for(Ulica ulica:ulicaModel){
            uliceDao.save(ulica);
            System.out.println(ulicaModel);
        }

    }

    @FXML
    void vymazeObec(ActionEvent event) {
        Ulica ulica=uliceListView.getSelectionModel().getSelectedItem();
        if(ulica.getId() !=0){
            uliceDao.delete(ulica.getId());
        }
        ulicaModel.remove(ulica);
        System.out.println(ulicaModel);

    }

    @FXML
    void vymazeVsetkyUlice(ActionEvent event) {
        if(ConfirmationDialog.show("naozaj chcete vymazat vsetky ulice?Tato operacia sa neda vratit")){
            uliceDao.deleteall();
            ulicaModel.clear();

        }


    }
    public class ConfirmationDialog {

        public static boolean show(String message) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText(message);

            // Customize the buttons
            ButtonType buttonTypeYes = new ButtonType("Ano");
            ButtonType buttonTypeNo = new ButtonType("Nie");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Show the dialog and wait for a button to be clicked
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.setAlwaysOnTop(true); // Set to true if you want the dialog to be always on top

            // Wait for the user's choice
            boolean result = alert.showAndWait().orElse(buttonTypeNo) == buttonTypeYes;

            return result;
        }
    }

}




