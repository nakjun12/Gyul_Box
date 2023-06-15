package jeju.oneroom.openapi.controller;

import jeju.oneroom.openapi.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

// OpenApi를 통한 데이터베이스 생성을 위한 Controller
@Slf4j
@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiService openApiService;
    private final WebClient webClient;

    @Value("${open-api.nurizip.key}")
    private String serviceKey;

    @GetMapping("/api/{sigunguCd}/{bjdongCd}")
    public String callApi(@PathVariable("sigunguCd") String sigunguCd,
                          @PathVariable("bjdongCd") String bjdongCd) throws IOException {

        StringBuilder returnAll = new StringBuilder();
        String decodedServiceKey = URLDecoder.decode(serviceKey, StandardCharsets.UTF_8);

        String url = "http://apis.data.go.kr/1613000/BldRgstService_v2/getBrTitleInfo";
        String uri = url + "?serviceKey={serviceKey}&sigunguCd={sigunguCd}&bjdongCd={bjdongCd}&_type=json";
        String responseBody = webClient.get()
                .uri(uri, decodedServiceKey, sigunguCd, bjdongCd)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        openApiService.init(responseBody);

        returnAll.append(responseBody);

        return returnAll.toString();
    }
}
