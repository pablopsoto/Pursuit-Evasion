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
        applyVelocites(1, 0);
            for (int i = 0; i < this.getPolygon().xpoints.length; i++) {
                System.out.println("Point"+ i +" : " + "( " + this.getPolygon().xpoints[i] + ", " + this.getPolygon().ypoints[i] +")");
            }
            System.out.println("Centre: " + this.getLocation());

    }

    @Override
    public void applyRotaion() {
       rotation =1;
    }

    public void applyVelocites(int x, int y){
        velX =x;
        velY =y;
    }
}
