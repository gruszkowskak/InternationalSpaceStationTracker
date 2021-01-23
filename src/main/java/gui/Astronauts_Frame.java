package gui;

import url.AstronautImagesURL;
import url.PeopleInSpaceNow;
import url.PeopleInSpaceNowURL;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static gui.ImageSave.saveImage;

public class Astronauts_Frame extends JFrame {

    public Astronauts_Frame() {
        super("Astronauts");
        setLayout(new FlowLayout());
        Image icon = new javax.swing.ImageIcon("src/main/resources/nasa_logo.png").getImage();
        setIconImage(icon);
        getContentPane().setBackground(new java.awt.Color(204, 230, 255));
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3,3));
        p.setBackground(new java.awt.Color(204, 230, 255));


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
            JLabel label = new JLabel(name, SwingConstants.CENTER);
            p.add(label);
            File tmpDir = new File("src/main/resources/"+name+".jpg");
            boolean exists = tmpDir.exists();
            if(exists){
                ImageIcon imageIcon = new ImageIcon("src/main/resources/"+name+".jpg");
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(200, 190,  java.awt.Image.SCALE_SMOOTH); // scale image
                imageIcon = new ImageIcon(newimg);
                p.add(new JLabel(imageIcon));
            }
            else{ //here new image is downloaded
                String imageUrl = AstronautImagesURL.search(name).getContentUrl();
                String destinationFile = "src/main/resources/"+name+".jpg";

                try {
                    saveImage(imageUrl, destinationFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageIcon imageIcon = new ImageIcon(destinationFile);
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(200, 190,  java.awt.Image.SCALE_SMOOTH); // scale image
                imageIcon = new ImageIcon(newimg);
                p.add(new JLabel(imageIcon));
            }
        }

        add(p);

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
        setJMenuBar(menuBar);
        setSize(1414,660);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}