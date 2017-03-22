package main.display;

import main.game.Game;
import main.game.agent.Agent;
import main.game.agent.Evader;
import main.game.agent.Pursuer;
import main.game.logic.GameObject;
import main.game.logic.Handler;
import main.game.obstacle.IrregularObstacle;
import main.mapEditor.EditorActionListener;
import main.mapEditor.MapEditor;
import main.mapEditor.regularEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

/**
 * Created by Jeroen on 10/03/2017.
 */
public class MapPanel extends JPanel
{
    Random r = new Random();
    Handler newHandler = null;
    Game game;
    MapEditor editor;
    regularEditor editorPersuerEvador;
    JButton polygonButton = new JButton("Polygon editor");
    JButton addPolygon = new JButton("Add polygon");

    JButton addPursuer = new JButton("Add pursuer");
    JButton addEvador  = new JButton("Add Evador");
    //MAKE EVADOR BUTTON BROH


    JButton start = new JButton("start");
    JButton stop = new JButton("stop");

    //save and load
    JButton save = new JButton("save");
    JButton load = new JButton("load");


        public MapPanel(Game game){
            this.add(polygonButton);
            this.add(addPolygon);
            this.add(addPursuer);
            this.add(addEvador);
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

            addPolygon.setEnabled(false);
            polygonButton.addActionListener(new EditorActionListener());
            addPolygon.addActionListener(new AddActionListener(game));

            this.game = game;
            editor = new MapEditor(game);
            editorPersuerEvador = new regularEditor(game);

            //game.render();
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
            System.out.println("Add Evader Button Pressed");
            Agent agent = new Evader(editorPersuerEvador.getX(), editorPersuerEvador.getY(), 20, game.getHandler(),game, game.getIDGenerator().getAndIncrement());
            game.getHandler().addObject(agent);

            //game.render();
        }

    }
    private class PursuerActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Add Pursuer Button Pressed");
            Agent agent = new Pursuer(editorPersuerEvador.getX(), editorPersuerEvador.getY(), 20, game.getHandler(),game, game.getIDGenerator().getAndIncrement());
            game.getHandler().addObject(agent);

            //game.render();
        }

    }
    private class SaveActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Save button Pressed");

            try{
            // Serialize data object to a file
                FileOutputStream ObjectsFile = new FileOutputStream("AllObjects.ser");
                ObjectOutputStream out = new ObjectOutputStream(ObjectsFile);
                out.writeObject(game.getHandler());
                out.close();
                ObjectsFile.close();
                System.out.printf("Serialized data is saved in AllObjects.ser");
            }catch(IOException i) {
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

            try {
                FileInputStream fileIn = new FileInputStream("AllObjects.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                newHandler = (Handler) in.readObject();
                in.close();
                fileIn.close();
                game.setHandler(newHandler);

            }catch(IOException i) {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c) {
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
            addPolygon.setEnabled(true);

        }
    }
    private class AddActionListener implements ActionListener
    {
        Game game;
        private AddActionListener(Game game){
            this.game=game;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Add Polygon Pressed");
            game.getHandler().addObject(new IrregularObstacle(editor.getxPoints().get(0),editor.getyPoints().get(0),editor.getxPoints().size(),game.getHandler(),editor.getxPoints(),editor.getyPoints(),game.getIDGenerator().getAndIncrement())
            );

            addPolygon.setEnabled(false);
            polygonButton.setEnabled(true);

            game.render();
        }
    }


}
