module com.example.projektvolby {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;


    opens com.example.projektvolby to javafx.fxml;
    exports com.example.projektvolby;
}