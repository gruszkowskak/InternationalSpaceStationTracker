package gui;

import url.AstronautImagesURL;
import url.PeopleInSpaceNow;
import url.PeopleInSpaceNowURL;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSave {

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        //saves image downloaded from imageUrl to destinationFile
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }
    public static void main(String[] args) throws IOException {
        //download the list of astronauts now on ISS
        PeopleInSpaceNowURL peopleInSpaceNowURL = new PeopleInSpaceNowURL();
        PeopleInSpaceNow peopleInSpaceNow = null;
        try {
            peopleInSpaceNow = peopleInSpaceNowURL.RequestPeopleInSpaceNow();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList people = null;
        if (peopleInSpaceNow != null) {
            people = peopleInSpaceNow.getPeople();
        }
        Pattern pattern = Pattern.compile("'(.*?)'");
        assert people != null;
        for (Object person : people) {
            String mydata = person.toString();
            //find name of astronaut
            Matcher matcher = pattern.matcher(mydata);
            String name = "a";
            if (matcher.find()) {
                name = matcher.group(1);
            }
            //get source url and define destination
            String imageUrl = AstronautImagesURL.search(name).getContentUrl();
            String destinationFile = "src/main/resources/" + name + ".jpg";

            saveImage(imageUrl, destinationFile);

        }
}
}
