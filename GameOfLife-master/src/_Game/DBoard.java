package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class DBoard {

    private Cell cell;
    private int canvasHeight;
    private int canvasWidth;
    int  oldJ ;
    int  oldI ;


    public List<List<Byte>> dynamicBoard;



    public DBoard(int height, int width, List<List<Byte>> dynamic)  {

        this.dynamicBoard = dynamic;

        this.canvasHeight = height;
        this.canvasWidth = width;

        this.cell = new Cell();
        fillBoard(dynamicBoard);

    }


    public void fillBoard(List<List<Byte>> board){

        for(int x = 0; x < canvasWidth; x++){
            List<Byte> row = new ArrayList<Byte>();
            for(int y = 0; y < canvasHeight; y++){
                row.add((byte)0);
            }
            board.add(row);
        }
    }



    public void drawCells(GraphicsContext gc) {


        gc.setFill(cell.getCellColor());
        for (int i = 0; i <  dynamicBoard.size(); i++) {

            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {

                if (dynamicBoard.get(i).get(j) == 1) {

                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());

                }

            }
        }

    }



    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(1);
        gc.strokeRect(0, 0, this.canvasWidth, this.canvasHeight);

        gc.setLineWidth(lineWidth);

        double a = cell.getCellSize();
        double b = cell.getCellSize();

        for (int i = 0; i < this.canvasHeight; i++) {
            gc.strokeLine(0, a, this.canvasWidth, a);
            a += cell.getCellSize();
        }
        for (int i = 0; i < this.canvasWidth; i++) {
            gc.strokeLine(b, 0, b, this.canvasHeight);
            b += cell.getCellSize();
        }
    }

    public void nextGeneration() {
        List<List<Byte>> nextBoard =  new ArrayList<List<Byte>>();

        for(int i = 0; i < canvasWidth; i++) {
            List<Byte> inner = new ArrayList<Byte>();
            for (int j = 0; j < canvasHeight; j++)
                inner.add((byte) 0);
            nextBoard.add(inner);
        }

      /*  System.out.println(dBoard.size());
        System.out.println(dBoard.get(1).size());
        System.out.println(nextBoard.size());
        System.out.println(nextBoard.get(1).size());*/

        for (int x = 1; x < dynamicBoard.size(); x++) {
            for (int y = 1; y < dynamicBoard.get(x).size(); y++) {
                int cellNeighbors = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(x+i>=0 && y+j >=0) {
                            if (x+i < dynamicBoard.size() && y+j < dynamicBoard.get(x).size()) {
                                cellNeighbors += dynamicBoard.get(x + i).get(y + j);
                            }
                        }
                    }
                }

                cellNeighbors -= dynamicBoard.get(x).get(y);

                if ((dynamicBoard.get(x).get(y) == 1) && (cellNeighbors < 2))
                    nextBoard.get(x).set(y, (byte) 0);
                    //Mindre en 2 rundt -> cellen dør

                else if ((dynamicBoard.get(x).get(y)  == 1) && (cellNeighbors > 3))
                    nextBoard.get(x).set(y, (byte) 0);
                    //Fler en tre rundt -> cellen dør

                else if ((dynamicBoard.get(x).get(y)  == 0) && (cellNeighbors == 3))
                    nextBoard.get(x).set(y, (byte) 1);
                    //Cellen er lik 3 -> cellen kommer til live

                else {
                    //Cellen lever
                    //System.out.println("x = " + x + ", y = " + y + " dboard val = " + dBoard.get(x).get(y));
                    nextBoard.get(x).set(y, dynamicBoard.get(x).get(y));
                    //dBoard.get(x).set(y, nextBoard.get(x).get(y));
                }
            }
        }
        dynamicBoard = nextBoard;


        //System.out.println("Start");
    }



    public void cleanArray() {
        for (int i = 0; i < dynamicBoard.size(); i++) {
            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {
                dynamicBoard.get(i).set(j, (byte) 0);
            }
        }
    }

    public  void drawPattern(int[][] pattern, GraphicsContext gc) {
        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                if (pattern[row][col] == 1) {
                    dynamicBoard.get(row).set(col, (byte) 1);
                }
            }
        }
    }


    public void randomButton(){

        System.out.println(dynamicBoard.size());

        for (int i = 0; i < dynamicBoard.size(); i++) {
            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {
                dynamicBoard.get(i).set(j, (byte) (Math.random() * 2));
            }
        }
    }

    public void CanvasPressed(MouseEvent a) throws Exception {
        try {

            int j = (int)(a.getX() / cell.getCellSize()) + 1;
            int i = (int)(a.getY() / cell.getCellSize()) + 1;

            if (i <= 0) {
                return;
            }
            if (j <= 0) {
                return;
            }
            if (j != oldJ || i != oldI) {
                if (dynamicBoard.get(i).get(j) == 0){
                    dynamicBoard.get(i).set(j,(byte)1);
                }
                else{ dynamicBoard.get(i).set(j,(byte)0);

                }

            }


            oldJ = j;
            oldI = i;
        }

        catch (Exception e){
            //System.err.println(" Exeption: " + e.getMessage());
            System.out.println("Task interrupted");

        }}


    public void moveCellsRight(){

        List<List<Byte>> rightBoard =  new ArrayList<List<Byte>>();


        fillBoard(rightBoard);


        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++){
                if (dynamicBoard.get(x).get(y) == 1){

                    rightBoard.get(x).set(y + 1, (byte) 1);

                }


            }

        }
        dynamicBoard = rightBoard;

    }


    public void moveCellsUp(){

        List<List<Byte>> upBoard =  new ArrayList<List<Byte>>();
        fillBoard(upBoard);

        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(y).size(); y++){

                if (dynamicBoard.get(x).get(y) == 1){

                    upBoard.get(y).set(x + 1, (byte) 1);

                }


            }
        }
        dynamicBoard = upBoard;
    }

    public void moveCellsLeft() {
        System.out.println("funker");

        List<List<Byte>> leftBoard = new ArrayList<List<Byte>>();
        fillBoard(leftBoard);


        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++){

                if (dynamicBoard.get(x).get(y) == 1) {

                    leftBoard.get(y).set(x - 1, (byte) 1);

                }

            }
            dynamicBoard = leftBoard;
        }
    }

    public void moveCellsDown() {
        List<List<Byte>> downBoard = new ArrayList<List<Byte>>();
        fillBoard(downBoard);

        for (int x = 1; x < dynamicBoard.size(); x++) {
            for (int y = 1; y < dynamicBoard.get(x).size(); y++){

                if (dynamicBoard.get(x).get(y) == 1) {

                    downBoard.get(x).set(y - 1, (byte) 1);

                }

            }
            dynamicBoard = downBoard;
        }
    }

    public void setCellSize(double size){
        this.cell.setCellSize(size);
    }

    public void setCellColor(Color color){

        this.cell.setCellColor(color);

    }
}




