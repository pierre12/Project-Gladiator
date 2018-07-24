package de.twins.ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(int width, int height, String title, Game game) {
        super(title);
        //Sets the size of the window
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));

        //closes jframe when clicking the x button
        //otherwise by clicking x button the window would just disappear but continue running
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Setting to null will display it in the midle of the screen
        setLocationRelativeTo(null);
        add(game);
        setVisible(true);
        game.start();

    }
}
