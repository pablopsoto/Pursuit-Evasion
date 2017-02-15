package main.display;

import main.display.game.GameFrame;
import main.game.Game;
import main.game.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jeroen on 2/8/17.
 */
public class SelectPanel extends JPanel
{
    private JFrame selectFrame;

    private JButton startButton;
    private JLabel setWidthLabel;
    private JLabel setHeightLabel;

    private JTextField widthTextField;
    private JTextField heightTextField;


    private Table table;
    public SelectPanel(MainFrame mainFrame) {

        table = new Table();
        selectFrame = mainFrame;

        startButton = new JButton("Create game!");
        startButton.addActionListener(new StartButton());

        setWidthLabel = new JLabel("Width: " );
        setHeightLabel = new JLabel("Height: ");

        System.out.println("Table default : " + Table.DEFAULT_BOARD_HEIGHT);
        widthTextField = new JTextField(""+ Table.DEFAULT_BOARD_WIDTH);
        heightTextField= new JTextField(""+Table.DEFAULT_BOARD_HEIGHT);



        this.setPreferredSize(new Dimension(370, 125));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 0;
        c.gridy = 0;
        this.add(setWidthLabel,c);

        c.gridx = 1;
        c.gridy = 0;
        this.add(widthTextField,c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(setHeightLabel,c);

        c.gridx = 1;
        c.gridy = 1;
        this.add(heightTextField,c);

        c.gridx = 1;
        c.gridy = 2;
        this.add(startButton,c);


    }

    private class StartButton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            table.setCurrentWidth(Integer.parseInt(widthTextField.getText()));
            table.setCurrentHeight(Integer.parseInt(heightTextField.getText()));

            System.out.println("Starts game");
            new GameFrame(table.createNewGame());
            selectFrame.dispose();;
        }
    }
}