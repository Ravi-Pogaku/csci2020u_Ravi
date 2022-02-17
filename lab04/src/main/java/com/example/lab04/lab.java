package com.example.lab04;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class lab extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GridPane gp = new GridPane();
        gp.setPadding( new Insets(10) );
        gp.setHgap( 4 );
        gp.setVgap( 10 );

        Label lblUsername = new Label("Username:");
        TextField tfUsername = new TextField();

        Label lblPass = new Label("Password:");
        PasswordField pfPass = new PasswordField();

        Label lblName = new Label("Full Name:");
        TextField tfName = new TextField();

        Label lblEmail = new Label("E-Mail:");
        TextField tfEmail = new TextField();

        Label lblPhone = new Label("Phone #:");
        TextField tfPhone = new TextField();

        Label lblBirth = new Label("Date of Birth:");
        DatePicker dpBirth = new DatePicker();

        Button button = new Button("Register");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(tfUsername.getText());
                System.out.println(pfPass.getText());
                System.out.println(tfName.getText());
                System.out.println(tfEmail.getText());
                System.out.println(tfPhone.getText());
                System.out.println(dpBirth.getValue());
            }
        });

        gp.add(lblUsername, 0,0);
        gp.add(lblPass,     0,1);
        gp.add(lblName,     0,2);
        gp.add(lblEmail,    0,3);
        gp.add(lblPhone,    0,4);
        gp.add(lblBirth,    0,5);

        gp.add(tfUsername, 1, 0);
        gp.add(pfPass,     1, 1);
        gp.add(tfName,     1, 2);
        gp.add(tfEmail,    1, 3);
        gp.add(tfPhone,    1, 4);
        gp.add(dpBirth,    1, 5);
        gp.add(button, 1,6);

        stage.setTitle("CSCI2020U Lab 04 Solution");
        Scene scene = new Scene(gp);
        stage.setScene(scene);
        stage.setWidth( 736 );
        stage.setHeight( 414  );
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
