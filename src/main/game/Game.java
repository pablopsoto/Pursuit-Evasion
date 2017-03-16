package main.game;

import main.display.Window;
import main.game.agent.Agent;
import main.game.logic.Handler;
import main.game.logic.KeyIn;
import main.game.obstacle.IrregularObstacle;
import main.game.obstacle.Obstacle;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public final int WIDTH = 800;
    public final int HEIGHT = WIDTH/ 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private int borderSize =20;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyIn(handler));
        new Window(WIDTH, HEIGHT, "Pursuit-Evasion", this);

        Random random = new Random();

//        for (int i = 0; i <10; i++){
//            handler.addObject(new Agent(random.nextInt(WIDTH)/2, random.nextInt(HEIGHT)/2, random.nextInt(8), handler));
//        }

        handler.addObject(new Agent(WIDTH/2 -100, HEIGHT/2, 3,handler));
        handler.addObject(new Obstacle(500,HEIGHT/2-10, random.nextInt(8) +3,30, handler));

        addSides();

    }

    public void addSides(){
        Integer[] arrayX= {0,borderSize,borderSize,0};
        ArrayList<Integer> leftXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));

        Integer[] arrayY= {0,0,getHeight(),getHeight()};
        ArrayList<Integer> leftYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,leftXpoints,leftYpoints));

        arrayX = new Integer[]{0, getWidth(), getWidth(), 0};
        arrayY= new Integer[]{0, 0, borderSize, borderSize};

        ArrayList<Integer> upXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));
        ArrayList<Integer> upYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,upXpoints,upYpoints));

        arrayX = new Integer[]{getWidth(),getWidth()-borderSize,getWidth()-borderSize,getWidth()};
        arrayY= new Integer[] {0,0,getHeight(),getHeight()};

        ArrayList<Integer> rightXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));
        ArrayList<Integer> rightYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,rightXpoints,rightYpoints));

        arrayX = new Integer[]{getWidth(),getWidth(),0,0};
        arrayY= new Integer[] {getHeight(),getHeight()-borderSize,getHeight()-borderSize,getHeight()};

        ArrayList<Integer> downXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));
        ArrayList<Integer> downYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,downXpoints,downYpoints));

    }

    public void run(){

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;

            while (delta >= 1){
                tick();
                delta--;
            }

            if(running){
                render();
            }

            frames++;

            if(System.currentTimeMillis()-timer >1000){
                timer += 1000;
                //System.out.printf("FPS: %d \n", frames);
                frames = 0;
            }
        }

        stop();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void tick() {
        handler.tick();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(6);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public Handler getHandler()
    {
        return handler;
    }
}
