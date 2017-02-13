package main.display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jeroen on 2/8/17.
 */
public class MainFrame extends JFrame
{

    private JPanel selectPanel;

    public MainFrame()
    {
        //create game elements and set up panels
        selectPanel = new SelectPanel(this);

        //set main gui settings
        setTitle("Persue the dream!");
        setLayout(new BorderLayout());
        getContentPane().add(selectPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}