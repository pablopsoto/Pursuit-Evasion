package main.game.logic;


import main.vision.Algorithm;
import main.vision.Line;
import main.vision.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Handler {

    protected ArrayList<GameObject> objects = new ArrayList<>();
    protected java.util.List<Line> sceneLines = new ArrayList<>();
    protected java.util.List<Line> scanLines = new ArrayList<>();
    private Algorithm algorithm = new Algorithm();
    private double startX=100;
    private double startY=100;

    public void visionStart( Graphics g){
        scanLines = algorithm.createScanLines(startX,startY);
        List<PVector> points = algorithm.getIntersectionPoints( scanLines, sceneLines);
        int count=0;
        g.setColor(Color.BLUE);
//        System.out.println("Points " + points);
        for( PVector point: points) {

            if( count == 0) {
//                g.moveTo(point.x, point.y);
            } else {
                g.drawLine((int)points.get(count-1).x,(int)points.get(count-1).y,(int)point.x,(int)point.y);
            }
            count++;
        }
        g.drawLine((int)points.get(count-1).x,(int)points.get(count-1).y,(int)points.get(0).x,(int)points.get(0).y);
    }

     public synchronized void tick(){
        for(GameObject o: objects){
           o.tick();
        }
    }

     public synchronized void render(Graphics g){
       for(GameObject o: objects){
            o.render(g);
        }
        visionStart(g);
      startX+=0.01;
    }

    public synchronized void addObject(GameObject object){
        objects.add(object);
    }

    public synchronized void removeObject(GameObject object){
        objects.remove(object);
    }

    public synchronized List<Line> getSceneLines()
    {
        return sceneLines;
    }

    public synchronized List<Line> getScanLines()
    {
        return scanLines;
    }

    public synchronized void setScanLines(List<Line> scanLines)
    {
        this.scanLines = scanLines;
    }
}
