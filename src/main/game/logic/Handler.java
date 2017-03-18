package main.game.logic;


import main.vision.Line;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Handler {

    protected ArrayList<GameObject> objects = new ArrayList<>();
    protected java.util.List<Line> sceneLines = new ArrayList<>();
    protected java.util.List<Line> scanLines = new ArrayList<>();

     public synchronized void tick(){
        for(GameObject o: objects){
           o.tick();
        }
    }

     public synchronized void render(Graphics g){
       for(GameObject o: objects){
            o.render(g);
        }
    }

    public synchronized void addObject(GameObject object){
        objects.add(object);
    }

    public void removeObject(GameObject object){
        objects.remove(object);
    }

    public List<Line> getSceneLines()
    {
        return sceneLines;
    }

    public List<Line> getScanLines()
    {
        return scanLines;
    }

    public void setScanLines(List<Line> scanLines)
    {
        this.scanLines = scanLines;
    }
}
