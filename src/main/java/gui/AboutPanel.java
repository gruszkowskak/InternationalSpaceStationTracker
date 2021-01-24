package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class AboutPanel extends JPanel {
    public AboutPanel() {
        super();
        //setLayout(new GridLayout(2,0));
        setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("src/main/resources/logo MINI.png");
        JLabel jLabel = new JLabel(imageIcon);
        //jLabel.setVerticalAlignment(JLabel.TOP);
        add(jLabel,BorderLayout.NORTH);
        JLabel label = new JLabel("<html><center>Authors: <br/><br/>" +
                "    Klaudia Gruszkowska<br/>" +
                "    Bartosz Jamroży<br/>" +
                "    Agata Kaczmarek<br/><br/><br/>" +

                "This project was created as part of course Advanced Object and Functional Programming.<br/><br/>" +
                "The application : <br/><br/>" +
                "   - shows the current position of the International Space Station on the world map<br/>" +
                "   - displays astronauts currently in space, along with their photos<br/>"+
                "   - displays the date of the next ISS flight over the selected location.<br/><br/><br/>"+
                "We get data via a publicly available API from http://open-notify.org/Open-Notify-API/.<br/><br/><br/><br/>" +
                " </center></html>");
        //label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);

    }
}