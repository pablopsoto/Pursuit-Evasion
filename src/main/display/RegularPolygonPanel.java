package main.display;

import main.game.Game;
import main.game.obstacle.IrregularObstacle;
import main.game.obstacle.Obstacle;
import main.mapEditor.regularEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jeroen on 13/03/2017.
 */
public class RegularPolygonPanel extends JPanel
{
    private  Game game;
    private JTextField sidesTextField = new JTextField("4");
    private JTextField sizeTextField = new JTextField("10");
    JLabel sidesLabel = new JLabel("Sides = ");
    JLabel sizeLabel = new JLabel("Size = ");

    regularEditor editor;

    JButton RegularPolygonEditor = new JButton("Regular polgyon editor");
    JButton addRegularPolygon = new JButton("Add regular polgyon");
    public RegularPolygonPanel(Game game){
        this.game =game;
        editor = new regularEditor(game);
        this.setPreferredSize(new Dimension(200,500));
//        this.add(new JLabel("Add regular polygon"),BorderLayout.NORTH);

        GridLayout layout = new GridLayout(2,2);

        this.add(sidesLabel,layout);
        this.add(sidesTextField,layout);
        this.add(sizeLabel,layout);
        this.add(sizeTextField,layout);
        this.add(RegularPolygonEditor);
        this.add(addRegularPolygon);


        addRegularPolygon.setEnabled(false);

        addRegularPolygon.addActionListener(new AddActionListener());
        RegularPolygonEditor.addActionListener(new EditActionListener());

    }

    private class EditActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Add regular Polygon Pressed");
            editor.setNull();
            RegularPolygonEditor.setEnabled(false);
            addRegularPolygon.setEnabled(true);

        }
    }
    private class AddActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Add regular Polygon Pressed");
            if(editor.getX()>0 && editor.getX()<game.getWidth()&&editor.getY()>0 && editor.getY()<game.getHeight()){
            game.getHandler().addObject(new Obstacle(editor.getX(),editor.getY(), Integer.parseInt(sidesTextField.getText()),Integer.parseInt(sizeTextField.getText()), game.getHandler(),game.getIDGenerator().getAndIncrement()));}
            else {
                System.out.println("NO MOUSE INPUT");}
            RegularPolygonEditor.setEnabled(true);
            addRegularPolygon.setEnabled(false);


        }
    }

}
