package main.util;


import main.game.logic.GameMap;

import java.io.*;

/**
 * Created by josevelasquez on 3/16/17.
 */
public class MapSaver {

    public static MapList list;

    public static void saveMap(GameMap map){

        getMapList();

        try{

            FileOutputStream fos = new FileOutputStream("MapList.txt");



        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void getMapList() {

        FileInputStream fis;

        try {
            fis = new FileInputStream(new File("MapList.txt"));
            ObjectInputStream input = new ObjectInputStream(fis);

            //list =  (MapList) input.read();

        } catch (FileNotFoundException e){
            list = new MapList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
