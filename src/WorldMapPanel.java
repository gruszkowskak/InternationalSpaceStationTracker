import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.imageio.ImageIO;
import javax.swing.*;

public class WorldMapPanel extends JPanel {

    private BufferedImage image;
    private ISSPositionURL issPositionURL= new ISSPositionURL();

    public WorldMapPanel() {
        super();

        File imageFile = new File("Nightmap.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image reading error");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }
    @Override

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
        drawPointB(g);


    }
    public void drawPointR (){
        Graphics gr = getGraphics();
        Graphics g = getComponentGraphics(gr);
        Graphics2D g2d = (Graphics2D) g;
        try {
            LatitudeLongitudeToPixel toPixel = new LatitudeLongitudeToPixel(issPositionURL);
            double height = toPixel.convertlatitude(image.getHeight());
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
    public void drawPointB (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        try {
            LatitudeLongitudeToPixel toPixel = new LatitudeLongitudeToPixel(issPositionURL);
            double height = toPixel.convertlatitude(image.getHeight());
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
}