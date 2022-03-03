package com.example.lab05;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class lab05 extends Application {
    public void start(Stage stage){
        TableView<StudentRecord> tbl = new TableView<>();

        //SID column
        TableColumn<StudentRecord, String> colSID = new TableColumn<>("SID");
        colSID.setCellValueFactory( new PropertyValueFactory<>("SID") );

        //Assignments column
        TableColumn<StudentRecord, Float> colAssignments = new TableColumn<>("Assignments");
        colAssignments.setCellValueFactory( new PropertyValueFactory<>("assignments") );

        //Midterm column
        TableColumn<StudentRecord, Float> colMidterm = new TableColumn<>("Midterm");
        colMidterm.setCellValueFactory( new PropertyValueFactory<>("midterm") );

        //Final Exam column
        TableColumn<StudentRecord, Float> colFinalExam = new TableColumn<>("Final Exam");
        colFinalExam.setCellValueFactory( new PropertyValueFactory<>("finalExam") );

        //Final Mark column
        TableColumn<StudentRecord, Float> colFinalMark = new TableColumn<>("Final Mark");
        colFinalMark.setCellValueFactory( new PropertyValueFactory<>("finalMark") );

        //Letter Grade column
        TableColumn<StudentRecord, Character> colLetterGrade = new TableColumn<>("Letter Grade");
        colLetterGrade.setCellValueFactory( new PropertyValueFactory<>("letterGrade") );

        tbl.getColumns().addAll(
                colSID, colAssignments, colMidterm, colFinalExam, colFinalMark, colLetterGrade
        );

        tbl.setItems(DataSource.getAllMarks());

        stage.setTitle("Lab05 CSCI2020U Ravi Pogaku");
        Scene scene = new Scene(tbl);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
