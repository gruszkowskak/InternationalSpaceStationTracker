
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class MainFrame extends JFrame {

    public MainFrame() throws IOException, InterruptedException {
        super("ISS tracker");
        setLayout(new FlowLayout(FlowLayout.CENTER,0,0));



        String fileName = "WholeMap.jpg";
        JMenuBar menuBar = new JMenuBar();
        JMenu mapMenu = new JMenu("Maps");
        JMenuItem menuItem = new JMenuItem("Night");
        menuItem.addActionListener((event) ->  System.out.println("Night"));
        mapMenu.add(menuItem);
        JMenuItem menuItem2 = new JMenuItem("WholeMap");
        menuItem.addActionListener((event) ->  System.out.println("WholeMap"));
        mapMenu.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Map");
        menuItem.addActionListener((event) ->  System.out.println("Map"));
        mapMenu.add(menuItem3);
        menuBar.add(mapMenu);
        setJMenuBar(menuBar);

        JPanel worldMapPanel = new WorldMapPanel();
        add(worldMapPanel);

        // Create HttpClient once in program
        //ISSPositionURL issPositionURL= new ISSPositionURL();
        // get actual issPosition, use when you nid get new actual position
        //ISSPosition issPosition = issPositionURL.RequestISSPosition();
        JTextField latitudeTextField = new JTextField("Latitude : ");
        latitudeTextField.setPreferredSize(new Dimension(100,24));
        add(latitudeTextField);
        JTextField longitudeTextField = new JTextField("Longitude : ");
        longitudeTextField.setPreferredSize(new Dimension(100, 24));
        add(longitudeTextField);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(5000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ((WorldMapPanel) worldMapPanel).drawPointR();
                ISSPosition issPosition = ((WorldMapPanel) worldMapPanel).getIssPosition();
                latitudeTextField.setText("Latitude : "+ issPosition.getLatitude());
                longitudeTextField.setText("Longitude : "+issPosition.getLongitude());

            }
        });

        timer.start();
        pack();
        setVisible(true);
    }
}