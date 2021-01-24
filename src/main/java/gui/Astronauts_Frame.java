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
        /* Here is created frame with astronauts, who are currently on ISS.*/

        //frame settings
        super("Astronauts");
        setLayout(new FlowLayout());
        Image icon = new javax.swing.ImageIcon(getClass().getResource("/nasa_logo.png")).getImage();
        setIconImage(icon);
        getContentPane().setBackground(new java.awt.Color(204, 230, 255));
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3,3));
        p.setBackground(new java.awt.Color(204, 230, 255));

        //downloading names of astronauts currently in space
        PeopleInSpaceNowURL peopleInSpaceNowURL= new PeopleInSpaceNowURL();
        PeopleInSpaceNow peopleInSpaceNow = null;
        try {
            peopleInSpaceNow = peopleInSpaceNowURL.RequestPeopleInSpaceNow();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList people = null;
        if (peopleInSpaceNow != null) {
            people = peopleInSpaceNow.getPeople();
        }

        Pattern pattern = Pattern.compile("'(.*?)'");
        assert people != null;
        for (Object person : people) {
            String mydata = person.toString();
            //find name of astronaut
            Matcher matcher = pattern.matcher(mydata);
            String name = "a";
            if (matcher.find()) {
                name = matcher.group(1);
            }
            JLabel label = new JLabel(name, SwingConstants.CENTER);
            p.add(label);
            //if photo was already downloaded, use it
            File tmpDir = new File("src/main/resources/" + name + ".jpg");
//            File tmpDir = new File(String.valueOf(getClass().getResource("/" + name + ".jpg")));
            boolean exists = tmpDir.exists();
            if (exists) {
                ImageIcon imageIcon = new ImageIcon("src/main/resources/" + name + ".jpg");
//                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/" + name + ".jpg"));
                //scale image
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(200, 190, Image.SCALE_SMOOTH); // scale image
                imageIcon = new ImageIcon(newimg);
                p.add(new JLabel(imageIcon));
            } else { //if there is no proper image, here new image is downloaded
                System.out.println("pobieranie zdjecia");
                String imageUrl = AstronautImagesURL.search(name).getContentUrl();
                String destinationFile = "src/main/resources/" + name + ".jpg";

                try {
                    saveImage(imageUrl, destinationFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageIcon imageIcon = new ImageIcon(destinationFile);
                //scale image
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(200, 190, Image.SCALE_SMOOTH); // scale image
                imageIcon = new ImageIcon(newimg);
                p.add(new JLabel(imageIcon));
            }
        }
        add(p);

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