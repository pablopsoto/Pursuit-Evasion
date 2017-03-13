package main.display;

import main.game.Game;
import main.game.logic.GameObject;
import main.game.obstacle.IrregularObstacle;
import main.mapEditor.EditorActionListener;
import main.mapEditor.MapEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jeroen on 10/03/2017.
 */
public class MapPanel extends JPanel
{
    Game game;
    MapEditor editor;
    JButton polygonButton = new JButton("Polygon editor");
    JButton addPolygon = new JButton("Add polygon");
        public MapPanel(Game game){
            this.add(polygonButton);
            this.add(addPolygon);
            addPolygon.setEnabled(false);
            polygonButton.addActionListener(new EditorActionListener());
            addPolygon.addActionListener(new AddActionListener(game));
            this.game = game;
            editor = new MapEditor(game);

        }
    private class EditorActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

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
            game.getHandler().addObject(new IrregularObstacle(editor.getxPoints().get(0),editor.getyPoints().get(0),editor.getxPoints().size(),game.getHandler(),editor.getxPoints(),editor.getyPoints())
            );
            editor.setNull();
            addPolygon.setEnabled(false);
            polygonButton.setEnabled(true);
        }
    }


}
