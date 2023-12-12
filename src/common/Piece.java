// Piece.java

package common;

public class Piece {
    private int[][] originalShape;
    private int[][] currentShape;

    public Piece(int[][] shape) {
        this.originalShape = shape;
        this.currentShape = shape;
    }

    public int[][] getShape() {
        return currentShape;
    }

    public int getRows() {
        return currentShape.length;
    }

    public int getCols() {
        return currentShape[0].length;
    }

    public void rotate() {
        int rows = currentShape.length;
        int cols = currentShape[0].length;
        int[][] rotatedShape = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedShape[j][rows - 1 - i] = currentShape[i][j];
            }
        }

        currentShape = rotatedShape;
    }

    public void rotateBack() {
        currentShape = originalShape;
    }
}
