package gui;

import url.PeopleInSpaceNow;
import url.PeopleInSpaceNowURL;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Astronauts_Frame extends JFrame {

    public Astronauts_Frame() {
        super("Astronauts");
        setLayout(new FlowLayout());


        PeopleInSpaceNowURL peopleInSpaceNowURL= new PeopleInSpaceNowURL();

        PeopleInSpaceNow peopleInSpaceNow = null;
        try {
            peopleInSpaceNow = peopleInSpaceNowURL.RequestPeopleInSpaceNow();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList people = peopleInSpaceNow.getPeople();

        Pattern pattern = Pattern.compile("'(.*?)'");
        for (int i=0; i<people.size(); i++) {
            String mydata = people.get(i).toString();
            //find name of astronaut
            Matcher matcher = pattern.matcher(mydata);
            String name="a";
            if (matcher.find())
            {
                name = matcher.group(1);
            }
            add(new JLabel(i+1+"." + name+"\n"));
        }

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
        menuBar.add(aboutMenu);JMenu restart = new JMenu("Restart");
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