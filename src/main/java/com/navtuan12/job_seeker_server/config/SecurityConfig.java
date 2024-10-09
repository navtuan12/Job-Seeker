package com.navtuan12.job_seeker_server.config;

import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PUBLIC_POST_ENDPOINTS =
            {"/users/register", "/companies/register", "/users/login", "/companies/login"};
    private final String[] PUBLIC_GET_ENDPOINTS = {"/jobs/find-jobs", "/companies"};

    @Value("${jwt.SIGNER_KEY}")
    private String SECRET_KEY;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                request -> request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                            .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
                            .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(cfg -> cfg.configurationSource(corsFilter()));
        return httpSecurity.build();
    }


    @Bean
    CorsConfigurationSource corsFilter() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(Arrays.asList("*"));
        cfg.setAllowedMethods(Arrays.asList("*"));
        cfg.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec jwtSpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder.withSecretKey(jwtSpec).macAlgorithm(MacAlgorithm.HS512).build();
    }
}
