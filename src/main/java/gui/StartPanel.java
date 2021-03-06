package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class StartPanel extends JPanel {
    private BufferedImage image;
    public String fileName = "ISS TRACKER START PAGE.png";

    public StartPanel(Frame frame){
        super();
        setLayout(new BorderLayout());
        InputStream imageFile = getClass().getResourceAsStream("/" + this.fileName);
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image reading error");
            e.printStackTrace();
        }
        JButton b=new JButton("START TRACKING");
        b.addActionListener((event)-> {
            try {
                new MainFrame("Map.jpg");
                setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            frame.setVisible(false);
        });
        Image icon = new javax.swing.ImageIcon(getClass().getResource("/nasa_logo.png")).getImage();
        frame.setIconImage(icon);
        add(b,BorderLayout.SOUTH);
        Dimension dimension = new Dimension(image.getWidth() , image.getHeight());
        setPreferredSize(dimension);
        setLocation(0,0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}
