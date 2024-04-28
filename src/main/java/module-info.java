module com.example.chessveframu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.Ui to javafx.fxml;
    exports com.example.Ui;
}