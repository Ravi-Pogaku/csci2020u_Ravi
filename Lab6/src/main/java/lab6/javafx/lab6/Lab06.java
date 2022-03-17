package lab6.javafx.lab6;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Lab06 extends Application {

    // BAR CHART DATA
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    // PIE CHART DATA
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Bar Chart
        primaryStage.setTitle("Bar and Pie Charts");
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart(xAxis, yAxis);
        bc.setTitle("Housing Prices and Commercial Prices By Year");
        xAxis.setLabel("Year");
        yAxis.setLabel("Price");

        XYChart.Series housing = new XYChart.Series();
        housing.setName("Housing Price");
        XYChart.Series commercial = new XYChart.Series();
        commercial.setName("Commercial Price");

        String[] years = {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007"};

        for (int i = 0; i < avgCommercialPricesByYear.length; i++) {
            housing.getData().add(new XYChart.Data(years[i], avgHousingPricesByYear[i]));
            commercial.getData().add(new XYChart.Data(years[i], avgCommercialPricesByYear[i]));
        }

        bc.getData().addAll(housing, commercial);

        // Pie Chart
        PieChart pc = new PieChart();
        for (int i = 0; i < ageGroups.length; i++) {
            pc.getData().add(new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]));
        }
        pc.setTitle("Each Age Groups' Purchases");

        //Display
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().add(bc);
        hbox.getChildren().add(pc);

        Scene scene = new Scene(hbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
