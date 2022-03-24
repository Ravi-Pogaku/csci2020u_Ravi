package lab07.lab07;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class main extends Application {
    public static List<String> read(String csv){
        List<String> rows = new ArrayList<>();
        try {
            FileReader fr = new FileReader(csv);
            BufferedReader input = new BufferedReader(fr);

            String line = "";

            while ((line = input.readLine()) != null) {
                String[] temp = line.split(",");
                rows.add(temp[5]); // Only relevant column (Names of natural disasters)
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String file = "weatherwarnings-2015.csv";
        List<String> data = read(file);

        Map<String, Integer> count = new HashMap<>();

        // Counting num of disasters with map
        for (String element: data) {
            if (count.containsKey(element)) { // if already exists, then just iterate +1
                count.put(element, count.get(element) + 1);
            }

            else { // else add element and counter to 1
                count.put(element, 1);
            }
        }

        // Pie Chart Data
        List<String> keys = new ArrayList<>(count.keySet());
        List<Integer> counts = new ArrayList<>(count.values());

        // Pie Chart
        PieChart pc = new PieChart();
        for (int i = 0; i < keys.size(); i++) {
            pc.getData().add(new PieChart.Data(keys.get(i), counts.get(i)));
        }
        pc.setTitle("Natural Disasters");

        //Display
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().add(pc);

        Scene scene = new Scene(hbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
