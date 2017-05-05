package _Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    double xOffset = 0;
    double yOffset = 0;
    Controller controller;


    /**
     * This is the main method which makes us start the program.
     * @param args lunching.
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is the start method which makes use creates the stage itself.
     * @param primaryStage parameter is used to create the stage.
     * @return Nothing.
     * @exception Exception On input error.
     * @see Exception
     *
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("GameOfLife");

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 1280, 780));

        controller = new Controller();


        primaryStage.show();
    }









         /*
        The two following lambda expressions makes it possible to move the application without the standard StageStyle
         */
        //Lambda mouse event handler



    }

