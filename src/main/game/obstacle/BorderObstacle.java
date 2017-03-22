package main.game.obstacle;

import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.logic.ID;
import main.vision.Line;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 13/03/2017.
 */
public class BorderObstacle extends GameObject{
        public BorderObstacle(int x, int y, int sides, Handler handler, ArrayList<Integer> xPoints, ArrayList<Integer> yPoints,int objectID){
            super(x,y,sides, ID.OBSTACLE,handler,xPoints,yPoints,objectID);
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
