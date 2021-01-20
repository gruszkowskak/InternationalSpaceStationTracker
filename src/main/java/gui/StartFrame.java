package gui;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    public StartFrame(){
        super("ISS tracker");
        setLayout(new BorderLayout());


        JPanel startPanel = new StartPanel(this);
        add(startPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
