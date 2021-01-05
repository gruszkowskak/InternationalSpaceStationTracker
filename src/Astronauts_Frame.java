
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

public class Astronauts_Frame extends JFrame {

    public Astronauts_Frame() throws IOException, InterruptedException {
        super("Astronauts");
        setLayout(new FlowLayout());
        JMenu mapMenu = new JMenu("Maps");
        JMenuBar menuBar = new JMenuBar();
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


        PeopleInSpaceNowURL peopleInSpaceNowURL= new PeopleInSpaceNowURL();

        PeopleInSpaceNow peopleInSpaceNow = peopleInSpaceNowURL.RequestPeopleInSpaceNow();
        ArrayList people = peopleInSpaceNow.getPeople();

        Pattern pattern = Pattern.compile("'(.*?)'");
        for (int i=0; i<people.size(); i++) {
            String mydata = people.get(i).toString();
            //find name of astronaut
            Matcher matcher = pattern.matcher(mydata);
            String name="a";
            if (matcher.find())
            {
                name = matcher.group(1);
            }
            add(new JLabel(i+1+"." + name+"\n"));
        }

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}