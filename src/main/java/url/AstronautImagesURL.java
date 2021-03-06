package url;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class AstronautImagesURL {
    public static AstronautImages search(String astronautName){
        String URL1="https://bing-image-search1.p.rapidapi.com/images/search?q=";
        String URL3="&count=1";
        String message = astronautName + " astronauts";
        String URL2=message.replaceAll(" ","%20");

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL1 + URL2 + URL3))
                .header("x-rapidapi-key", "55383a6721msha6d633630e4e1c7p1b3eabjsn6944f46877e8")
                .header("x-rapidapi-host", "bing-image-search1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        // get json from web
        String jsonInput=null;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonInput = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // parse json to class PeopleInSpaceNow instance
        Gson gson = new Gson();
        AstronautImages astronautImages = gson.fromJson(jsonInput,AstronautImages.class);
        return astronautImages;
    }

}

