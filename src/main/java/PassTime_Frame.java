
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassTime_Frame extends JFrame {

    public PassTime_Frame() throws IOException, InterruptedException {
        super("Next pass time");
        setLayout(new FlowLayout());

        ISSPassTimesURL isspasstimesURL= new ISSPassTimesURL();
        ISSPassTimes issPassTimes = isspasstimesURL.RequestISSPassTimes(52.216f, 21); //Warsaw
        //take the first pass
        String time= String.valueOf(issPassTimes.getResponse().get(0));
        //convert date to human readable format
        Long time_sub= Long.parseLong(time.substring(32,42));
        Date date = new java.util.Date(time_sub*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1"));
        String formattedDate = sdf.format(date);
        add(new JLabel("Next pass date is : "+formattedDate));

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu isstrackerMenu = new JMenu("ISS Tracker");
        JMenuItem isstrackerItem = new JMenuItem("Go to...");
        isstrackerItem.addActionListener((event)-> {
            try {
                new MainFrame();
                setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        });
        isstrackerMenu.add(isstrackerItem);
        menuBar.add(isstrackerMenu);
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
        setJMenuBar(menuBar);
        setSize(1414,660);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}