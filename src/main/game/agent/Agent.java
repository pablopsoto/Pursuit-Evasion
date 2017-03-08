package main.game.agent;


import main.logic.GameObject;
import main.logic.Handler;
import main.logic.ID;

/**
 * Created by josevelasquez on 3/7/17.
 */
public class Agent extends GameObject{



    public Agent(int x, int y, int sides, Handler handler) {
        super(x, y, sides, 30, ID.NEUTRAL, handler);
    }

    @Override
    public void applyVelocities() {
        applyVelocites(3, 0);
    }

    @Override
    public void applyRotaion() {
       // rotation = Math.PI/13000;
    }

    public void applyVelocites(int x, int y){
        velX =x;
        velY =y;
    }
}
