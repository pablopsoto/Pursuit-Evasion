package main.game.logic;

import main.vision.Line;
import main.vision.PVector;
import main.util.Location;

import java.awt.*;
import java.awt.geom.Area;
import java.util.*;

public abstract class GameObject
{

    protected Location location;
    protected ID id;
    protected int velX, velY;
    protected double rotation;
    protected int sides;
    protected Polygon polygon = null;
    protected Handler handler;


    public GameObject(int x, int y, int sides, int radius, ID id, Handler handler)
    {
        location = new Location(x, y);
        this.sides = sides;
        this.id = id;
        this.handler = handler;

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
                handler.getSceneLines().add(new Line(start,end));
            }

//            System.out.println("Scenelines " + handler.getSceneLines());
        }
//        handler.getSceneLines().add(new Line(new PVector(xPoints[0],yPoints[0]),new PVector(xPoints[1],yPoints[1]) ));


        polygon = new Polygon(xPoints, yPoints, sides);

    }

    public GameObject(int x, int y, int sides, ID id, Handler handler, ArrayList<Integer> xPoints, ArrayList<Integer>
            yPoints)
    {
        location = new Location(x, y);
        this.sides = sides;
        this.id = id;
        this.handler = handler;

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
                handler.getSceneLines().add(new Line(start,end));
            }
//            handler.getSceneLines().add(new Line(new PVector(xPoints.get(xPoints.size()-1),yPoints.get(yPoints.size()-1)), new PVector(xPoints.get(0),yPoints.get(0))));
//            System.out.println("Scenelines " + handler.getSceneLines());
        }

        polygon = new Polygon(xPointsInt, yPointsInt, sides);
    }

    /**
     * This method is going to be used to run the logic of each GameObject
     */
    public  void tick()
    {
        applyVelocities();
        applyRotaion();


        polygon.translate(velX, velY);
        polygon = rotatedPolygon(rotation, polygon);


        if (collided(handler))
        {
            //System.out.println("COLIDED");
            polygon.translate(-velX, -velY);
        } else
        {
            // System.out.println("NOT COLIDED");
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

    public synchronized boolean collided(Handler handler)
    {
        boolean collided = false;
        Area a1 = new Area(polygon);


        for (GameObject o : handler.objects)
        {

            Area a2 = new Area(o.polygon);
            if (!this.equals(o))
            {

                a2.intersect(a1);
                if (!a2.isEmpty())
                {
                    collided = true;
                    break;
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

    public abstract void applyVelocities();

    public abstract void applyRotaion();

}
