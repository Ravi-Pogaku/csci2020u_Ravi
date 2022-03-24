module lab07.lab07 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab07.lab07 to javafx.fxml;
    exports lab07.lab07;
}