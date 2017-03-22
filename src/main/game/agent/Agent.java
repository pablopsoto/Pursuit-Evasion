package main.game.agent;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

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
	
	Algorithm algorithm = new Algorithm();
	

    public Agent(int x, int y, int sides, Handler handler) {
        super(x, y, sides, 30, ID.PURSUER, handler);
    }
    
    public void visionStart(Graphics g, double startX, double startY, List<Line> sceneLines, List<Line> scanLines){
        scanLines = algorithm.createScanLines(startX,startY);
        List<PVector> points = algorithm.getIntersectionPoints(scanLines, sceneLines);
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
    
    
    
}
