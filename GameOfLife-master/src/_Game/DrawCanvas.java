package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawCanvas {

     Cell cell;
    StaticBoard staticBoard;
    private int canvasHeight;
    private int canvasWidth;
    private byte[][] sboard;
    int oldJ;
    int oldI;

    public DrawCanvas(int height, int width, byte[][] sboard){
        staticBoard = new StaticBoard(sboard,width,height);
        cell = new Cell();

        this.canvasHeight = height;
        this.canvasWidth = width;
        this.sboard = sboard;
        cell = new Cell();
    }


    /**
     * This method is used to draw a vertical and horizontal lines on canvas.
     *
     * @return Nothing.
     * @param gc is the first parameter to draw on canvas
     * @param lineWidth is the second choosing the thickness of line
     * @param lineColor is the third parameter and it is choosing the color of line
     *
     */

    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(3);
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

    /**
     * This method is used to draw a cell on canvas.
     *
     * @return Nothing.
     *
     */
    public void drawCells(GraphicsContext gc) {


        gc.setFill(cell.getCellColor());
        try {
            for (int i = 0; i < this.canvasHeight; i++) {
                for (int j = 0; j < this.canvasWidth; j++) {
                    if (staticBoard.getBoard()[i][j] == 1) {
                        gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                    }
                }
            }


            //System.out.println("draw");
        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("ArrayIndexOutOfBoundsException" + e);
        }
    }

    /**
     * This method is used to draw when clicked on canvas.
     *
     * @return Nothing.
     * @exception Exception On input error.
     * @see Exception
     */

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
                if (staticBoard.getBoard()[i][j] == 0){
                    staticBoard.getBoard()[i][j] = 1;
                }
                else{ staticBoard.getBoard()[i][j] = 0;
                }

            }


            oldJ = j;
            oldI = i;
        }

        catch (Exception e){
            //System.err.println(" Exeption: " + e.getMessage());
            System.out.println("Task interrupted");

        }}

    /**
     * This method is used to change cell color.
     *
     * @param color this parameter changes the cell color in cell class
     * @return Nothing.
     */

    public void setCellColor(Color color){

        this.cell.setCellColor(color);

    }

    /**
     * This method is used to change cell size.
     *
     * @param size this parameter changes the cell size
     * @return Nothing.
     */

    public void setCellSize(double size){
        this.cell.setCellSize(size);
    }




}