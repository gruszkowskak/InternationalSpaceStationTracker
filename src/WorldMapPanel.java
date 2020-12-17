import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WorldMapPanel extends JPanel {

    private BufferedImage image;

    public WorldMapPanel() {
        super();

        File imageFile = new File("WholeMap.jpg");
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
        try {
            LatitudeLongitudeToPixel toPixel = new LatitudeLongitudeToPixel();
            double height = toPixel.convertlatitude(image.getHeight(),image.getWidth());
            System.out.println(height);
            double width = toPixel.convertlongitude(image.getWidth());
            System.out.println(width);
            Ellipse2D circle = new Ellipse2D.Double(width,height , 10, 10);
            g2d.setPaint(Color.RED);
            g2d.fill(circle);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}