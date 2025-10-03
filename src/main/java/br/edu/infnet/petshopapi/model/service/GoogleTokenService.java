package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.clients.GoogleAuthClient;
import br.edu.infnet.petshopapi.model.dto.GoogleTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleTokenService {

    @Value("${google.auth.client.id}")
    private String clientId;

    @Value("${google.auth.client.secret}")
    private String clientSecret;

    @Value("${google.auth.refresh.token}")
    private String refreshToken;

    private String accessToken;

    private Instant expiresAt;

    private final GoogleAuthClient googleAuthClient;

    public GoogleTokenService(GoogleAuthClient googleAuthClient) {
        this.googleAuthClient = googleAuthClient;
    }

    public String getValidAccessToken() {
        if (accessToken == null || Instant.now().isAfter(expiresAt)) {
            renovarToken();
        }
        return accessToken;
    }

    private void renovarToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("refresh_token", refreshToken);
        params.put("grant_type", "refresh_token");

        GoogleTokenDTO googleTokenDTO = googleAuthClient.getAccessToken(params);

        this.accessToken = googleTokenDTO.getAccessToken();
        this.expiresAt = Instant.now().plusSeconds(googleTokenDTO.getExpiresIn() - 60);
    }
}