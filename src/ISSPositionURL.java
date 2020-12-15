import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class ISSPositionURL {
    private static final String URL="http://api.open-notify.org/iss-now.json";
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
        // get json from web
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);

        // parse json to class ISSposition instance
        Gson gson = new Gson();
        ISSPosition issposition= gson.fromJson(json,ISSPosition.class);
        System.out.println(issposition);
        System.out.println(issposition.getLatitude() + " "+ issposition.getLongitude());

    }
}