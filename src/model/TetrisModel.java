// TetrisModel.java

package model;

import common.Observer;
import common.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TetrisModel {
    private List<Observer> observers = new ArrayList<>();
    private int[][] grid;
    private Piece currentPiece;
    private int currentRow;
    private int currentCol;

    public TetrisModel() {
        // Initialize the model, grid, and current piece
        grid = new int[20][10]; // Adjust the grid size as needed
        generateRandomPiece();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    private void generateRandomPiece() {
        Random random = new Random();
        int pieceType = random.nextInt(7); // There are 7 different Tetris pieces

        switch (pieceType) {
            case 0:
                currentPiece = new Piece(new int[][]{{1, 1, 1, 1}});
                break;
            case 1:
                currentPiece = new Piece(new int[][]{{1, 1}, {1, 1}});
                break;
            case 2:
                currentPiece = new Piece(new int[][]{{1, 1, 1}, {0, 1, 0}});
                break;
            // Add more piece types as needed
        }

        // Set the initial position of the piece
        currentRow = 0;
        currentCol = (grid[0].length - currentPiece.getCols()) / 2;

        // Check for collisions with the new piece
        if (isCollision()) {
            // Game over condition: stop the game or take appropriate action
            // For now, simply reset the grid and generate a new piece
            grid = new int[20][10];
            generateRandomPiece();
        }
    }

    private boolean isCollision() {
        int[][] shape = currentPiece.getShape();
        int rows = shape.length;
        int cols = shape[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (shape[i][j] == 1) {
                    int newRow = currentRow + i;
                    int newCol = currentCol + j;

                    // Check for collisions with the grid boundaries
                    if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
                        return true;
                    }

                    // Check for collisions with existing pieces in the grid
                    if (grid[newRow][newCol] == 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void moveLeft() {
        currentCol--;
        if (isCollision()) {
            currentCol++; // Move back if there's a collision
        }
        notifyObservers();
    }

    public void moveRight() {
        currentCol++;
        if (isCollision()) {
            currentCol--; // Move back if there's a collision
        }
        notifyObservers();
    }

    public void moveDown() {
        currentRow++;
        if (isCollision()) {
            currentRow--; // Move back if there's a collision
            placePiece(); // Place the piece in the grid and generate a new one
        }
        notifyObservers();
    }

    public void stopMoveDown() {
        // Stop moving down (optional, depending on your requirements)
    }

    public void rotate() {
        currentPiece.rotate();
        if (isCollision()) {
            currentPiece.rotateBack(); // Rotate back if there's a collision
        }
        notifyObservers();
    }

    private void placePiece() {
        // Place the current piece in the grid
        int[][] shape = currentPiece.getShape();
        int rows = shape.length;
        int cols = shape[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (shape[i][j] == 1) {
                    grid[currentRow + i][currentCol + j] = 1;
                }
            }
        }

        // Generate a new random piece
        generateRandomPiece();
    }

    public int[][] getGrid() {
        return grid;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }
}
