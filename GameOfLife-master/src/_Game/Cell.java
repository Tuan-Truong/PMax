package _Game;

import javafx.scene.paint.Color;

/**
 * The Game Of Life program created for HIOA final project
 * The Board class have all the variables of the cell property.
 *
 * @version 1.0
 * @since   2017-01-14
 */

public class Cell {
    private double cellSize;
    private int cellGap;
    public Color cellColor;

    /**
     *  Constructs and initializes cellSize, cellGap and cellColor
     */

    public Cell(){

        cellSize = 10;
        cellGap = 1;
        cellColor = Color.WHITE;
    }

    /**
     * This method returning cell color
     * @return cellColor
     */

    public Color getCellColor() {
        return cellColor;
    }

    /**
     * @param cellColor parameter is assigned to the cellColor for this class
     */

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }

    /**
     * This method returning cell size
     * @return cellSize
     */
    public double getCellSize() {
        return cellSize;
    }

    /**
     * @param cellSize parameter is assigned to the cellSize for this class
     */

    public void setCellSize(double cellSize) {

        this.cellSize = cellSize;
    }

    /**
     * This method returning cell gap
     * @return cellGap
     */

    public int getCellGap() {
        if (getCellSize() < 2){
            cellGap = 0;
        }
        else{
            cellGap = 1;
        }
        return cellGap;
    }


}
