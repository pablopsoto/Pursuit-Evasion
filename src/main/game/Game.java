package main.game;

import main.display.Window;
import main.game.agent.Agent;
import main.game.logic.Handler;
import main.game.logic.KeyIn;
import main.game.obstacle.Obstacle;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public final int WIDTH = 640;
    public final int HEIGHT = WIDTH/ 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyIn(handler));
        new Window(WIDTH, HEIGHT, "Pursuit-Evasion", this);

        Random random = new Random();

        /*for (int i = 0; i <10; i++){
            handler.addObject(new Agent(random.nextInt(WIDTH)/2, random.nextInt(HEIGHT)/2, random.nextInt(8), handler));
        }*/

        handler.addObject(new Agent(WIDTH/2 -100, HEIGHT/2, random.nextInt(8) +3,handler));
        handler.addObject(new Obstacle(500,HEIGHT/2-10, random.nextInt(8) +3, handler));
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

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(6);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }
}
