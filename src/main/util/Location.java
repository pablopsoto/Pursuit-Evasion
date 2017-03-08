package main.util;


public class Location {

    public float x, y;

    public Location(float x, float y){
        this.x = x;
        this.y = y;
    }

    public static float distance(Location a, Location b){
        float dx = a.x - b.x;
        float dy = a.y - b.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

}
