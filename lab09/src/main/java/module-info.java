module lab.lab09 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab.lab09 to javafx.fxml;
    exports lab.lab09;
}