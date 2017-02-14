package main.game;

import main.game.board.Board;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class Game
{

    private Board board;



    public Game(int width, int height)
    {
        //then create the board
        board = new Board(width, height);

    }

    public int getHeight()
    {
        return board.getHeight();
    }

    public int getWidth()
    {
        return board.getWidth();
    }
}
