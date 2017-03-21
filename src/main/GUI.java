package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewController;

/**
 * Created by user on 1/16/2017.
 */
public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewController viewController = new ViewController(primaryStage);
    }
}
