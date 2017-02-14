package main.game;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class Table
{
    public static final int DEFAULT_BOARD_HEIGHT = 10;
    public static final int DEFAULT_BOARD_WIDTH = 10;

    public int currentWidth;
    public int currentHeight;
    public Game createNewGame()
    {
        Game game = new Game(currentWidth, currentHeight);
        return game;
    }

    public void setCurrentHeight(int currentHeight)
    {
        this.currentHeight = currentHeight;
    }

    public void setCurrentWidth(int currentWidth)
    {
        this.currentWidth = currentWidth;
    }
}
