package main.game.logic;


import main.game.agent.Agent;
import main.vision.Algorithm;
import main.vision.Line;
import main.vision.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Handler implements java.io.Serializable{

    private ArrayList<GameObject> objects = new ArrayList<>();
    protected List<Line> sceneLines = new ArrayList<>();
    protected List<Line> scanLines = new ArrayList<>();
    protected List<Line> agentLines = new ArrayList<>();
    protected List<Agent> agents = new ArrayList<>();

    boolean movement = false;

    public synchronized void tick()
    {
        for (GameObject o : getObjects())
        {
            if (o instanceof Agent)
                o.tick();
        }
    }

    public synchronized void render(Graphics g)
    {
        agentLines.clear();
        for (GameObject o : getObjects())
        {
            o.render(g);

            if (o instanceof Agent)
            {
                agents.add((Agent) o);
            }


        }
        for (Agent a : agents)
        {
            a.visionStart(g, a.getX(), a.getY(), sceneLines, scanLines, agentLines);
        }
        agents.clear();

//      startX+=0.1;
    }

    public synchronized void addObject(GameObject object)
    {
        getObjects().add(object);
    }

    public synchronized void removeObject(GameObject object)
    {
        getObjects().remove(object);
    }

    public synchronized List<Line> getSceneLines()
    {
        return sceneLines;
    }

    public synchronized List<Line> getScanLines()
    {
        return scanLines;
    }

    public synchronized void setScanLines(List<Line> scanLines)
    {
        this.scanLines = scanLines;
    }

    public ArrayList<GameObject> getObjects()
    {
        return objects;
    }

    public void setObjects(ArrayList<GameObject> objects)
    {
        this.objects = objects;
    }

    public List<Line> getAgentLines()
    {
        return agentLines;
    }

    public void delete(int objectID) {
        for(int i =0; i<objects.size();i++){
            if(objects.get(i).getObjectID()==objectID)objects.remove(i);

        }
    }

    public void switchMovement(){
        movement = !movement;
    }

    public boolean getMovement()
    {
        return movement;
    }
}
