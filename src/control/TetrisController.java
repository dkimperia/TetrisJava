// TetrisController.java

package control;

import model.TetrisModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisController implements KeyListener {
    private TetrisModel model;

    public TetrisController(TetrisModel model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            model.moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            model.moveRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            model.moveDown();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            model.rotate();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed events
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key releases
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN) {
            // Stop moving down when the key is released
            model.stopMoveDown();
        }
    }
}
