package test;

import main.game.Game;
import main.game.agent.Agent;
import main.game.obstacle.Obstacle;
import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.util.Location;

import javax.swing.*;
import java.awt.*;

public class Test {

    
    public void testTester(){
        System.out.print("This is a test");
    }

    public void testLocation(){
        Location a = new Location(300, 350);
        Location b = new Location(100, 400);
        float d = Location.distance(a,b);
        System.out.print(d);
    }

    public void testCollision(){
        Handler handler = new Handler();
        GameObject agent = new Agent(50,300,3, handler);
        GameObject obstacle = new Obstacle(75, 300, 4,30, handler);

        handler.addObject(agent);
        handler.addObject(obstacle);

        if(agent.collided(handler)){
            System.out.println("Collided");
        } else {
            System.out.println("Didn't collide");
        }
    }

    public void testCanvas(){
        new Game();
    }

    @org.junit.Test
    public void testRotation(){
        Handler handler = new Handler();
        GameObject agent = new Agent(0,0,3,handler);
        for(int i=0; i<agent.getPolygon().xpoints.length; i++){
            System.out.println("Point: " + "X: " + agent.getPolygon().xpoints[i] + "Y: " + agent.getPolygon().ypoints[i]);
        }
        System.out.println("Centre: " + agent.getLocation() );
        for (int j=0;j<10;j++) {
            agent.rotatedPolygon(Math.PI / 2, agent.getPolygon());
            for (int i = 0; i < agent.getPolygon().xpoints.length; i++) {
                System.out.println("Point: " + "X: " + agent.getPolygon().xpoints[i] + "Y: " + agent.getPolygon().ypoints[i]);
            }
            System.out.println("Centre: " + agent.getLocation());
        }
    }

}
