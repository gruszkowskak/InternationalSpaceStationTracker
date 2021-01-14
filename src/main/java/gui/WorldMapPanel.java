package gui;

import url.ISSPosition;
import url.ISSPositionURL;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;


import javax.imageio.ImageIO;
import javax.swing.*;

public class WorldMapPanel extends JPanel {

    private BufferedImage image;
    private ISSPositionURL issPositionURL= new ISSPositionURL();
    public String fileName = "WholeMap.jpg";
    private BufferedImage logo;

    public ISSPosition getIssPosition() {
        return issPosition;
    }

    private ISSPosition issPosition;

    public WorldMapPanel(String filename) {
        super();

        //File imageFile = new File(fileName);
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
    public void drawPointR (){
        Graphics gr = getGraphics();
        Graphics g = getComponentGraphics(gr);
        Graphics2D g2d = (Graphics2D) g;
        try {
            this.issPosition = issPositionURL.RequestISSPosition();
            LatitudeLongitudeToPixel toPixel = new LatitudeLongitudeToPixel(issPosition);
            double height = toPixel.convertlatitude(image.getHeight(),image.getWidth());
            double width = toPixel.convertlongitude(image.getWidth());
            Ellipse2D circle = new Ellipse2D.Double(width,height , 4, 4);
            g2d.setPaint(Color.RED);
            g2d.fill(circle);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void drawlogo (){
        InputStream imageFile = getClass().getResourceAsStream("logo3.png");
        try {
            logo = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image reading error");
            e.printStackTrace();
        }
        Graphics gr = getGraphics();
        Graphics g = getComponentGraphics(gr);
        Graphics2D g2d = (Graphics2D) g;
        try {
            this.issPosition = issPositionURL.RequestISSPosition();
            LatitudeLongitudeToPixel toPixel = new LatitudeLongitudeToPixel(issPosition);
            double height = toPixel.convertlatitude(image.getHeight(),image.getWidth());
            double width = toPixel.convertlongitude(image.getWidth());
            gr.drawImage(logo,(int)width-15,(int)height-10,null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void drawPointB (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        try {
            this.issPosition = issPositionURL.RequestISSPosition();
            LatitudeLongitudeToPixel toPixel = new LatitudeLongitudeToPixel(issPosition);
            double height = toPixel.convertlatitude(image.getHeight(),image.getWidth());
            double width = toPixel.convertlongitude(image.getWidth());
            Ellipse2D circle = new Ellipse2D.Double(width,height , 6, 6);
            g2d.setPaint(Color.RED);
            g2d.fill(circle);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}