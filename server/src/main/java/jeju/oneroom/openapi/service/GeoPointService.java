package jeju.oneroom.openapi.service;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@ConfigurationProperties("secret.open-api")
public class GeoPointService {

    private final WebClient webClient;
    private final String clientId;
    private final String clientSecret;

    public GeoPointService(WebClient.Builder webClientBuilder, @Value("${naver.cloud.client-id}") String clientId,
                           @Value("${naver.cloud.client-secret}") String clientSecret) {
        this.webClient = webClientBuilder
                .baseUrl("https://naveropenapi.apigw.ntruss.com/map-geocode/v2")
                .build();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public double[] findGeoPoint(String location) {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/geocode")
                        .queryParam("query", location)
                        .build())
                .header("X-NCP-APIGW-API-KEY-ID", clientId)
                .header("X-NCP-APIGW-API-KEY", clientSecret)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonObject json = JsonParser.parseString(response).getAsJsonObject();
        JsonArray addresses = json.getAsJsonArray("addresses");
        JsonObject address = addresses.get(0).getAsJsonObject();
        String latitude = address.get("y").getAsString();
        String longitude = address.get("x").getAsString();

        double[] coords = new double[2];
        coords[0] = Double.parseDouble(latitude);
        coords[1] = Double.parseDouble(longitude);

        return coords;
    }
}