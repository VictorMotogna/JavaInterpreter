package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by user on 1/16/2017.
 */
public class ViewController {
    //region Fields
    public static Stage primaryStage;
    //endregion

    //region Constructor
    public ViewController(Stage stage) {
        primaryStage = stage;
        initGUI(stage);
    }
    //endregion

    //region Methods
    private void initGUI(Stage stage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewController.class.getResource("program_panel.fxml"));
        try {
            HBox progamHorizontalBox = loader.load();
            Scene programScene = new Scene(progamHorizontalBox);
            stage.setScene(programScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
}
