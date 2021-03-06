package gui;

import url.ISSPosition;
import url.ISSPositionURL;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


import javax.imageio.ImageIO;
import javax.swing.*;

public class WorldMapPanel extends JPanel {

    private BufferedImage image;
    private ISSPositionURL issPositionURL= new ISSPositionURL();
    private ISSPosition issPosition;

    public ISSPosition getIssPosition() {
        return issPosition;
    }


    public WorldMapPanel(String filename) {
        super();
        InputStream imageFile = getClass().getResourceAsStream("/" + filename);
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image reading error");
            e.printStackTrace();
        }
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
        setLocation(0,0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

    // function draw a single point and add to Panel
    public void drawPoint (Color color){
        Graphics gr = getGraphics();
        Graphics g = getComponentGraphics(gr);
        Graphics2D g2d = (Graphics2D) g;
        try {
            this.issPosition = issPositionURL.RequestISSPosition();
            LatitudeLongitudeToPixel toPixel = new LatitudeLongitudeToPixel(issPosition);
            double height = toPixel.convertlatitude(image.getHeight());
            double width = toPixel.convertlongitude(image.getWidth());
            Ellipse2D circle = new Ellipse2D.Double(width,height , 8, 8);
            g2d.setPaint(color);
            g2d.fill(circle);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}