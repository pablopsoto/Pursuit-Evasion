package main.game.board;

import main.display.MainFrame;
import main.display.game.BoardPanel;
import main.display.game.GameFrame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Created by Jeroen on 14/02/2017.
 */
public class Game implements ActionListener, KeyListener
{
    private Board board;
    public int direction=DOWN, time;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public boolean over=false, paused;
    public Timer timer = new Timer(20, this);
    public Point agent;
    public Random random;
    public BoardPanel newRenderPanel;
    private int width;
    private int height;



        public Game(int newWidth, int newHeight, JFrame frame)
        {
            //then create the board
            //board = new Board(width, height);
            width = newWidth;
            height = newHeight;

            frame.add(newRenderPanel = new BoardPanel(this));
            frame.addKeyListener(this);
            startGame();
        }

    public void startGame()
    {
        over = false;
        paused = false;
        time = 0;
        direction = DOWN;
        agent = new Point(0, -1);
        random = new Random();
        timer.start();
    }
    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int i = e.getKeyCode();

        if (i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)
        {
            direction = LEFT;
        }

        if (i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT)
        {
            direction = RIGHT;
        }

        if (i == KeyEvent.VK_W || i == KeyEvent.VK_UP)
        {
            direction = UP;
        }

        if (i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN)
        {
            direction = DOWN;
        }
        if (i == KeyEvent.VK_SPACE)
        {
            if (over)
            {
                startGame();
            }
            else
            {
                paused = !paused;
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        newRenderPanel.repaint();

        if (agent != null && !over && !paused)
        {
            time++;

            if (direction == UP)
            {
                if (agent.y - 1 >= 0)
                {
                    agent = new Point(agent.x, agent.y - 1);
                }
                else
                {
                    over = true;

                }
            }

            if (direction == DOWN)
            {
                if (agent.y + 1 < 500 )
                {
                    agent = new Point(agent.x, agent.y + 1);
                }
                else
                {
                    over = true;
                }
            }

            if (direction == LEFT)
            {
                if (agent.x - 1 >= 0 )
                {
                    agent = new Point(agent.x - 1, agent.y);
                }
                else
                {
                    over = true;
                }
            }

            if (direction == RIGHT)
            {
                if (agent.x + 1 < 500 )
                {
                    agent = new Point(agent.x + 1, agent.y);
                }
                else
                {
                    over = true;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }


}
