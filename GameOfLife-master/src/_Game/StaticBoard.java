/**
 * The Game Of Life program created for HIOA final project
 * The Board class creates arrays for the board.
 * it does also drawing each cell and line.
 *
 * @version 1.0
 * @since   2017-01-14
 */

package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;


public class StaticBoard {

    DrawCanvas drawCanvas;


    byte[][] sBoard;
    int canvasWidth;
    int canvasHeight;

    /**
     *
     *  Constructs and initializes a board with array, width and height
     *
     *  @param boardArray creating a array for Board
     *  @param canWidth canvas width
     *  @param canHeight canvas height
     *  @return nothing.
     */


    public StaticBoard(byte[][] boardArray, int canWidth, int canHeight) {

        this.sBoard = boardArray;
        this.canvasHeight = canHeight;
        this.canvasWidth = canWidth;

    }


    /**
     * This method is used to make a new board with rules.
     *
     * @return Nothing.
     */


    public void nextGeneration() {

        byte[][] nextBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += sBoard[x + i][y + j];
                    }
                }

                cellNeighbors -= sBoard[x][y];
                if ((sBoard[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt
                else if ((sBoard[x][y] == 1) && (cellNeighbors > 3))
                    nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((sBoard[x][y] == 0) && (cellNeighbors == 3))
                    nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = sBoard[x][y];
            }
        }

        sBoard = nextBoard;
    }




    /**
     * This method is used to zero the numbers in board array.
     *
     * @return Nothing.
     *
     */

    public void cleanArray() {
        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                sBoard[i][j] = 0;
            }
        }
    }

    /**
     * This method is used to draw the reaadgameboard pattern.
     *
     * @return Nothing.
     * @param pattern is the first parameter and it is the array for the new pattern.
     * @param gc is the second parameter and is used to drawing on the canvas.
     *
     */

    public  void drawPattern(int[][] pattern, GraphicsContext gc)
    {
        for (int row=0; row < pattern.length; row++)
        {
            for (int col=0; col < pattern[row].length; col++)
            {
                if(pattern[row][col] ==1)
                {
                    sBoard[row][col] = 1;

                }
            }
        }


    }





    /**
     * This method is used to set a random board.
     *
     * @param i this is the first parameter for setBoardRandom method
     * @param j this is the second parameter for setBoardRandom method
     * @return int this returns a random array multiplied with 2.
     */

    public int setBoardRandom(int i, int j) {

        return  sBoard[i][j] = (byte) (Math.random() * 2);

    }





    /**
     * This method is used to change cell size.
     *
     * @param size this parameter changes the cell size
     * @return Nothing.
     */


    /**
     * This method is used to assign a new board.
     *
     * @param board this parameter is used to change board
     * @return Nothing.
     *
     */

    public void setBoard(byte[][] board) {
        this.sBoard = board;
    }

    /**
     * This method is used to get current board.
     *
     * @return int this returns current board.
     */

    public byte[][] getBoard() {
        return sBoard;
    }

    public void moveCellsRight(){
        byte[][] rightBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((sBoard[x][y] == 1))rightBoard[x][y+1] = 1;
            }
        }
        sBoard = rightBoard;

    }

    public void moveCellsUp(){

        byte[][] upBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((sBoard[x][y] == 1))upBoard[x-1][y] = 1;
            }
        }
        sBoard = upBoard;
    }

    public void moveCellsLeft(){
        byte[][] leftBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((sBoard[x][y] == 1))leftBoard[x][y-1] = 1;

            }
        }
        sBoard = leftBoard;
    }
    public void moveCellsDown(){
        byte[][] downBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 0; x < canvasHeight; x++) {
            for (int y = 0; y < canvasWidth; y++){
                if ((sBoard[x][y] == 1))downBoard[x+1][y] = 1;
            }
        }
        sBoard = downBoard;
    }


}