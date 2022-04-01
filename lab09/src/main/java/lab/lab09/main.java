package lab.lab09;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class main extends Application {
    public static List<Float> downloadStockPrices(String ticker) throws IOException {
        URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/" + ticker +
                "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");

        URLConnection conn = url.openConnection();
        conn.setDoOutput(false);
        conn.setDoInput(true);
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        br.readLine();

        List<Float> data = new ArrayList<>();
        String line = "";
        while((line = br.readLine()) != null) {
            String[] row = line.split(",");
            data.add(Float.parseFloat(row[4])); // Closing price values
        }
        br.close();
        return data;
    }

    public static Canvas drawLinePlot(List<Float> data1, List<Float> data2) {
        // For canvas width
        int size = data1.size();
        if (data2.size() > size) {
            size = data2.size();
        }

        // For canvas height
        float max = Collections.max(data1);
        if (Collections.max(data2) > max) {
            max = Collections.max(data2);
        }

        Canvas canvas = new Canvas(size*7 + 100,max/2.0 + 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Plot data
        gc.setStroke(Color.RED);
        plotLine(data1, gc);
        gc.setStroke(Color.BLUE);
        plotLine(data2, gc);

        canvas.setScaleY(-1); // Flip canvas to orient the data properly

        // Draw Axes
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50, 50, 50, canvas.getHeight() - 50);
        gc.strokeLine(50,50,canvas.getWidth() - 50,50);

        return canvas;
    }

    public static void plotLine(List<Float> data, GraphicsContext gc) {
        for (int i = 0; i < data.size() - 1; i++) {
            // points every 7 pixels, data divided by 2 to reduce window size
            gc.strokeLine(i*7 + 50, data.get(i)/2.0 + 50, (i+1)*7 + 50, data.get(i+1)/2.0 + 50);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        List<Float> data1 = downloadStockPrices("GOOG");
        List<Float> data2 = downloadStockPrices("AAPL");

        Group root = new Group();
        Canvas canvas = drawLinePlot(data1, data2);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, canvas.getWidth(),canvas.getHeight());
        stage.setTitle("Lab09 Ravichandra Pogaku, 100784105");
        stage.setScene(scene);
        stage.show();
    }

    public static void Main(String[] args) throws IOException {
        launch();
    }
}
