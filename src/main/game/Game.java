package main.game;


import main.display.Window;
import main.game.agent.Agent;
import main.game.agent.Evader;
import main.game.agent.Pursuer;
import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.logic.KeyIn;
import main.game.obstacle.IrregularObstacle;
import main.game.obstacle.Obstacle;


import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends Canvas implements Runnable{

    public final int WIDTH = 1200;
    public final int HEIGHT = WIDTH/ 12 * 7;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private int borderSize =20;
    
    private Graphics g;
    private BufferStrategy bs;
    private Random random;
    private AtomicInteger ID_GENERATOR = new AtomicInteger(100);

    public Agent getAgent()
    {
        return agent;
    }

     public Agent agent;
     public Agent agent2;

    ExecutorService ex = Executors.newWorkStealingPool();



    public Game(){
        handler = new Handler();

//        handler.addObject(new Obstacle(150,150,4,50,handler));
        
//        this.addKeyListener(new KeyIn(this));
        new Window(WIDTH, HEIGHT, "Pursuit-Evasion", this);

        random = new Random();

        for (int i = 0; i <0; i++){
            handler.addObject(new Obstacle(random.nextInt(WIDTH)/2, random.nextInt(HEIGHT)/2,4,30, handler,ID_GENERATOR.getAndIncrement()));
        }
       /* agent = new Evader(WIDTH/2, HEIGHT/2, 36, handler,this,ID_GENERATOR.getAndIncrement());
        agent2 = new Pursuer(WIDTH/8, HEIGHT/2, 36, handler,this,ID_GENERATOR.getAndIncrement());
        handler.addObject(new Pursuer(WIDTH/8, HEIGHT/4, 36, handler,this,ID_GENERATOR.getAndIncrement()));
        handler.addObject(new Pursuer(WIDTH/3, HEIGHT/4, 36, handler,this,ID_GENERATOR.getAndIncrement()));
        handler.addObject( new Evader(WIDTH/4, HEIGHT/5, 36, handler,this,ID_GENERATOR.getAndIncrement()));
        handler.addObject( new Evader(WIDTH/7, HEIGHT/5, 36, handler,this,ID_GENERATOR.getAndIncrement()));
        handler.addObject(agent);
        handler.addObject(agent2);
*/
//        handler.addObject(new Obstacle(500,HEIGHT/2-10, random.nextInt(8) +3,30, handler,ID_GENERATOR.getAndIncrement()));



        addSides();


//        gc = window.getGraphics();
    }



    public void addSides(){
        Integer[] arrayX= {0,borderSize,borderSize,0};
        ArrayList<Integer> leftXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));

        Integer[] arrayY= {0,0,getHeight(),getHeight()};
        ArrayList<Integer> leftYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,leftXpoints,leftYpoints,ID_GENERATOR.getAndIncrement()));

        arrayX = new Integer[]{0, getWidth(), getWidth(), 0};
        arrayY= new Integer[]{0, 0, borderSize, borderSize};

        ArrayList<Integer> upXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));
        ArrayList<Integer> upYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,upXpoints,upYpoints,ID_GENERATOR.getAndIncrement()));


        arrayX = new Integer[]{getWidth(),getWidth()-borderSize,getWidth()-borderSize,getWidth()};
        arrayY= new Integer[] {0,0,getHeight(),getHeight()};

        ArrayList<Integer> rightXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));
        ArrayList<Integer> rightYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,rightXpoints,rightYpoints,ID_GENERATOR.getAndIncrement()));

        arrayX = new Integer[]{getWidth(),getWidth(),0,0};
        arrayY= new Integer[] {getHeight(),getHeight()-borderSize,getHeight()-borderSize,getHeight()};

        ArrayList<Integer> downXpoints = new ArrayList<Integer>(Arrays.asList(arrayX));
        ArrayList<Integer> downYpoints = new ArrayList<Integer>(Arrays.asList(arrayY));

        handler.addObject(new IrregularObstacle(0,0,4,handler,downXpoints,downYpoints,ID_GENERATOR.getAndIncrement()));

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
                render();  
                delta--;
            }

//            if(running){
//                render();  
//            }

            frames++;

            if(System.currentTimeMillis()-timer >1000){
                timer += 1000;
                //System.out.printf("FPS: %d \n", frames);
                frames = 0;
            }

        }

        stop();
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop(){
        try{
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void tick() {
        handler.tick();
    }

    public void render()
    {    	
        bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(6);
            return;
        }

        g = bs.getDrawGraphics();

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

    public AtomicInteger getIDGenerator()
    {
        return ID_GENERATOR;
    }
    public void setHandler(Handler h){
        this.handler = h;
    }
}
