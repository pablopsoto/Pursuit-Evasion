package main.display;

import javafx.scene.input.MouseEvent;
import main.game.Game;
import main.game.logic.KeyIn;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window extends Canvas {

    public Window(int w, int h, String title, Game game){

        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(w, h));
        frame.setMaximumSize(new Dimension(w, h));
        frame.setMinimumSize(new Dimension(w, h));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game,BorderLayout.CENTER);
        frame.add(new MapPanel(game), BorderLayout.SOUTH);
        frame.add(new RegularPolygonPanel(game), BorderLayout.EAST);
        frame.addKeyListener(new KeyIn(game));

        frame.setVisible(true);
        frame.setFocusable(true);
        game.start();

    }

}
