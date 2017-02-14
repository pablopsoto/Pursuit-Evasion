package main.display.game;

import main.game.board.Game;

import main.game.board.Board;
import main.game.board.SquareState;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class BoardPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private final int borderSize =20;
    private DrawPanel draw;

    private int panelWidth = 550;
    private int panelHeight = 550;
    public main.game.board.Game newGame;
    private int panelScale = 600;

    private int squareSize = 25;
    private Board board;
//    private GameState gameState;

//    public BoardPanel(SquareState[][] board){
    public BoardPanel(main.game.board.Game game){
        newGame = game;
        board = new Board(game.getWidth(),game.getHeight());
        int panelWidth = board.getWidth()*squareSize+borderSize*2;
        int panelHeight= board.getHeight()*squareSize+borderSize*2;
        draw = new DrawPanel(board,squareSize,borderSize);

        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        draw.draw(g, newGame);
    }

    /*@Override
    public void update()
    {
        repaint();
    }*/
}
