package main.game.agent;

import main.game.Game;
import main.game.logic.Handler;
import main.game.logic.ID;

/**
 * Created by Jeroen on 22/03/2017.
 */
public class Evader extends Agent
{
    public Evader(int x, int y, int sides, Handler handler, Game game, int objectID)
    {
        super(x, y, sides, handler, ID.EVADER, game, objectID);
    }
}
