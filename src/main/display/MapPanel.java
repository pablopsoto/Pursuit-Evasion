package main.display;

import main.game.Game;
import main.game.agent.Agent;
import main.game.agent.Evader;
import main.game.agent.Pursuer;
import main.game.logic.Handler;
import main.game.obstacle.IrregularObstacle;
import main.mapEditor.MapEditor;
import main.mapEditor.regularEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

/**
 * Created by Jeroen on 10/03/2017.
 */
public class MapPanel extends JPanel
{
    //Boolean for checking when buttons are pressed
    boolean polygonEditor = false;
    boolean pursuer = false;
    boolean evader = false;


    Random r = new Random();
    Handler newHandler = null;
    Game game;
    MapEditor editor;
    regularEditor editorPersuerEvador;
    JButton polygonButton = new JButton("Irregular polygon");
    JButton add = new JButton("Add!");

    JButton addPursuer = new JButton("Pursuer");
    JButton addEvador = new JButton("Evador");
    //MAKE EVADOR BUTTON BROH


    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");

    //save and load
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");


    public MapPanel(Game game)
    {
        this.add(polygonButton);
        this.add(addPursuer);
        this.add(addEvador);
        this.add(add);
        //save and load
        this.add(save);
        this.add(load);
        save.addActionListener(new SaveActionListener());
        load.addActionListener(new LoadActionListener());
        addPursuer.addActionListener(new PursuerActionListener());
        addEvador.addActionListener(new EvaderActionListener());

        this.add(start);
        this.add(stop);
        start.addActionListener(new StartActionListener());
        stop.addActionListener(new StopActionListener());

        add.setEnabled(false);
        polygonButton.addActionListener(new EditorActionListener());
        add.addActionListener(new addAction());

        this.game = game;
        editor = new MapEditor(game);

        //game.render();
    }

    private class addAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (pursuer)
            {
                System.out.println("Add Pursuer Button Pressed");
                Agent agent = new Pursuer(editor.getxPoints().get(0), editor.getyPoints().get(0), 20, game.getHandler
                        (), game, game.getIDGenerator().getAndIncrement());
                game.getHandler().addObject(agent);
            } else if (evader)
            {
                System.out.println("Add Evader Button Pressed");
                Agent agent = new Evader(editor.getxPoints().get(0), editor.getyPoints().get(0), 20, game.getHandler
                        (), game, game.getIDGenerator().getAndIncrement());
                game.getHandler().addObject(agent);
            } else if (polygonEditor)
            {
                System.out.println("Add Polygon Pressed");
                game.getHandler().addObject(new IrregularObstacle(editor.getxPoints().get(0), editor.getyPoints().get
                        (0),
                        editor.getxPoints().size(), game.getHandler(), editor.getxPoints(), editor.getyPoints(), game
                        .getIDGenerator().getAndIncrement())
                );
            }
                add.setEnabled(false);
                polygonButton.setEnabled(true);
                addEvador.setEnabled(true);
                addPursuer.setEnabled(true);


        }
    }


    private class StartActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Start Button Pressed");
            game.start();
        }
    }

    private class StopActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Stop Button Pressed");
            game.stop();
        }
    }

    private class EvaderActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           /* System.out.println("Add Evader Button Pressed");
            Agent agent = new Evader(editorPersuerEvador.getX(), editorPersuerEvador.getY(), 20, game.getHandler(),
                    game, game.getIDGenerator().getAndIncrement());
            game.getHandler().addObject(agent);*/
            if(!evader){
                evader=true;
            }else evader = false;
            polygonButton.setEnabled(false);
            addEvador.setEnabled(false);
            addPursuer.setEnabled(false);
            add.setEnabled(true);
            //game.render();
        }

    }

    private class PursuerActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
/*            System.out.println("Add Pursuer Button Pressed");
            Agent agent = new Pursuer(editorPersuerEvador.getX(), editorPersuerEvador.getY(), 20, game.getHandler(),
                    game, game.getIDGenerator().getAndIncrement());
            game.getHandler().addObject(agent);*/
            if(!pursuer){
                pursuer=true;
            }else pursuer = false;
            polygonButton.setEnabled(false);
            addEvador.setEnabled(false);
            addPursuer.setEnabled(false);
            add.setEnabled(true);
            //game.render();
        }

    }

    private class SaveActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Save button Pressed");

            try
            {
                // Serialize data object to a file
                FileOutputStream ObjectsFile = new FileOutputStream("AllObjects.ser");
                ObjectOutputStream out = new ObjectOutputStream(ObjectsFile);
                out.writeObject(game.getHandler());
                out.close();
                ObjectsFile.close();
                System.out.printf("Serialized data is saved in AllObjects.ser");
            } catch (IOException i)
            {
                i.printStackTrace();
            }

        }
    }

    private class LoadActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Load button Pressed");

            try
            {
                FileInputStream fileIn = new FileInputStream("AllObjects.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                newHandler = (Handler) in.readObject();
                in.close();
                fileIn.close();
                game.setHandler(newHandler);

            } catch (IOException i)
            {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c)
            {
                System.out.println("Handler class not found");
                c.printStackTrace();
                return;
            }
        }
    }

    private class EditorActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            editor.setNull();
            System.out.println("Button Pressed");
            polygonButton.setEnabled(false);
            addEvador.setEnabled(false);
            addPursuer.setEnabled(false);
            add.setEnabled(true);
            if(!polygonEditor){
                polygonEditor=true;
            }else polygonEditor = false;

        }
    }

}
