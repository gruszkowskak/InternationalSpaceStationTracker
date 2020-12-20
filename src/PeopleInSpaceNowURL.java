import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PeopleInSpaceNowURL {

    private static final String URL="http://api.open-notify.org/astros.json";
    private HttpClient client;
    private HttpRequest request;


    public PeopleInSpaceNowURL() {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
    }

    public PeopleInSpaceNow RequestPeopleInSpaceNow() throws IOException, InterruptedException {
        // get json from web
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);

        // parse json to class ISSposition instance
        Gson gson = new Gson();
        PeopleInSpaceNow peopleInSpaceNow = gson.fromJson(json,PeopleInSpaceNow.class);
        return peopleInSpaceNow;
    }
    // for testing
    public static void main(String[] args) throws IOException, InterruptedException {
        PeopleInSpaceNowURL peopleInSpaceNowURL = new PeopleInSpaceNowURL();
        PeopleInSpaceNow peopleInSpaceNow= peopleInSpaceNowURL.RequestPeopleInSpaceNow();
        System.out.println(peopleInSpaceNow);
    }
}
