package _Game;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Game Of Life program created for HIOA final project
 * The Controller class is the fx for fxml, all the properties in fxml are assign here.
 * The class is also implementing Initializable interface.
 *
 * @version 1.0
 * @since   2017-01-14
 */


public class Controller implements Initializable {
    public Canvas CanvasId;
    @FXML
    private CFrame cFrame;
    public ColorPicker colorPicker;
    public ChoiceBox patternChoiceBox, musicChoiceBox;
    private GUI gui;
    private Timeline timeline;


    public Slider sliderFPS, cellSlider;
    public StaticBoard sBoard;
    public DBoard dBoard;
    public Button musicStartButton, drawPattern;
    public ToolBar Toolbar;
    String line;
    @FXML
    TextField textBox;
    private Timeline tl;


    int user_id = 2;
    ReadGameBoard readGameBoard;
    public Text tekst;

    /**
     * Constructs and initializes the canvas and gui.
     *
     * @param location  unused.
     * @param resources unused.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cFrame = new CFrame((int) CanvasId.getHeight(), (int) CanvasId.getWidth(), CanvasId.getGraphicsContext2D());
        this.gui = new GUI(cFrame);
        key();
        tekst.setText("");
        patternChoiceBox();


    }


    public void key() {
        CanvasId.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            public void handle(javafx.scene.input.KeyEvent event) {
                cFrame.key(event);

            }
        });
    }




    public void clickedToolbar(){
    }
    public void clickedStartButton() {
       int TIME = 1000/ cFrame.getFPS();


        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {

            cFrame.getDynamicBoard().nextGeneration();
            cFrame.clearCanvas();

//            try{Thread.sleep(100);} catch (Exception a){}
            cFrame.pressedCanvas();
            timeline.playFromStart();


        }));
    timeline.play();

    }

    public void clickedClearButton() {
        timeline.stop();
        gui.ClearButton();
    }

    public void clickedRandomButton() {
        gui.RandomButton();
    }

    public void patternChoiceBox(){
        patternChoiceBox.getItems().add("Random");
        patternChoiceBox.setValue("Random");
        drawPattern.setOnAction(event -> getPatternChoice(patternChoiceBox));
    }

    public void getPatternChoice(ChoiceBox<String>patternChoiceBox) {
        switch (patternChoiceBox.getValue()) {
            case "Random":
                System.out.println(patternChoiceBox.getValue());
                cFrame.RandomButtonAction();
                break;
        }
    }

    public void clickedDrawButton(){

    }

    public void clickedExitButton(){
        Platform.exit();
    }

    public void colorPickerClicked() {
        cFrame.colorPicker(colorPicker);
    }

    public void clickedStopButton() {
        timeline.stop();
    }

    public void FPSClicked() {
        int a = (int) sliderFPS.getValue();
        cFrame.setFPS(a);
        //cFrame.SetTimeline();
        cFrame.drawCanvas();
    }

    public void CellSizeClicked() {
        double a = cellSlider.getValue();
        cFrame.cellSize(a);
        cFrame.drawCanvas();
    }

    public void CanvasReleased() {
    }

    public void CanvasPressed(MouseEvent a) throws Exception {
        cFrame.CanvasPressed(a);
    }


    /**
     * This method is reading the RLE file
     *
     * @return Nothing.
     * @throws IOException On input error.
     * @see IOException
     */
    public void openFile() throws IOException {


        readGameBoard = new ReadGameBoard(cFrame.getHEIGHT(), cFrame.getWIDTH());

        readGameBoard.readFile(line);

        cFrame.drawPattern(readGameBoard.pattern);

        tekst.setText(" Created on: " + readGameBoard.getCreationDetails(readGameBoard.file) + " File name: " + readGameBoard.file.getName() +
                "  Created by: " + readGameBoard.file.getParent() +
                "  Pattern name: " + readGameBoard.getPatterName());

            cellSlider.setValue(readGameBoard.getCellSize());
    }

    /**
     * This method is closing the window.
     */
    public void closeWindow() {
        Platform.exit();
    }



    public void patternLoad() throws IOException {

        URL url = new URL(textBox.getText());
        InputStream in = url.openStream();
        Scanner scan = new Scanner(in);

        while (scan.hasNext())
        {
            String str = scan.nextLine();
            System.out.println(str);
        }
        scan.close();
        


    }
    

}

