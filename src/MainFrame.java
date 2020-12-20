
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame() throws IOException, InterruptedException {
        super("ISS tracker");
        setLayout(new FlowLayout());

        JPanel worldMapPanel = new WorldMapPanel();
        add(worldMapPanel);


        // Create HttpClient once in program
        ISSPositionURL issPositionURL= new ISSPositionURL();
        // get actual issPosition, use when you nid get new actual position
        ISSPosition issPosition = issPositionURL.RequestISSPosition();
        add(new JTextField("Latitude is : "+String.valueOf(issPosition.getLatitude())));
        add(new JTextField("Longitude is : "+String.valueOf(issPosition.getLongitude())));
        add(new JButton("Drow point"));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Timer timer = new Timer(5000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Graphics g = getGraphics();
                ((WorldMapPanel) worldMapPanel).drawPointR(g);

            }
        });

        timer.start();
        pack();
        setVisible(true);
    }
}