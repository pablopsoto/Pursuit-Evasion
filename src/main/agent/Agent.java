package main.agent;


import main.game.board.Board;
import main.logic.Location;

public interface Agent {

    Location getLocation();

    public Board getBoard();
    public void setBoard(Board b);
    public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();



}
