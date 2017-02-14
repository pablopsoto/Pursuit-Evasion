package main.display.game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class BoardPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private DrawPanel draw;

    private int panelWidth = 750;
    private int panelHeight = 750;
//    private GameState gameState;
    public BoardPanel(){
        draw = new DrawPanel(panelWidth,panelHeight);

        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw.draw(g);
    }

    /*@Override
    public void update()
    {
        repaint();
    }*/
}
