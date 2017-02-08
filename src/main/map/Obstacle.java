package main.map;

import main.logic.Location;

import java.awt.*;


public interface Obstacle {

    Location getLocation();
    Rectangle getBoundaries();

}
