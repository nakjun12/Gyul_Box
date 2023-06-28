package jeju.oneroom.openapi.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

// 주소를 위도, 경도로 반환하는 서비스 로직
@Service
@Transactional
@RequiredArgsConstructor
public class GeoPointService {

    private final WebClient webClient;

    @Value("${open-api.naver-cloud.id}")
    private String clientId;

    @Value("${open-api.naver-cloud.secret}")
    private String clientSecret;

    public double[] findGeoPoint(String location) {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("naveropenapi.apigw.ntruss.com")
                        .path("/map-geocode/v2/geocode")
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
