import url.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Create HttpClient once in program
        ISSPositionURL issPositionURL= new ISSPositionURL();
        // get actual issPosition, use when you nid get new actual position
        ISSPosition issPosition = issPositionURL.RequestISSPosition();

        System.out.println(issPosition);
        System.out.println(issPosition.getLatitude() + " "+ issPosition.getLongitude());


        // Create HttpClient once in program
        ISSPassTimesURL isspasstimesURL= new ISSPassTimesURL();
        // get actual issPosition, use when you nid get new actual position
        ISSPassTimes issPassTimes = isspasstimesURL.RequestISSPassTimes(52.216f, 21);
        System.out.println(issPassTimes.getMessage());
        System.out.println(issPassTimes.getResponse());
        System.out.println(issPassTimes.getResponse().get(0));
        System.out.println(issPassTimes.getRequest());

        // Create HttpClient once in program
        PeopleInSpaceNowURL peopleInSpaceNowURL= new PeopleInSpaceNowURL();
        // get actual issPosition, use when you nid get new actual position
        PeopleInSpaceNow peopleInSpaceNow = peopleInSpaceNowURL.RequestPeopleInSpaceNow();
        ArrayList people = peopleInSpaceNow.getPeople();
        String mydata = people.get(1).toString();
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(mydata);
        if (matcher.find())
        {
            System.out.println(matcher.group(1));
        }
        System.out.println(people.get(1).toString());
    }
}
