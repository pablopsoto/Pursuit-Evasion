package main.game.obstacle;

import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.logic.ID;

import java.util.ArrayList;

/**
 * Created by Jeroen on 13/03/2017.
 */
public class BorderObstacle extends GameObject{
        public BorderObstacle(int x, int y, int sides, Handler handler, ArrayList<Integer> xPoints, ArrayList<Integer> yPoints){
            super(x,y,sides, ID.OBSTABLE,handler,xPoints,yPoints);
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
