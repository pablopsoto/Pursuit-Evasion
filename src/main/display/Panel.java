package main.display;

import javafx.beans.property.DoubleProperty;
import main.game.Game;
import main.game.agent.Agent;
import main.game.agent.Evader;
import main.game.agent.Pursuer;
import main.game.logic.Handler;
import main.game.obstacle.IrregularObstacle;
import main.game.obstacle.Obstacle;
import main.mapEditor.MapEditor;
import main.mapEditor.regularEditor;
import main.vision.Algorithm;
import main.vision.Settings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by Jeroen on 13/03/2017.
 */
public class Panel extends JPanel
{
    boolean polygonEditor = false;
    boolean pursuer = false;
    boolean evader = false;
    boolean regularPolygon = false;


    Random r = new Random();
    Handler newHandler = null;
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




    //-------------------------------------------------------------------------
    private  Game game;
    private JTextField sidesTextField = new JTextField("7");
    private JTextField sizeTextField = new JTextField("20");
    JLabel sidesLabel = new JLabel("Sides = ");
    JLabel sizeLabel = new JLabel("Size = ");

    JSlider rangeSlider = new JSlider(0,200);

    MapEditor editor;

    JButton RegularPolygonEditor = new JButton("Regular polgyon editor");

    public Panel(Game game){
        this.game =game;
//        editor = new regularEditor(game);
        this.setPreferredSize(new Dimension(200,500));
//        this.add(new JLabel("Add regular polygon"),BorderLayout.NORTH);

        GridLayout layout = new GridLayout(11,1);

        this.add(sidesLabel,layout);
        this.add(sidesTextField,layout);
        this.add(sizeLabel,layout);
        this.add(sizeTextField,layout);
        this.add(RegularPolygonEditor,layout);



        RegularPolygonEditor.addActionListener(new addRegular());

        // - - - - - - - - - - - - - - - - -- - - - -

        this.add(polygonButton);
        this.add(addPursuer);
        this.add(addEvador);
        this.add(add);
        //save and load
        this.add(save);
        this.add(load);
        //slider
        this.add(rangeSlider);
        //Create the label table
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 0 ), new JLabel("No range") );
        labelTable.put( new Integer( 200 ), new JLabel("Large range") );
        rangeSlider.setLabelTable( labelTable );
        rangeSlider.setPaintLabels(true);


        stop.setEnabled(false);
        save.addActionListener(new SaveActionListener());
        load.addActionListener(new LoadActionListener());
        addPursuer.addActionListener(new PursuerActionListener());
        addEvador.addActionListener(new EvaderActionListener());
        rangeSlider.addChangeListener(new RangeListener());
        this.add(start);
        this.add(stop);
        start.addActionListener(new StartActionListener());
        stop.addActionListener(new StopActionListener());

        add.setEnabled(false);
        polygonButton.addActionListener(new EditorActionListener());
        add.addActionListener(new addAction());

        this.game = game;

        editor = new MapEditor(game);

    }



    private class AddActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Add regular Polygon Pressed");
            if(editor.getxPoints().get(0)>0 && editor.getxPoints().get(0)<game.getWidth()&&editor.getyPoints().get(0)>0 && editor.getyPoints().get(0)<game.getHeight()){
                game.getHandler().addObject(new Obstacle(editor.getxPoints().get(0),editor.getyPoints().get(0), Integer.parseInt(sidesTextField.getText()),Integer.parseInt(sizeTextField.getText()), game.getHandler(),game.getIDGenerator().getAndIncrement()));}
            else {
                System.out.println("NO MOUSE INPUT");}


            polygonButton.setEnabled(false);
            addEvador.setEnabled(false);
            addPursuer.setEnabled(false);
            RegularPolygonEditor.setEnabled(false);

        }
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
            }else if(regularPolygon){
                System.out.println("Add regular Polygon Pressed");
                if(editor.getyPoints().get(0)>0 && editor.getxPoints().get(0)<game.getWidth()&&editor.getyPoints().get(0)>0 && editor.getyPoints().get(0)<game.getHeight()){
                    game.getHandler().addObject(new Obstacle(editor.getxPoints().get(0),editor.getyPoints().get(0), Integer.parseInt(sidesTextField.getText()),Integer.parseInt(sizeTextField.getText()), game.getHandler(),game.getIDGenerator().getAndIncrement()));}
                else {
                    System.out.println("NO MOUSE INPUT");}
            }
            editor.setNull();
            polygonEditor = false;
            evader = false;
            pursuer = false;
            regularPolygon=false;
            add.setEnabled(false);
            polygonButton.setEnabled(true);
            addEvador.setEnabled(true);
            addPursuer.setEnabled(true);
            RegularPolygonEditor.setEnabled(true);

        }
    }


    private class StartActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Start Button Pressed");
//            game.start();
            game.getHandler().startMovement();
            start.setEnabled(false);
            stop.setEnabled(true);
        }
    }

    private class StopActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        /*    System.out.println("Stop Button Pressed");
            game.stop();*/
            game.getHandler().stopMovement();
            start.setEnabled(true);
            stop.setEnabled(false);
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
            RegularPolygonEditor.setEnabled(false);
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
            RegularPolygonEditor.setEnabled(false);
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
            while(game.isRunning()){
               game.stop();
            }
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
            game.start();
        }
    }

    private class LoadActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Load button Pressed");
            while(game.isRunning()){
                game.stop();
            }
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

            game.start();
            game.getHandler().stopMovement();
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
            RegularPolygonEditor.setEnabled(false);
            add.setEnabled(true);
            if(!polygonEditor){
                polygonEditor=true;
            }else polygonEditor = false;

        }
    }

    private class addRegular implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            editor.setNull();
            System.out.println("Button Pressed");
            polygonButton.setEnabled(false);
            addEvador.setEnabled(false);
            addPursuer.setEnabled(false);
            RegularPolygonEditor.setEnabled(false);
            add.setEnabled(true);
            if(!regularPolygon){
                regularPolygon=true;
            }else regularPolygon = false;
        }
    }

    private class RangeListener implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e)
        {
            JSlider source = (JSlider)e.getSource();
            Settings.get().setScanLineLength(source.getValue());
        }
    }
}
