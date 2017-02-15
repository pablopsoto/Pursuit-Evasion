package main.display.game;

import main.game.board.Board;
import main.game.board.Game;
import main.game.board.SquareState;

import java.awt.*;


import static main.game.board.SquareState.*;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class DrawPanel
{

    private final int squareSize;
    private int scale;
    private int borderSize = 20;
    private int panelWidth;
    private int panelHeight;
    private Board board;

    public DrawPanel(Board board,int squareSize,int borderSize){
        this.board = board;
        this.squareSize = squareSize;
        this.borderSize=borderSize;

    }

    public void draw(Graphics g, Game game)
    {
        Game agent1 = game;
        Game agent2 = game;
        //Board board = new Board(10, 10);
        System.out.println("BoardWidth = " + board.getWidth());
        System.out.println("BoardHeight = " + board.getHeight());
        g.setColor(Color.black);
        g.fillRect(0,0,borderSize*2+squareSize*board.getWidth(),borderSize*2+squareSize*board.getHeight());
       /* for (int i = 0; i < board.getWidth(); i++)
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
                    case OBSTACLE:
                        g.setColor(Color.BLUE);
                        break;
                }
               g.fillRect(i * squareSize + borderSize, j * squareSize + borderSize, squareSize, squareSize);

            }
            */
            g.setColor(Color.white);
            g.fillRect(agent1.agent.x * 2 +20, agent1.agent.y * 2 +20, 10, 10);

        }
    }

