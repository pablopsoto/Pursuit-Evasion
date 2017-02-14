package main.display.game;

import main.display.game.BoardPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class GameFrame23 extends JFrame
{
    private static final long serialVersionUID = 1L;
    BoardPanel boardPanel;

    public GameFrame23(){
        //set main gui settings
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();

        //create panel which draws the board
        boardPanel = new BoardPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        getContentPane().add(boardPanel, c);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
