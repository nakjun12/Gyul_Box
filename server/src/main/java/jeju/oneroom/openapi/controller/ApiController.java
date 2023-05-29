package jeju.oneroom.openapi.controller;

import jeju.oneroom.openapi.service.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
public class ApiController {

    private final ApiService apiService;
    private final String serviceKey;

    public ApiController(ApiService apiService, @Value("${open-api.key}") String serviceKey) {
        this.apiService = apiService;
        this.serviceKey = serviceKey;
    }

    @GetMapping("/api")
    public String callApi() throws IOException {
        StringBuilder returnAll = new StringBuilder();
        for (int i = 0; i < 43; i++) {
            String url = "http://apis.data.go.kr/1613000/BldRgstService_v2/getBrTitleInfo";
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("serviceKey", serviceKey)
                    .queryParam("sigunguCd", "50110")
                    .queryParam("bjdongCd", "12200")
                    .queryParam("numOfRows", "5000")
                    .queryParam("pageNo", String.valueOf(i))
                    .queryParam("_type", "json");

            ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(builder.toUriString(), String.class);
            String responseBody = responseEntity.getBody();
            apiService.init(responseBody);
            returnAll.append(responseBody);
        }
        return returnAll.toString();
    }
}
