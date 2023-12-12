// TetrisView.java

package view;

import common.Observer;
import model.TetrisModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisView extends JFrame implements Observer {
    private TetrisModel model;

    private JPanel gamePanel;
    private Timer timer;

    public TetrisView(TetrisModel model) {
        this.model = model;
        this.model.addObserver(this);

        // Set up the graphical interface
        gamePanel = new TetrisGamePanel(model);
        this.add(gamePanel);

        // Set up a timer for animation
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.moveDown();
            }
        });
        timer.start();

        // Set up the JFrame
        this.setSize(400, 600);
        this.setTitle("Tetris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Implement graphical updates based on the model's state
    @Override
    public void update() {
        gamePanel.repaint();
    }
}
