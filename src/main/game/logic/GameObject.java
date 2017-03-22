package main.game.logic;

import main.vision.Line;
import main.vision.PVector;
import main.util.Location;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public abstract class GameObject implements java.io.Serializable
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
    protected int velX,velY;

    public void setHandler(Handler h){
        this.objectHandler = h;
    }
    private int objectID;



    public GameObject(int x, int y, int sides, int radius, ID id, Handler objectHandler, int objectID)
    {
        location = new Location(x, y);
        this.sides = sides;
        this.id = id;
        this.objectHandler = objectHandler;
        this.x = x;
        this.y = y;
        this.objectID=objectID;
        System.out.println("OBJECTID = " + objectID);
        System.out.println("ID " + id);
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        double angle = 2 * Math.PI / sides;

        for (int i = 0; i < sides; i++)
        {
            double a = i * angle;
            xPoints[i] = (int) (location.x + Math.cos(a) * radius);
            yPoints[i] = (int) (location.y + Math.sin(a) * radius);
            if (i > 0 && id ==ID.OBSTACLE)
//            if (i > 0 )
            {
                PVector start = new PVector(xPoints[i-1], yPoints[i-1]);
                PVector end = new PVector(xPoints[i], yPoints[i]);
                objectHandler.getSceneLines().add(new Line(start,end,id));
            }

//            System.out.println("Scenelines " + handler.getSceneLines());
        }
        if(id == ID.OBSTACLE){
        PVector start = new PVector(xPoints[sides-1], yPoints[sides-1]);
        PVector end = new PVector(xPoints[0], yPoints[0]);
        objectHandler.getSceneLines().add(new Line(start,end,id));}

        ImageIcon image = new ImageIcon("jan.jpg");

        polygon = new Polygon(xPoints, yPoints, sides);

    }

    public GameObject(int x, int y, int sides, ID id, Handler objectHandler, ArrayList<Integer> xPoints, ArrayList<Integer>
            yPoints, int objectID)
    {
        location = new Location(x, y);
        this.sides = sides;
        this.id = id;
        this.objectHandler = objectHandler;
        int[] xPointsInt = new int[sides];
        this.objectID=objectID;
        int[] yPointsInt = new int[sides];
        for (int i = 0; i < xPoints.size(); i++)
        {
            xPointsInt[i] = xPoints.get(i);
            yPointsInt[i] = yPoints.get(i);
            if (i > 0 && id != ID.EVADER && id!=ID.PURSUER)
//            if (i > 0 )
            {
                PVector start = new PVector(xPoints.get(i - 1), yPoints.get(i - 1));
                PVector end = new PVector(xPoints.get(i), yPoints.get(i));
                objectHandler.getSceneLines().add(new Line(start,end,id));
            }
//            System.out.println("Scenelines " + handler.getSceneLines());
        }
        if(id != ID.EVADER && id!=ID.PURSUER){
        PVector start = new PVector(xPointsInt[sides-1], yPointsInt[sides-1]);
        PVector end = new PVector(xPointsInt[0], yPointsInt[0]);
        objectHandler.getSceneLines().add(new Line(start,end,id));}

        polygon = new Polygon(xPointsInt, yPointsInt, sides);
    }

    /**
     * This method is going to be used to run the logic of each GameObject
     */
    public void tick()
    {
//        getDirection();
        applyRotaion();
        applyVelocity();
        location.x+=velX;
        location.y+=velY;

        polygon.translate(velX, velY);
        polygon = rotatedPolygon(rotation, polygon);


        if (collided(objectHandler))
        {
            polygon.translate(-velX,-velY);
            location.x-=velX;
            location.y-=velY;
//            setX(getX()-1);
//            setY(getY()-1);
//            polygon.translate(-1,-1);

        } else
        {
            // System.out.println("NOT COLIDED");
//        	setX(getX()+getVelX());
//       	   	setY(getY()+getVelY());
        }
    }

    /**
     * @param g graphics where GameObject will be rendered
     */
    public void render(Graphics g)
    {

        if (id == ID.EVADER)
        {
            g.setColor(Color.RED);
            renderPoints(polygon.xpoints,polygon.ypoints);
        } else if (id == ID.PURSUER)
        {
            g.setColor(Color.GREEN);
            renderPoints(polygon.xpoints,polygon.ypoints);

        } else if (id == ID.OBSTACLE)
        {
            g.setColor(Color.WHITE);
        } else
        {
            g.setColor(Color.ORANGE);
        }

        g.fillPolygon(polygon);
    }
    public void renderPoints(int[] xPoints,int[] yPoints)
    {
        for (int i = 1; i < xPoints.length; i++)
        {
            PVector start = new PVector(xPoints[i-1], yPoints[i-1]);
            PVector end = new PVector(xPoints[i], yPoints[i]);
            objectHandler.getAgentLines().add(new Line(start,end,id,objectID));
        }
        PVector start = new PVector(xPoints[sides-1], yPoints[sides-1]);
        PVector end = new PVector(xPoints[0], yPoints[0]);
        objectHandler.getAgentLines().add(new Line(start,end,id,objectID));
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
    	return (int) location.x;
    }

    public void setX(int x){
    	this.x = x;
    }

    public void setY(int y){
    	this.y = y;
    }

    public int getY(){
    	return (int) location.y;
    }

    public abstract void getDirection();

    public abstract void applyRotaion();
    public abstract void applyVelocity();

    public abstract int getVelY();
    public abstract void setVelY(int y);

    public abstract int getVelX();
    public abstract void setVelX(int x);

    public abstract void visionStart(Graphics g, double startX, double startY, List<Line> sceneLines, List<Line> scanLines, List<Line> agentLines);


    public int getObjectID()
    {
        return objectID;
    }
}
