package main.game.logic;


import main.vision.Line;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Handler {

    protected ArrayList<GameObject> objects = new ArrayList<>();
    protected java.util.List<Line> sceneLines = new ArrayList<>();
    protected java.util.List<Line> scanLines = new ArrayList<>();

    synchronized public void tick(){
        for(GameObject o: objects){
           o.tick();
        }
    }

    synchronized public     void render(Graphics g){
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

    public List<Line> getSceneLines()
    {
        return sceneLines;
    }
}
