package gui;

import url.ISSPassTimes;
import url.ISSPassTimesURL;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PassTime_Frame extends JFrame {

    public PassTime_Frame() {
        super("Next pass time");
        Image icon = new javax.swing.ImageIcon("src/main/resources/nasa_logo.png").getImage();
        setIconImage(icon);


        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("src/main/resources/City_poland.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str=null;
        //HashMap cities = new HashMap();
        ArrayList<String> lines = new ArrayList<String>();
        while(true){
            try {
                if (!((str = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String string = str.substring(0,23)+" ("+str.substring(24,31)+", " +str.substring(39,46)+")";
            lines.add(string);
        }
        String[] linesArray = lines.toArray(new String[lines.size()]);


        JComboBox<String> comboCities = new JComboBox<String>();
        // add items to the combo box
        for (int i=0; i<linesArray.length; i++){
            comboCities.addItem(linesArray[i]);
        }
        comboCities.setEditable(true);
        AutoCompletion.enable(comboCities);
        //add(comboLanguage);
        JPanel wrapper = new JPanel();
        wrapper.add( comboCities);
        JButton b=new JButton("Search");
        wrapper.add(b);
        add( wrapper );
        JTextField latitudeTextField = new JTextField("Next pass in chosen city. ");
        latitudeTextField.setPreferredSize(new Dimension(300,24));
        wrapper.add(latitudeTextField);
        //wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));
        //wrapper.setLayout(new GridLayout(3,3));
        wrapper.setBackground(new java.awt.Color(204, 230, 255));
        add(wrapper);
        b.addActionListener((event)-> {
            String selectedCity = (String) comboCities.getSelectedItem();
            System.out.println(selectedCity);
            String name = selectedCity.substring(0,23);
            Float latitude = Float.parseFloat(selectedCity.substring(26,27))+Float.parseFloat(selectedCity.substring(29,30))/60;
            Float longitude = Float.parseFloat(selectedCity.substring(35,36))+Float.parseFloat(selectedCity.substring(38,39))/60;
            ISSPassTimesURL isspasstimesURL= new ISSPassTimesURL();
            ISSPassTimes issPassTimes = null; //Warsaw

            try {
                issPassTimes = isspasstimesURL.RequestISSPassTimes(latitude, longitude);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //if not null, bo inaczej się ???
            //take the first pass
            String time= String.valueOf(issPassTimes.getResponse().get(0));
            //convert date to human readable format
            Long time_sub= Long.parseLong(time.substring(32,42));
            Date date = new java.util.Date(time_sub*1000L);
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1"));
            String formattedDate = sdf.format(date);
            System.out.println(formattedDate);
            latitudeTextField.setText("Next pass at : "+ formattedDate);
        });
//        frame.setLayout(new GridLayout(2,3));
//        frame.setSize(300,300);
//        frame.setVisible(true);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu isstrackerMenu = new JMenu("ISS Tracker");
        JMenuItem isstrackerItem = new JMenuItem("Go to...");
        isstrackerItem.addActionListener((event)-> {
            try {
                new MainFrame("Map.jpg");
                setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        });
        isstrackerMenu.add(isstrackerItem);
        menuBar.add(isstrackerMenu);
        JMenu astrounautsMenu = new JMenu("Astrounauts");
        JMenuItem astrounatsItem = new JMenuItem("Go to...");
        astrounatsItem.addActionListener((event)-> {
            new Astronauts_Frame();
            setVisible(true);
            this.setVisible(false);
        });
        astrounautsMenu.add(astrounatsItem);
        menuBar.add(astrounautsMenu);
        setJMenuBar(menuBar);
        setSize(1414,660);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}