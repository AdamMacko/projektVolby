package com.example.projektvolby;
import com.example.projektvolby.storage.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.stage.FileChooser.ExtensionFilter;

public class VoliciScenaController {
    @FXML
    private TextField PSCTextField;
    @FXML
    private TextField bydliskoTextField;

    @FXML
    private TextField cisloOPTextField;

    @FXML
    private Button extrahovatButton;

    @FXML
    private TextField menoTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField priezviskoTextFiled;

    @FXML
    private Button spatButton;

    @FXML
    private Button ulozitButton;

    @FXML
    private ListView<Volic> volicListView;

    @FXML
    private Button vymazVsetkoButton;

    @FXML
    private Button vymazatbutton;
    private Volic novyVolic;




    private VolicFxModel volicFxModel;

    public VoliciScenaController(){
        volicFxModel=new VolicFxModel();
    }
    public VoliciScenaController(Volic volic){
        volicFxModel=new VolicFxModel(volic);
    }
    @FXML
    public void initialize() {
        menoTextField.textProperty().bindBidirectional(volicFxModel.menoProperty());
        priezviskoTextFiled.textProperty().bindBidirectional(volicFxModel.priezviskoProperty());
        cisloOPTextField.textProperty().bindBidirectional(volicFxModel.cOPProperty());
        volicListView.setItems(volicFxModel.volic());
        VolicDao volicDao = DaoFactory.INSTANCE.getVolicDao();
        List<Volic> existingVoters = volicDao.getAll(); // Získanie všetkých voličov z databázy
        volicFxModel.volic().addAll(existingVoters); // Pridanie existujúcich voličov do ObservableList
        volicListView.setCellFactory(param -> new ListCell<Volic>() {
            @Override
            protected void updateItem(Volic item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMeno() + " " + item.getPriezvisko());
                    setOnMouseEntered(event -> {
                        setScaleX(1.1); // Zväčšenie šírky
                        setScaleY(1.1); // Zväčšenie výšky
                    });
                    setOnMouseExited(event -> {
                        setScaleX(1.0); // Vrátenie šírky na pôvodnú hodnotu
                        setScaleY(1.0); // Vrátenie výšky na pôvodnú hodnotu
                    });
                }
            }
        });
    }
    @FXML
    //nacitanie zo suboru
    void extrahovat(ActionEvent event) {
        if(! volicFxModel.volic().isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Nahradenie volicov z exportu");
            alert.setHeaderText("Nahradenie volicov z exportu");
            alert.setContentText("Volici, ktorí sú doteraz na zozname, "
                    + "budú nahradení volicmi z CSV súboru.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() != ButtonType.OK){
                return;
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(volicListView.getScene().getWindow());
        if (selectedFile != null) {
            System.out.println("Vybratý súbor: " + selectedFile);
            try {
                List<Volic> students = nacitajzCSV(selectedFile);
                volicFxModel.volic().setAll(students);
            } catch (FileNotFoundException e) {
                // nenastane
                e.printStackTrace();
            }
        }
    }


    private List<Volic> nacitajzCSV(File file) throws FileNotFoundException  {
        Scanner scanner = new Scanner(file, "utf-8");
        List<Volic> volici = new ArrayList<>();
        String[]udaje = new String[6];



        while(scanner.hasNextLine()) {
            scanner.nextLine();
            String vcelku = scanner.nextLine();
            udaje = vcelku.split(";");
            Volic volic = new Volic(udaje[0],udaje[1],udaje[2]);
            volici.add(volic);
        }
        return volici;
    }

    @FXML
        //tlacidlo pridat
    void pridat(ActionEvent event) {
     String meno = menoTextField.getText().trim();
     String priezvisko = priezviskoTextFiled.getText().trim();
     String cisloOP = cisloOPTextField.getText().trim();
     String trvaleBydlisko = bydliskoTextField.getText().trim();
     String psc = PSCTextField.getText().trim();


         Volic volic = new Volic(meno, priezvisko, cisloOP);
         volicFxModel.volic().add(volic);
         menoTextField.clear();
         priezviskoTextFiled.clear();
         cisloOPTextField.clear();
         bydliskoTextField.clear();
         PSCTextField.clear();



    }

    @FXML
    //tlacidlo ulozit
    void ulozit(ActionEvent event) {
        Volic volic= volicFxModel.getVolic();
        VolicDao volicDao = DaoFactory.INSTANCE.getVolicDao();
        novyVolic=volicDao.save(volic);
        ulozitButton.getScene().getWindow().hide();
    }

    @FXML
    //Listview
    void volicList(ActionEvent event) {

    }

    @FXML
    //tlacidlo na vymazanie volica
    void vymazat(ActionEvent event) {
        Volic volic = volicListView.getSelectionModel().getSelectedItem();
        if (volic != null) {
            VolicDao volicDao = DaoFactory.INSTANCE.getVolicDao();
            try {
                volicDao.delete(volic.getId()); // Vymazanie z databázy podľa ID
                volicFxModel.volic().remove(volic); // Odstránenie z používateľského rozhrania
            } catch (EntityNotFoundException e) {

                e.printStackTrace();
            }
        }
    }


    @FXML
    void spatButton(ActionEvent event) {
        openAdminlayout();
        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void vymazeVsetkychVolicov(ActionEvent event){
        VolicDao volicDao = DaoFactory.INSTANCE.getVolicDao();
        volicDao.deleteAll(); // Volanie metódy na vymazanie celej tabuľky

        // Následne môžeme aktualizovať používateľské rozhranie, vyčistením ListView
        volicFxModel.volic().clear();

    }


    private void openAdminlayout() {
        try {
            AdminLayoutSceneController controller = new AdminLayoutSceneController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("adminlayout.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ADMIN");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}