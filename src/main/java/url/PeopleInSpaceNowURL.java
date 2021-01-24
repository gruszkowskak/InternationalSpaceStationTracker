package url;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class PeopleInSpaceNowURL {

    private static final String URL="http://api.open-notify.org/astros.json";
    private HttpClient client;
    private HttpRequest request;


    public PeopleInSpaceNowURL() {
        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
    }

    public PeopleInSpaceNow RequestPeopleInSpaceNow() throws IOException, InterruptedException {
        // get json from web
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        // parse json to class PeopleInSpaceNow instance
        Gson gson = new Gson();
        return gson.fromJson(json,PeopleInSpaceNow.class);
    }

}
