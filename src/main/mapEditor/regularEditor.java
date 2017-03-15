package main.mapEditor;

import main.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Jeroen on 13/03/2017.
 */
public class regularEditor
{
    Game game;
    int x;
    int y;
    
    public regularEditor(Game game){
        this.game = game;
        game.addMouseListener(new EditorMouseListener());
    }
    public void setNull(){
        x= 0;
        y= 0;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    private class EditorMouseListener implements MouseListener
    {


        @Override
        public void mouseClicked(MouseEvent e)
        {
            x = e.getX();
            y = e.getY();
            System.out.println(x);
            System.out.println(y);

        }

        @Override
        public void mousePressed(MouseEvent e)
        {



        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

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
