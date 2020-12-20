
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Astronauts_Frame extends JFrame {

    public Astronauts_Frame() throws IOException, InterruptedException {
        super("Astronauts");
        setLayout(new FlowLayout());


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