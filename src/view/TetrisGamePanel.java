// TetrisGamePanel.java

package view;

import common.Piece;
import model.TetrisModel;

import javax.swing.*;
import java.awt.*;

public class TetrisGamePanel extends JPanel {
    private TetrisModel model;

    public TetrisGamePanel(TetrisModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawPiece(g, model.getCurrentPiece());
    }

    private void drawGrid(Graphics g) {
        int[][] grid = model.getGrid();
        int cellSize = 30;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else {
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private void drawPiece(Graphics g, Piece piece) {
        // Draw the current Tetris piece
        if (piece != null) {
            int cellSize = 30;
            int rows = piece.getRows();
            int cols = piece.getCols();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (piece.getShape()[i][j] == 1) {
                        g.fillRect((model.getCurrentCol() + j) * cellSize, (model.getCurrentRow() + i) * cellSize, cellSize, cellSize);
                    }
                }
            }
        }
    }
}
