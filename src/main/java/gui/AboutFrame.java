package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AboutFrame extends JFrame {
    public AboutFrame(){
        super("About");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new java.awt.Color(204, 230, 255));
        Image icon = new javax.swing.ImageIcon("src/main/resources/nasa_logo.png").getImage();
        setIconImage(icon);

        JPanel aboutPanel = new AboutPanel();
        add(aboutPanel);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu isstrackerMenu = new JMenu("ISS Tracker");
        JMenuItem isstrackerItem = new JMenuItem("Go to...");
        isstrackerItem.addActionListener((event)-> {
            try {
                new MainFrame("Map.jpg");
                setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        });
        isstrackerMenu.add(isstrackerItem);
        menuBar.add(isstrackerMenu);
        JMenu astronautsMenu = new JMenu("Astronauts");
        JMenuItem astronautsItem = new JMenuItem("Go to...");
        astronautsItem.addActionListener((event)-> {
            new Astronauts_Frame();
            setVisible(true);
            this.setVisible(false);
        });
        astronautsMenu.add(astronautsItem);
        menuBar.add(astronautsMenu);
        JMenu pass_timeMenu = new JMenu("Pass Time");
        JMenuItem pass_timeItem = new JMenuItem("Go to...");
        pass_timeItem.addActionListener((event)-> {
            new PassTime_Frame();
            setVisible(true);
            this.setVisible(false);
        });
        pass_timeMenu.add(pass_timeItem);
        menuBar.add(pass_timeMenu);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1214,689);
        setVisible(true);
    }
}
