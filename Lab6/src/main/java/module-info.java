module lab6.javafx.lab6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens lab6.javafx.lab6 to javafx.fxml;
    exports lab6.javafx.lab6;
}