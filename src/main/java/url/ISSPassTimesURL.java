package url;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ISSPassTimesURL {
    private static final String URL="http://api.open-notify.org/iss-pass.json";

    public ISSPassTimes RequestISSPassTimes(float latitude,float longitude) throws IOException, InterruptedException {
        String urlQuery=URL + "?" + "lat=" + latitude +"&" + "lon=" + longitude;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlQuery))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Gson gson = new Gson();
        ISSPassTimes issPassTimes = gson.fromJson(json,ISSPassTimes.class);
        return  issPassTimes;


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        float lat = 52;
        float lon = 21;
        String urlQuery=URL + "?" + "lat=" + lat +"&" + "lon=" + lon;

        System.out.println(urlQuery);
        HttpClient client;
        HttpRequest request;
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlQuery))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);

        Gson gson = new Gson();
        ISSPassTimes issPassTimes = gson.fromJson(json,ISSPassTimes.class);
        System.out.println(issPassTimes);
    }
}
