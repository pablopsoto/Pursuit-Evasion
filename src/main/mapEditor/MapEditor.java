package main.mapEditor;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Jeroen on 11/03/2017.
 */
public class MapEditor extends MouseInputAdapter
{
    ArrayList<Integer> xPoints = new ArrayList<Integer>();
    ArrayList<Integer> yPoints = new ArrayList<Integer>();
    int currentX;
    int currentY;

    public MapEditor(Canvas game)
    {
        game.addMouseListener(new EditorMouseListener());
    }

    private boolean debug = false;



    public ArrayList<Integer> getxPoints()
    {
        return xPoints;
    }

    public ArrayList<Integer> getyPoints()
    {
        return yPoints;
    }

    public void setNull()
    {
        xPoints.clear();
        yPoints.clear();
    }

    private class EditorMouseListener implements MouseListener
    {


        @Override
        public void mouseClicked(MouseEvent e)
        {
            xPoints.add(e.getX());
            yPoints.add(e.getY());
            if (debug)
            {
                System.out.println(xPoints);
                System.out.println(yPoints);
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {



        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            currentX = e.getX();
            currentY = e.getY();
            System.out.println("X = " + currentX + " Y = " + currentY);

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }
}

