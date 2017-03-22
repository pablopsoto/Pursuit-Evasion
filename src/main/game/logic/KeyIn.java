package main.game.logic;

import main.game.Game;

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



                if(key == 'a'){
//                    game.getAgent().setVelX(game.getAgent().getVelX()+1);
                    game.getAgent().setX(game.getAgent().getX()-1);
                    System.out.println("Posx" + game.getAgent().posX);
                }


                if(key == 'd'){
                    game.getAgent().setX(game.getAgent().getX()+1);
                }

                if(key == 'w'){
                    game.getAgent().setY(game.getAgent().getY()-1);
                }

                if(key == 's'){
                    game.getAgent().setY(game.getAgent().getY()+1);
                }




    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

    }

}
