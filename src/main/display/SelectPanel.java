package main.display;

//import display.game.GameFrame;

import main.display.game.GameFrame23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jeroen on 2/8/17.
 */
public class SelectPanel extends JPanel {
    private JFrame selectFrame;

    private JButton startButton;

    public SelectPanel(MainFrame mainFrame) {
        selectFrame = mainFrame;

        startButton = new JButton("Create game!");
        startButton.addActionListener(new StartButton());

        this.setPreferredSize(new Dimension(370, 125));
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(startButton, c);

    }

    private class StartButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Starts game");
            new GameFrame23();
            selectFrame.dispose();;
        }
    }
}
