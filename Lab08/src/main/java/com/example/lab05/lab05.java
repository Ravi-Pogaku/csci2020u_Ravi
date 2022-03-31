package com.example.lab05;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/*
COPY-PASTED THE LAB05 DIRECTORY AND ADDED TO IT
 */

public class lab05 extends Application {
    // NEW METHOD
    public static void New(TableView table) {
        table.getItems().clear();
    }

    // LOAD METHOD (FOR OPEN BUTTON)
    public static void load(String fileName, TableView table) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while((line = br.readLine()) != null) {
                String[] temp = line.split(",");

                StudentRecord newStudent = new StudentRecord(temp[0], Float.parseFloat(temp[1]),
                        Float.parseFloat(temp[2]), Float.parseFloat(temp[3]));

                table.getItems().add(newStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SAVE METHOD
    public static void save(String fileName, ObservableList<StudentRecord> tableData) throws IOException {
        Writer writer = null;
        try {
            File file = new File(fileName);
            writer = new BufferedWriter(new FileWriter(file));
            for (StudentRecord student: tableData) {
                String text = String.format("%s,%f,%f,%f\n",student.getSID(), student.getMidterm(),
                        student.getAssignments(), student.getFinalExam());

                writer.write(text);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            writer.flush();
            writer.close();
        }
    }

    // EXIT METHOD
    public static void exit(Stage stage) {
        stage.close();
    }

    // DEFAULT TO DataSource.csv
    String currentFilename = "DataSource.csv";

    public void start(Stage stage){
        // Table of Student Records
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

        tbl.getColumns().setAll(
                colSID, colAssignments, colMidterm, colFinalExam, colFinalMark, colLetterGrade
        );

        tbl.setItems(DataSource.getAllMarks());

        // Add New Student Records
        GridPane gp = new GridPane();
        gp.setHgap(10.0);
        gp.setVgap(3.0);
        gp.setPadding(new Insets(3.0));

        Label lblSID = new Label("SID:");
        TextField textSID = new TextField();

        Label lblAssignments = new Label("Assignments:");
        TextField textAssignments = new TextField();

        Label lblMidterm = new Label("Midterm:");
        TextField textMidterm = new TextField();

        Label lblFinalExam = new Label("Final Exam:");
        TextField textFinalExam = new TextField();

        Button buttonAdd = new Button("Add");

        gp.add(lblSID, 0, 0);
        gp.add(textSID, 1,0);
        gp.add(lblAssignments, 2, 0);
        gp.add(textAssignments, 3, 0);
        gp.add(lblMidterm, 0, 1);
        gp.add(textMidterm, 1, 1);
        gp.add(lblFinalExam, 2, 1);
        gp.add(textFinalExam, 3, 1);
        gp.add(buttonAdd, 1, 2);

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StudentRecord newStudent = new StudentRecord(textSID.getText(), Float.parseFloat(textMidterm.getText()),
                        Float.parseFloat(textAssignments.getText()), Float.parseFloat(textFinalExam.getText()));

                tbl.getItems().add(newStudent);
            }
        });

        // Menu Bar
        MenuBar mb = new MenuBar();

        Menu menuFile =  new Menu("File");
        MenuItem menuNew = new MenuItem("New");
        MenuItem menuOpen = new MenuItem("Open");
        MenuItem menuSave = new MenuItem("Save");
        MenuItem menuSaveAs = new MenuItem("Save As");
        MenuItem menuExit = new MenuItem("Exit");

        menuFile.getItems().addAll(menuNew, menuOpen, menuSave, menuSaveAs, menuExit);
        mb.getMenus().add(menuFile);

        // WHEN NEW IS PRESSED
        menuNew.setOnAction(e -> {
            New(tbl);
        });

        // WHEN OPEN IS PRESSED
        menuOpen.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Choose a CSV File to load");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files","*.csv"));

            File selectedFile = fc.showOpenDialog(stage);
            if (null != selectedFile) {
                currentFilename = selectedFile.getName();
                New(tbl); // Remove previous data
                load(selectedFile.getName(), tbl); // Load new data
            }
        });

        // WHEN SAVE IS PRESSED
        menuSave.setOnAction(e -> {
            try {
                save(currentFilename, tbl.getItems());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // WHEN SAVE AS IS PRESSED
        menuSaveAs.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Save file as");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files","*.csv"));

            File savedFile = fc.showSaveDialog(stage);
            if (null != savedFile) {
                currentFilename = savedFile.getName();

                try {
                    save(savedFile.getName(), tbl.getItems());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // WHEN EXIT IS PRESSED
        menuExit.setOnAction(e -> {
            exit(stage);
        });

        // Window Layout/Output
        VBox vbox = new VBox();
        vbox.getChildren().addAll(mb, tbl, gp);
        stage.setTitle("Lab08 CSCI2020U Ravi Pogaku");
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
