package main.game.obstacle;

import java.awt.Graphics;
import java.util.List;

import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.logic.ID;
import main.vision.Line;

/**
 * Created by josevelasquez on 3/7/17.
 */
public class Obstacle extends GameObject {

    public Obstacle(int x, int y, int sides,int radius, Handler handler,int objectID) {
        super(x, y, sides, radius, ID.OBSTACLE, handler,objectID);
    }

    @Override
    public void getDirection() {
        posX = 0;
        posY = 0;
    }

    @Override
    public void applyRotaion() {

    }

	@Override
	public int getVelY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVelY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVelX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVelX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visionStart(Graphics g, double startX, double startY, List<Line> sceneLines, List<Line> scanLines, List<Line> agentLines)
	{

	}


}
