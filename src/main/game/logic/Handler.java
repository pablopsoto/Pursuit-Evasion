package main.game.logic;


import main.game.logic.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class Handler {

    protected ArrayList<GameObject> objects = new ArrayList<>();

    public void tick(){
        for(GameObject o: objects){
            o.tick();
        }
    }

    public void render(Graphics g){
        for(GameObject o: objects){
            o.render(g);
        }
    }

    public void addObject(GameObject object){
        objects.add(object);
    }

    public void removeObject(GameObject object){
        objects.remove(object);
    }

}
