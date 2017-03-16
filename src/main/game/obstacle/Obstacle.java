package main.game.obstacle;

import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.logic.ID;

/**
 * Created by josevelasquez on 3/7/17.
 */
public class Obstacle extends GameObject {

    public Obstacle(int x, int y, int sides,int radius, Handler handler) {
        super(x, y, sides, radius, ID.OBSTABLE, handler);
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
