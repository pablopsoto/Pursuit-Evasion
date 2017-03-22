package main.game.logic;

import main.game.Game;
import main.game.agent.Agent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by josevelasquez on 3/3/17.
 */
public class KeyIn extends KeyAdapter {


    private Game game;

    public KeyIn(Game game){
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyChar();
        System.out.println(e.getKeyChar());

        Agent a = game.getAgent();

                if(key == 'a'){
//                    game.getAgent().setVelX(game.getAgent().getVelX()+1);
                    a.setX(a.getX()-1);
                }


                if(key == 'd'){
                    a.setX(a.getX()+1);
                }

                if(key == 'w'){
                    game.getAgent().setY(game.getAgent().getY()-1);
                }

                if(key == 's'){
                    game.getAgent().setY(game.getAgent().getY()+1);
                }

                if(key == KeyEvent.KEY_LOCATION_LEFT){
                   a.setAngle(a.getAngle()-1);
                }




    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

    }

}
