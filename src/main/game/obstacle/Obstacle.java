package main.game.obstacle;


import main.logic.GameObject;
import main.logic.Handler;
import main.logic.ID;

/**
 * Created by josevelasquez on 3/7/17.
 */
public class Obstacle extends GameObject {

    public Obstacle(int x, int y, int sides, Handler handler) {
        super(x, y, sides, 30, ID.OBSTABLE, handler);
    }

    @Override
    public void applyVelocities() {
        velX = 0;
        velY = 0;
    }

    @Override
    public void applyRotaion() {

    }
}
