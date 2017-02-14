package main.game.board;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class Board
{
    private SquareState[][] board;

    public int getWidth()
    {
        return board.length;
    }

    public int getHeight()
    {
        return board[0].length;
    }

    private int boardWidth;
    private int boardHeight;
    public Board(int width, int height)
    {
        board = new SquareState[width][height];
        for(int i =0; i<width;i++){
            for(int j =0;j<height;j++){
                board[i][j]=SquareState.NEUTRAL;
            }
        }
        System.out.println("RANDOM  = " + (int)(Math.random() * width));
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.EVD;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.EVD;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.PSR;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.PSR;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.OBSTACLE;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.OBSTACLE;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.OBSTACLE;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.OBSTACLE;
        board[(int)(Math.random() * width)][(int)(Math.random() * height)] = SquareState.OBSTACLE;
    }

    public SquareState getState(int width, int height){
        return board[width][height];
    }


}
