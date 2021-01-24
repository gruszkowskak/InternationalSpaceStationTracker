package gui;

import url.ISSPassTimes;
import url.ISSPassTimesURL;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassTime_Frame extends JFrame {

    public PassTime_Frame() {
        super("Next pass time");
        setLayout(new FlowLayout());

        ISSPassTimesURL isspasstimesURL= new ISSPassTimesURL();
        ISSPassTimes issPassTimes = null; //Warsaw
        try {
            issPassTimes = isspasstimesURL.RequestISSPassTimes(52.216f, 21);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //take the first pass
        String time= String.valueOf(issPassTimes.getResponse().get(0));
        //convert date to human readable format
        Long time_sub= Long.parseLong(time.substring(32,42));
        Date date = new java.util.Date(time_sub*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1"));
        String formattedDate = sdf.format(date);
        add(new JLabel("Next pass date is : "+formattedDate));

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
        setSize(1214,689);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}