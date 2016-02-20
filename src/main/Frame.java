package main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Jakub Vitásek & Matěj Stuchlík
 */
public class Frame extends Canvas{
    JFrame frame;
    public Frame (int width, int height, String title, GamePanel game) {
        frame = new JFrame(title);
        frame.setMinimumSize(new Dimension(width / 2, height / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        game.setPreferredSize(new Dimension(width, height));
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    void makeFullscreen(boolean full) {
        frame.dispose();
        frame.setResizable(!full);
        frame.setUndecorated(full);
        if(full)
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        else
            frame.pack();
        frame.setVisible(true);
    }
}
