package main.display;

import main.game.Game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {



    public GameFrame(Game game){
        super("Game Frame");

        this.setSize(new Dimension(800, 600));
        this.add(mainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel mainPanel(){

        JPanel p = new JPanel();
        p.setBackground(Color.black);

        p.setLayout(new GridLayout(1,2));

        JButton runButton = new JButton("Run Environment");
        runButton.addActionListener(e -> {
            System.out.println("Clicked Running Button");
            //TODO: This button should open the recently edited environment or a default environment
        });
        runButton.setEnabled(false);

        JButton editButton = new JButton("Edit the Environment");
        editButton.addActionListener(e -> {
            System.out.println("Clicked Editing Button");
            runButton.setEnabled(true);

            //TODO: this button should open the EditingPanel
        });


        p.add(runButton);
        p.add(editButton);



        return p;
    }

    JPanel blackPanel(){
        JPanel p =new JPanel();
        p.setBackground(Color.black);
        return p;
    }


}
