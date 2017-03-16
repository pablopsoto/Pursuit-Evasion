package main.game.agent;

import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.logic.ID;


/**
 * Created by josevelasquez on 3/7/17.
 */
public class Agent extends GameObject{

    public Agent(int x, int y, int sides, Handler handler) {
        super(x, y, sides, 30, ID.NEUTRAL, handler);
    }

    @Override
    public void applyVelocities() {
        applyVelocites(0, 0);
        for (int j=0;j<10;j++) {
            for (int i = 0; i < this.getPolygon().xpoints.length; i++) {
                System.out.println("Point: " + "X: " + this.getPolygon().xpoints[i] + "Y: " + this.getPolygon().ypoints[i]);
            }
            System.out.println("Centre: " + this.getLocation());
        }
    }

    @Override
    public void applyRotaion() {
       rotation = 0.0174533;
    }

    public void applyVelocites(int x, int y){
        velX =x;
        velY =y;
    }
}
