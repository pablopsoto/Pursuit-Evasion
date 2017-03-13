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
        applyVelocites(3, 1);
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
