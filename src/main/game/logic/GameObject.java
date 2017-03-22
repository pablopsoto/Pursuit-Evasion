package main.game.logic;

import main.util.Location;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class GameObject {

    protected int[] allX = new int[360*4];
    protected int[] allY = new int[360*4];

    protected Location location;
    protected ID id;
    protected int velX, velY;
    protected double rotation;
    protected int sides;
    protected Polygon polygon;
    protected Polygon fullPolygon;
    protected Handler handler;

    private int[] pointer;
    private double shift;

    public GameObject(int x, int y, int sides, int radius,ID id, Handler handler){

        location = new Location(x,y);
        this.sides = sides;
        this.id = id;
        this.handler = handler;

        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        double ang = 2 * Math.PI / 360 * 4;

        for (int i = 0; i < 360 * 4; i++){
            double an = i * ang;
            allX[i] = (int)(location.x + Math.cos(an) * radius);
            allY[i] = (int)(location.y + Math.sin(an) * radius);
        }

        fullPolygon = new Polygon(allX, allY, allX.length);

        shift = 1440/ sides;
        pointer = new int[sides];

        for (int i = 0; i < sides; i++){
            pointer[i] = (int)shift * i;
            xPoints[i] = allX[(int)shift * i];
            yPoints[i] = allY[(int)shift * i];
        }

        polygon = new Polygon(xPoints, yPoints, sides);

    }
    public GameObject(int x, int y, int sides, ID id, Handler handler,ArrayList<Integer> xPoints, ArrayList<Integer> yPoints){
        location = new Location(x,y);
        this.sides = sides;
        this.id = id;
        this.handler = handler;

        int[] xPointsInt = new int[sides];
        int[] yPointsInt = new int[sides];

        for(int i = 0;i<xPoints.size();i++){
            xPointsInt[i]=xPoints.get(i);
        }

        for(int i = 0;i<yPoints.size();i++){
            yPointsInt[i]=yPoints.get(i);
        }
        fullPolygon = new Polygon(new int[sides], new int[sides], 4*360);
        pointer  = new int[sides];
        polygon = new Polygon(xPointsInt,yPointsInt,sides);
    }

    public Polygon getPolygon(){
            return polygon;
        }
    public Location getLocation(){
        return location;
    }
    /**
     * This method is going to be used to run the logic of each GameObject
     */
    public void tick(){
        applyVelocities();

        location.x += velX;
        location.y += velY;

        polygon.translate(velX, velY);
        fullPolygon.translate(velX, velY);

        if (collided(handler)) {
            //System.out.println("COLIDED");
            polygon.translate(-velX, -velY);
            fullPolygon.translate(-velX, -velY);
            location.x -= velX;
            location.y -= velY;
        } else {
            // System.out.println("NOT COLIDED");
        }
        applyRotaion();
        polygon = rotatedPolygon(rotation, polygon);
        if (collided(handler)) {
            polygon = rotatedPolygon(-rotation, polygon);
        } else {

        }

    }

    /**
     * @param g graphics where GameObject will be rendered
     */
    public void render(Graphics g){

        if(id == ID.EVADOR){
            g.setColor(Color.CYAN);
        } else if (id == ID.PURSUER){
            g.setColor(Color.RED);
        } else if (id == ID.OBSTABLE){
            g.setColor(Color.WHITE);
        }   else{
            g.setColor(Color.ORANGE);
        }

        g.fillPolygon(polygon);
        g.setColor(Color.PINK);
        g.fillOval((int)location.x -3, (int)location.y - 3, 6,6);
    }

    public boolean collided(Handler handler){
        boolean collided = false;
        Area a1 = new Area(polygon);

        for (GameObject o: handler.objects){
            Area a2 = new Area(o.polygon);
            if(!this.equals(o)) {
                a2.intersect(a1);
                if (!a2.isEmpty()) {
                    collided = true;
                    break;
                }
            }
        }

        return collided;
    }

    public Polygon rotatedPolygon(double theta, Polygon polygon){

        /*TODO: use allX and allY locations to do rotation on the polygon
        feel free to re write this part*/

        int[] xs = polygon.xpoints;
        int[] ys = polygon.ypoints;

        for(int i = 0; i < xs.length; i++){

            if(theta == 1){
                if(pointer[i] == allX.length - 1){
                    pointer[i] = 0;
                } else {
                    pointer[i]++;
                }
            } else if( theta == -1){
                if(pointer[i] == 0){
                    pointer[i] = allX.length - 1;
                } else {
                    pointer[i]--;
                }
            }

           // xs[i] = allX[pointer[i]];
            //ys[i] = allY[pointer[i]];
            xs[i] = fullPolygon.xpoints[pointer[i]];
            ys[i] = fullPolygon.ypoints[pointer[i]];
        }

        Polygon p = new Polygon(xs, ys, xs.length);

        return p;
    }

    private void rotationPoint(Location origin, Location point, double theta){
        double x1 = point.x - origin.x;
        double y1 = point.y - origin.y;

        double x2 = x1 * Math.cos(theta) - y1 * Math.sin(theta);
        double y2 = x1 * Math.sin(theta) + y1 * Math.cos(theta);

        point.x = (float)x2 + origin.x;
        point.y = (float)y2 + origin.y;
    }

    private void updateAllPoints(int sides, int pointer){

    }

    public abstract void applyVelocities();
    public abstract void applyRotaion();

}
