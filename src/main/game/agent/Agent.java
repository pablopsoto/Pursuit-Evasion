package main.game.agent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import main.game.Game;
import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.logic.ID;
import main.vision.Algorithm;
import main.vision.Line;
import main.vision.PVector;


/**
 * Created by josevelasquez on 3/7/17.
 */
public class Agent extends GameObject{
	
	int velX = 0;
	int velY = 0;

    double angle = 0;



    public Agent(int x, int y, int sides, Handler handler, Game game, int objectID) {
        super(x, y, sides, 10, ID.PURSUER, handler,objectID);

    }
    private Algorithm algorithm = new Algorithm();
    public void visionStart(Graphics g, double startX, double startY, List<Line> sceneLinesOld, List<Line> scanLines, List<Line> agentLines){
        scanLines = this.algorithm.createScanLines(startX,startY,angle,this.getObjectID());
        List<Line> sceneLines= new ArrayList<>();
        sceneLines.addAll(sceneLinesOld);
        sceneLines.addAll(agentLines);

        List<PVector> points = algorithm.getIntersectionPoints(scanLines, sceneLines);
        int count=0;
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(3));
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

    @Override
    public void getDirection() {
        applyVelocites(velX, velY);
    }

    @Override
    public void applyRotaion() {
       // rotation = Math.PI/13000;
    }
    
    public void setPosition(int x, int y){
    	posX = x;
    	posY = y;
    }
    
    public PVector getPosition(){
    	return new PVector(posX, posY);
    }

    public void applyVelocites(int x, int y){
        posX =x;
        posY =y;
    }
    
    public int getVelX(){
    	return velX;
    }
    
    public void setVelX(int x){
    	this.velX = x;
    }



    public int getVelY(){
    	return velY;
    }
    
    public void setVelY(int y){
    	this.velY = y;
    }


    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }
}
