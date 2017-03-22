package main.game.logic;

import main.game.Game;
import main.game.agent.Agent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

/**
 * Created by josevelasquez on 3/3/17.
 */
public class KeyIn extends KeyAdapter
{


    private Game game;

    public KeyIn(Game game)
    {
        this.game = game;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyChar();
//        System.out.println(e.getKeyChar());

        Agent a = game.agent;
        Agent b = game.agent2;
        if (key == 'a')
        {
//                    game.getAgent().setVelX(game.getAgent().getVelX()+1);
            a.setX(a.getX() - 1);
            a.polygon.translate(-1,0);
        }
        if (key == 'd')
        {
            a.setX(a.getX() + 1);
            a.polygon.translate(1,0);
        }

        if (key == 'w')
        {
            game.getAgent().setY(game.getAgent().getY() - 1);
            a.polygon.translate(0,-1);
        }

        if (key == 's')
        {
            game.getAgent().setY(game.getAgent().getY() + 1);
            a.polygon.translate(0,1);
        }

        if (key ==',')
        {
            a.setAngle(a.getAngle() - 0.01);
        }
        if (key == '.')
        {
            a.setAngle(a.getAngle() +0.01);
        }

//agent B
        if (key == '4')
        {
//                    game.getAgent().setVelX(game.getAgent().getVelX()+1);
            b.setX(b.getX() - 1);
            b.polygon.translate(-1,0);
        }
        if (key == '6')
        {
            b.setX(b.getX() + 1);
            b.polygon.translate(1,0);
        }

        if (key == '8')
        {
            b.setY(b.getY() - 1);
            b.polygon.translate(0,-1);
        }

        if (key == '5')
        {
            b.setY(b.getY() + 1);
            b.polygon.translate(0,1);
        }

        if (key =='[')
        {
            b.setAngle(b.getAngle() - 0.01);
        }
        if (key == ']')
        {
            b.setAngle(b.getAngle() +0.01);
        }


    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

    }

}
