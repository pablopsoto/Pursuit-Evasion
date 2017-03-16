package main.util;

import main.game.logic.GameMap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josevelasquez on 3/16/17.
 */
public class MapList implements Serializable {

    public ArrayList<GameMap> list;

    public MapList(){
        list = new ArrayList<>();
    }

    public String toString(){
        return "This is the list";
    }

    public boolean equals(Object obj){
        return (this.list.equals(((MapList)obj).list));
    }

}
