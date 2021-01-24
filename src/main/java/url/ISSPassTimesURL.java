package url;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ISSPassTimesURL {
    private static final String URL="http://api.open-notify.org/iss-pass.json";

    public ISSPassTimes RequestISSPassTimes(float latitude,float longitude) throws IOException, InterruptedException {
        String urlQuery=URL + "?" + "lat=" + latitude +"&" + "lon=" + longitude;

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlQuery))
                .build();
        // get json from web
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        // parse json to class ISSPassTimes instance
        Gson gson = new Gson();
        ISSPassTimes issPassTimes = gson.fromJson(json,ISSPassTimes.class);
        return  issPassTimes;
    }

}
