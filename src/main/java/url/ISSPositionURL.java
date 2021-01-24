package url;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;

public class ISSPositionURL {
    private static final String URL="http://api.open-notify.org/iss-now.json";
    private HttpClient client;
    private HttpRequest request;


    public ISSPositionURL() {
        client  = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
    }

    public ISSPosition RequestISSPosition() throws IOException, InterruptedException {

        // get json from web
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        // parse json to class ISSPosition instance
        Gson gson = new Gson();
        ISSPosition issposition= gson.fromJson(json,ISSPosition.class);

        return issposition;
    }
}