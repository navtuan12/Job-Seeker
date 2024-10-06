package com.navtuan12.job_seeker_server.utils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import com.navtuan12.job_seeker_server.exception.AppException;
import com.navtuan12.job_seeker_server.exception.ErrorCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtService {
    
    @Value("${jwt.SIGNER_KEY}")
    @NonFinal 
    String SECRET_KEY;

    public String generatorToken(String email) {
        //define algorithm
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //define payload 
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
            .subject(email)
            .issuer("navtuan12")
            .issueTime(new Date())
            .expirationTime(new Date(
                Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
            ))
            .build();
        
        Payload payload = new Payload(claimSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create", e);
            throw new RuntimeException(e);
        }
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            log.info("TOKENNNNN", bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        throw new AppException(ErrorCode.INVALID_TOKEN);
    }

    public String getPayloadFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            return claims.getSubject();
        } catch (ParseException e) {
            log.error("can't get payload", e);
            throw new RuntimeException(e);
        }
    }
}
