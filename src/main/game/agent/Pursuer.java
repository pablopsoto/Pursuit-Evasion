package main.game.agent;

import main.game.Game;
import main.game.logic.Handler;
import main.game.logic.ID;

import java.awt.*;

/**
 * Created by Jeroen on 22/03/2017.
 */
public class Pursuer extends Agent
{
    public Pursuer(int x, int y, int sides, Handler handler, Game game, int objectID)
    {
        super(x, y, sides, handler, ID.PURSUER, game, objectID);
        super.setColor(Color.GREEN);
    }
}

