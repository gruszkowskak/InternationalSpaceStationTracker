package gui;

import url.ISSPassTimes;
import url.ISSPassTimesURL;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class PassTime_Frame extends JFrame {

    public PassTime_Frame() {
        /* Here is created frame with pass time of ISS above chosen city.*/

        //frame settings
        super("Next pass time");
        Image icon = new javax.swing.ImageIcon(getClass().getResource("/nasa_logo.png")).getImage();
        setIconImage(icon);

        //read from file list of cities in Poland and save it
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/City_poland.txt"),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str=null;
        ArrayList<String> lines = new ArrayList<>();
        while(true){
            try {
                if (!((str = in.readLine()) != null)) break;
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
            String string = null;
            if (str != null) {
                string = str.substring(0,23)+" ("+str.substring(24,31)+", " +str.substring(39,46)+")";
            }
            lines.add(string);
        }
        String[] linesArray = lines.toArray(new String[lines.size()]);

        //create drop down list with cities
        JComboBox<String> comboCities = new JComboBox<>();
        for (String s : linesArray) {
            comboCities.addItem(s);
        }
        comboCities.setEditable(true);
        AutoCompletion.enable(comboCities);

        //create JPanel
        JPanel wrapper = new JPanel();
        wrapper.add( comboCities);
        //create button 'Search'
        JButton b=new JButton("Search");
        wrapper.add(b);
        //create text field, which displays time of next pass in choosen city
        JTextField passTimeTextField = new JTextField("Next pass in chosen city. ");
        passTimeTextField.setPreferredSize(new Dimension(300,24));
        wrapper.add(passTimeTextField);
        wrapper.setBackground(new java.awt.Color(204, 230, 255));
        add(wrapper);
        //add and scale image of ISS seen from Earth
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iss_from_earth.jpg"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(550, 500,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        wrapper.add(new JLabel(imageIcon));

        //for selected city (after pressing button) display next pass time
        b.addActionListener((event)-> {
            String selectedCity = (String) comboCities.getSelectedItem();
            assert selectedCity != null;
            float latitude = Float.parseFloat(selectedCity.substring(26,27))+Float.parseFloat(selectedCity.substring(29,30))/60;
            float longitude = Float.parseFloat(selectedCity.substring(35,36))+Float.parseFloat(selectedCity.substring(38,39))/60;
            ISSPassTimesURL isspasstimesURL= new ISSPassTimesURL();
            ISSPassTimes issPassTimes = null;

            try {
                issPassTimes = isspasstimesURL.RequestISSPassTimes(latitude, longitude);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            if (issPassTimes !=null){
                //take the first pass
                String time= String.valueOf(issPassTimes.getResponse().get(0));
                //convert date to human readable format
                long time_sub= Long.parseLong(time.substring(32,42));
                Date date = new java.util.Date(time_sub*1000L);
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1"));
                String formattedDate = sdf.format(date);
                passTimeTextField.setText("Next pass at : "+ formattedDate);
            }
        });

        //add menu bar
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