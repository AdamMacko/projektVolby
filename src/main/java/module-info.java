module com.example.projektvolby {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projektvolby to javafx.fxml;
    exports com.example.projektvolby;
}