
import main.game.Game;
import main.game.agent.Agent;
import main.game.obstacle.Obstacle;
import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.util.Location;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Test {

    
    public void testTester(){
        System.out.print("This is a test");
    }

    public void testLocation(){
        Location a = new Location(300, 350);
        Location b = new Location(100, 400);
        float d = Location.distance(a,b);
        System.out.print(d);
    }

//    @org.junit.Test
//    public void testSave(){
//        Handler h = new Handler();
//        h.addObject(new Agent(100, 200, 4, h));
//        h.addObject(new Obstacle(450, 1, 8, 30, h));
//
//        FileOutputStream fos;
//        ObjectOutputStream oos;
//        FileInputStream fis;
//        ObjectInputStream ois;
//
//        Handler handler;
//        try {
//            fos = new FileOutputStream("t.tmp");
//            oos = new ObjectOutputStream(fos);
//
//
//            oos.writeObject(h);
//            oos.close();
//
//            fis = new FileInputStream("t.tmp");
//            ois = new ObjectInputStream(fis);
//
//            handler = (Handler) ois.readObject();
//
//            System.out.println(handler.toString());
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

/*    public void testCollision(){
        Handler handler = new Handler();
//        GameObject agent = new Agent(50,300,3, handler,new Game game);
        GameObject obstacle = new Obstacle(75, 300, 4,30, handler);

//        handler.addObject(agent);
        handler.addObject(obstacle);

        if(agent.collided(handler)){
            System.out.println("Collided");
        } else {
            System.out.println("Didn't collide");
        }
    }*/

    public void testCanvas(){
        new Game();
    }

}
