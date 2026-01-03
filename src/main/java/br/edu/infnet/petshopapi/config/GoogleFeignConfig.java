package br.edu.infnet.petshopapi.config;

import br.edu.infnet.petshopapi.model.service.GoogleTokenService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GoogleFeignConfig {

    private final GoogleTokenService googleTokenService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = googleTokenService.getValidAccessToken();
            requestTemplate.header("Authorization", "Bearer " + token);
            requestTemplate.header("Accept", "application/json");
        };
    }
}