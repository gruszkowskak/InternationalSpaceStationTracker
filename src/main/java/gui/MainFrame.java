package gui;


import url.ISSPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private Color color = Color.RED;

    public MainFrame(String filename) {
        super("ISS tracker");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new java.awt.Color(204, 230, 255));
        Image icon = new javax.swing.ImageIcon("src/main/resources/nasa_logo.png").getImage();
        setIconImage(icon);

        JPanel worldMapPanel = new WorldMapPanel(filename);
        add(worldMapPanel);
        JTextField latitudeTextField = new JTextField("Latitude : "+"Longitude : ");
        latitudeTextField.setPreferredSize(new Dimension(100,24));
        add(latitudeTextField,BorderLayout.SOUTH);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu mapMenu = new JMenu("Maps");
        JMenuItem menuItem = new JMenuItem("Night Map");
        menuItem.addActionListener((event) -> {
            try {
                new MainFrame("NightMap.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(true);
            this.setVisible(false);
        });
        mapMenu.add(menuItem);
        JMenuItem menuItem2 = new JMenuItem("Map 1");
        menuItem2.addActionListener((event) ->  {
            try {
                new MainFrame("WholeMap.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(true);
            this.setVisible(false);
        });
        mapMenu.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Map 2");
        menuItem3.addActionListener((event) ->  {
            try {
                new MainFrame("Map.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(true);
            this.setVisible(false);
        });
        mapMenu.add(menuItem3);
        menuBar.add(mapMenu);
        JMenu colorMenu = new JMenu("Point Color");
        JMenuItem redColor = new JMenuItem("RED");
        redColor.addActionListener((event)->{
            this.color = Color.RED;
        });
        colorMenu.add(redColor);
        JMenuItem blueColor = new JMenuItem("BLUE");
        blueColor.addActionListener((event)->{
            this.color = Color.BLUE;
        });
        colorMenu.add(blueColor);
        JMenuItem greenColor = new JMenuItem("GREEN");
        greenColor.addActionListener((event)->{
            this.color = Color.GREEN;
        });
        colorMenu.add(greenColor);
        menuBar.add(colorMenu);
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
        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("Go to...");
        aboutItem.addActionListener((event)-> {
            new AboutFrame();
            setVisible(true);
            this.setVisible(false);
        });
        aboutMenu.add(aboutItem);
        menuBar.add(aboutMenu);
        JMenu restart = new JMenu("Restart");
        JMenuItem restart_item = new JMenuItem("Restart");
        restart_item.addActionListener((event)-> {
            try {
                new StartFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(true);
            this.setVisible(false);
        });
        restart.add(restart_item);
        menuBar.add(restart);
        setJMenuBar(menuBar);

        //draws points every 5 seconds
        Timer timer = new Timer(5000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ((WorldMapPanel) worldMapPanel).drawPoint(color);
                ISSPosition issPosition = ((WorldMapPanel) worldMapPanel).getIssPosition();
                latitudeTextField.setText("Latitude : "+ issPosition.getLatitude()+",  Longitude : " +issPosition.getLongitude());

            }
        });
        timer.start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}

