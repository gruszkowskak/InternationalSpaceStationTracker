

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class MainFrame extends JFrame {

    public MainFrame() throws IOException, InterruptedException {
        super("ISS tracker");
        setLayout(new FlowLayout(FlowLayout.CENTER,0,0));



        JPanel worldMapPanel = new WorldMapPanel("Map.jpg");
        add(worldMapPanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu mapMenu = new JMenu("Maps");
        JMenuItem menuItem = new JMenuItem("Night");
        menuItem.addActionListener((event) -> System.out.println("Night")); // change file in WorldMapPanel
        mapMenu.add(menuItem);
        JMenuItem menuItem2 = new JMenuItem("WholeMap");
        menuItem2.addActionListener((event) ->  System.out.println("WholeMap")); // change file in WorldMapPanel
        mapMenu.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Map");
        menuItem3.addActionListener((event) ->  System.out.println("Map"));// change file in WorldMapPanel
        mapMenu.add(menuItem3);
        menuBar.add(mapMenu);
        JMenu astrounautsMenu = new JMenu("Astrounauts");
        JMenuItem astrounatsItem = new JMenuItem("Go to...");
        astrounatsItem.addActionListener((event)-> {
            try {
                new Astronauts_Frame();
                setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        });
        astrounautsMenu.add(astrounatsItem);
        menuBar.add(astrounautsMenu);
        JMenu pass_timeMenu = new JMenu("Pass Time");
        JMenuItem pass_timeItem = new JMenuItem("Go to...");
        pass_timeItem.addActionListener((event)-> {
            try {
                new PassTime_Frame();
                setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        });
        pass_timeMenu.add(pass_timeItem);
        menuBar.add(pass_timeMenu);
        setJMenuBar(menuBar);



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