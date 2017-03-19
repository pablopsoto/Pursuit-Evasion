package main.game.logic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by josevelasquez on 3/3/17.
 */
public class KeyIn extends KeyAdapter {

    Handler handler;

    public KeyIn(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyChar();
        System.out.println(e.getKeyChar());

        for(GameObject object : handler.getObjects()){

            if(object.id == ID.NEUTRAL){

                if(key == 'a'){
                    object.location.x--;
                }

                if(key == 'd'){
                    object.location.x++;
                }

                if(key == 'w'){
                    object.location.y--;
                }

                if(key == 's'){
                    object.location.y++;
                }

            }

        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

    }

}
