import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Create HttpClient once in program
        ISSPositionURL issPositionURL= new ISSPositionURL();
        // get actual issPosition, use when you nid get new actual position
        ISSPosition issPosition = issPositionURL.RequestISSPosition();

        System.out.println(issPosition);
        System.out.println(issPosition.getLatitude() + " "+ issPosition.getLongitude());

    }
}
