package main.display;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {



    public MainFrame(){
        super("Main Frame");

        this.setSize(new Dimension(800, 600));
        this.add(mainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel mainPanel(){

        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);

        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));

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

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.LINE_AXIS));
        bPanel.setBackground(Color.BLACK);
        bPanel.add(runButton);
        bPanel.add(editButton);


        p.add(blackPanel());
        p.add(bPanel);
        p.add(blackPanel());

        return p;
    }

    JPanel blackPanel(){
        JPanel p =new JPanel();
        p.setBackground(Color.BLACK);
        return p;
    }

}
