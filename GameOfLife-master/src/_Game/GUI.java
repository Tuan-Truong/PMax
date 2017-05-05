package _Game;

import javafx.animation.Timeline;


public class GUI{

    private Timeline timeline;
    private CFrame cFrame;


    /**
     * Initializes cFrame
     *
     *  @param cFrame creating CFrame object
     */


    public GUI(CFrame cFrame){

        this.cFrame = cFrame;
        this.timeline = cFrame.SetTimeline();
    }

    /**
     * This method stop the calling the RandomButtonAction in cFrame.
     */

    public void RandomButton() {
        cFrame.RandomButtonAction();
    }

    /**
     * This method stop the timeline, cleaning canvas and clearing the array.
     */

    public void ClearButton() {
        cFrame.clearCanvas();
        cFrame.clearArray();
        timeline.stop();
    }

    /**
     * This method playing the timeline from start.
     */


    public void StartButton() {
        cFrame.dBoard.nextGeneration();
    }

    /**
     * This method stop the timeline.
     */

    public void StopButton() {
        timeline.stop();
        cFrame.drawCanvas();
    }

}



