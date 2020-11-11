package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private static double x = 0;

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), 500);
        stage.setScene(scene);

        Canvas canvas = new Canvas(5000, 500);
        root.add(canvas, 0, 0);
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        g.setLineWidth(5);


        stage.show();


        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                iterate(g, canvas);
            }

        };


        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                System.out.println("A key was pressed");
                timer.schedule(task, 1, 30);
            }
        });

    }

    private static void iterate(GraphicsContext g, Canvas canvas) {
        double y = Math.sin(x);
        System.out.println("(" + x + ";" + y + ")");
        g.strokeLine(100 * x, canvas.getHeight() / 2, 100 * x, canvas.getHeight() / 2 - y * 100);
        x += 0.1;
        System.out.println("(" + x + ";" + y + ")");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
