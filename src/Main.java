// TetrisGame.java

import control.TetrisController;
import model.TetrisModel;
import view.TetrisView;

public class Main {
    public static void main(String[] args) {
        TetrisModel model = new TetrisModel();
        TetrisView view = new TetrisView(model);
        TetrisController controller = new TetrisController(model);

        // Set up the GUI components and connect the controller
        view.addKeyListener(controller);
        view.setFocusable(true);
        view.requestFocusInWindow();

        // Game loop
        while (true) {
            // Update game state
            // For example, move the piece down in each iteration
            model.moveDown();

            // Repaint the view
            view.repaint();

            // Add a small delay to control the speed of the game
            try {
                Thread.sleep(500);  // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}