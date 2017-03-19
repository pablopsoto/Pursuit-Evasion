package main.game.logic;

import main.vision.Line;
import main.vision.PVector;
import main.util.Location;

import java.awt.*;
import java.awt.geom.Area;
import java.util.*;
import java.util.List;

public abstract class GameObject
{

    protected Location location;
    protected ID id;
    protected int posX, posY;
    protected int x, y;
    protected double rotation;
    protected int sides;
    protected Polygon polygon = null;
    protected Handler objectHandler;
    protected Handler agentHandler;


    public GameObject(int x, int y, int sides, int radius, ID id, Handler objectHandler)
    {
        location = new Location(x, y);
        this.sides = sides;
        this.id = id;
        this.objectHandler = objectHandler;
        this.x = x;
        this.y = y;

        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        double angle = 2 * Math.PI / sides;

        for (int i = 0; i < sides; i++)
        {
            double a = i * angle;
            xPoints[i] = (int) (location.x + Math.cos(a) * radius);
            yPoints[i] = (int) (location.y + Math.sin(a) * radius);
            if (i > 0)
            {
                PVector start = new PVector(xPoints[i-1], yPoints[i-1]);
                PVector end = new PVector(xPoints[i], yPoints[i]);
                objectHandler.getSceneLines().add(new Line(start,end));
            }
//            System.out.println("Scenelines " + handler.getSceneLines());
        }


        polygon = new Polygon(xPoints, yPoints, sides);

    }

    public GameObject(int x, int y, int sides, ID id, Handler objectHandler, ArrayList<Integer> xPoints, ArrayList<Integer>
            yPoints)
    {
        location = new Location(x, y);
        this.sides = sides;
        this.id = id;
        this.objectHandler = objectHandler;

        int[] xPointsInt = new int[sides];
        int[] yPointsInt = new int[sides];
        for (int i = 0; i < xPoints.size(); i++)
        {
            xPointsInt[i] = xPoints.get(i);
            yPointsInt[i] = yPoints.get(i);
            if (i > 0)
            {
                PVector start = new PVector(xPoints.get(i - 1), yPoints.get(i - 1));
                PVector end = new PVector(xPoints.get(i), yPoints.get(i));
                objectHandler.getSceneLines().add(new Line(start,end));
            }
//            System.out.println("Scenelines " + handler.getSceneLines());
        }

        polygon = new Polygon(xPointsInt, yPointsInt, sides);
    }

    /**
     * This method is going to be used to run the logic of each GameObject
     */
    public void tick()
    {
        getDirection();
        applyRotaion();


        polygon.translate(posX, posY);
        polygon = rotatedPolygon(rotation, polygon);


        if (collided(objectHandler))
        {
            //System.out.println("COLIDED");
            polygon.translate(-posX, -posY);
           
        } else
        {
            // System.out.println("NOT COLIDED");
        	setX(getX()+getVelX());
       	   	setY(getX()+getVelY());   
        }
    }

    /**
     * @param g graphics where GameObject will be rendered
     */
    public void render(Graphics g)
    {

        if (id == ID.EVADOR)
        {
            g.setColor(Color.CYAN);
        } else if (id == ID.PURSUER)
        {
            g.setColor(Color.RED);
        } else if (id == ID.OBSTABLE)
        {
            g.setColor(Color.WHITE);
        } else
        {
            g.setColor(Color.ORANGE);
        }

        g.fillPolygon(polygon);
    }

    public synchronized boolean collided(Handler objectHandler)
    {
        boolean collided = false;
        Area a1 = new Area(polygon);


        for (GameObject o : objectHandler.getObjects())
        {

            Area a2 = new Area(o.polygon);
            if (!this.equals(o))
            {

                a2.intersect(a1);
                if (!a2.isEmpty())
                {
                    collided = true;
//                    break;
                }
                
            }


        }


        return collided;
    }

    
    public Polygon rotatedPolygon(double theta, Polygon polygon)
    {

        //TODO: it rotates in the origin, not the polygon

        int[] xs = polygon.xpoints;
        int[] ys = polygon.ypoints;
        int x = (int) location.x;
        int y = (int) location.y;

        for (int i = 0; i < xs.length; i++)
        {
            int tempx = xs[i];
            int tempy = ys[i];

            xs[i] = (int) (tempx * Math.cos(theta) - tempy * Math.sin(theta));
            ys[i] = (int) (tempy * Math.cos(theta) + tempx * Math.sin(theta));
        }

        Polygon p = new Polygon(xs, ys, xs.length);

        return p;
    }
    
    
    public int getX(){
    	return x;
    }
    
    public void setX(int x){
    	this.x = x;
    }
    
    public void setY(int y){
    	this.y = y;
    }
    
    public int getY(){
    	return y;
    }

    public abstract void getDirection();

    public abstract void applyRotaion();
    
    public abstract int getVelY();
    public abstract void setVelY(int y);
    
    public abstract int getVelX();
    public abstract void setVelX(int x);
    
    public abstract void visionStart(Graphics g, double startX, double startY, List<Line> sceneLines, List<Line> scanLines);
}
