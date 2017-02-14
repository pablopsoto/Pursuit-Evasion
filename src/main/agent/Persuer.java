package main.agent;

import main.game.board.Board;
import main.logic.Location;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class Persuer implements Agent
{

    private Board board;

    @Override
    public Location getLocation()
    {
        return null;
    }

    @Override
    public Board getBoard()
    {
        return board;
    }

    @Override
    public void setBoard(Board b)
    {
    board = b;
    }

    @Override
    public void moveUp()
    {

    }

    @Override
    public void moveDown()
    {

    }

    @Override
    public void moveLeft()
    {

    }

    @Override
    public void moveRight()
    {

    }
}
