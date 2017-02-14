package main.display.game;

import main.game.board.Board;
import main.game.board.SquareState;

import java.awt.*;


import static main.game.board.SquareState.*;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class DrawPanel
{
    private static int scale = 50;
    private static int borderSize = 20;
    public DrawPanel(int width, int height){


    }

    public void draw(Graphics g)
    {
        Board board = new Board(10, 10);
        System.out.println("BoardWidth = " + board.getWidth());
        System.out.println("BoardHeight = " + board.getHeight());
        g.setColor(Color.black);
        g.fillRect(0,0,board.getWidth()*scale+borderSize*2,board.getHeight()*scale+borderSize*2);
        for (int i = 0; i < board.getWidth(); i++)
        {
            for (int j = 0; j < board.getHeight(); j++)
            {
//                Rectangle rect = new Rectangle(i * scale + 10, j * scale + 10, scale, scale);
//                g.drawRect(i * scale + 10, j * scale + 10, scale-5, scale-5);

                SquareState state = board.getState(i, j);
                switch (state)
                {
                    case PSR:
                        g.setColor(Color.RED);
                        break;
                    case EVD:
                        g.setColor(Color.GREEN);
                        break;
                    case NEUTRAL:
                        g.setColor(Color.DARK_GRAY);
                        break;
                }
                g.fillRect(i * scale + borderSize, j * scale + borderSize, scale, scale);

            }


        }
    }
}
