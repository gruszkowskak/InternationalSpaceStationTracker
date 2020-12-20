
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
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}