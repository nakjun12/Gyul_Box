package jeju.oneroom.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelloHomeController {
    private final OAuth2AuthorizedClientService auth2AuthorizedClientService;

    @GetMapping("/hello-oauth2")
    public String home(Authentication authentication) {
        OAuth2AuthorizedClient authorizedClient = auth2AuthorizedClientService.loadAuthorizedClient("google", authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        System.out.println("Access Token Value: " + accessToken.getTokenValue());  // (3-1)
        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());  // (3-2)
        System.out.println("Access Token Scopes: " + accessToken.getScopes());       // (3-3)
        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());    // (3-4)
        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());  // (3-5)


        return "hello-oauth2";
    }
}