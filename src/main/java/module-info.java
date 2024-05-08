module com.example.chessveframu {
    requires javafx.controls;
    requires javafx.fxml;
    requires chesslib;


    opens com.example.Ui to javafx.fxml;
    exports com.example.Ui;
}