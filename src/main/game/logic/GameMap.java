package main.game.logic;


import java.util.ArrayList;

/**
 * Created by josevelasquez on 3/16/17.
 */
public class GameMap {

    private String name;
    private ArrayList<GameObject> gameObjects;

    public GameMap(String name, ArrayList<GameObject> gameObjects){
        this.name = name;
        this.gameObjects = gameObjects;
    }

    public String toString(){
        return name;
    }

    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

}
