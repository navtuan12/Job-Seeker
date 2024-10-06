package com.navtuan12.job_seeker_server.config;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PUBLIC_POST_ENDPOINTS = {"/users/register", "/companies/register"
                                                    , "/users/login", "/companies/login"};
    private final String[] PUBLIC_GET_ENDPOINTS = {"/jobs/find-jobs", "/companies/get-company"};

    @Value("${jwt.SIGNER_KEY}")
    private String SECRET_KEY;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(request -> 
                request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                    .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
                    .anyRequest().authenticated());
        
        httpSecurity.oauth2ResourceServer(oauth2 -> 
                oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec jwtSpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HS512");

        return NimbusJwtDecoder
                    .withSecretKey(jwtSpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build(); 
    }
}
