package org.example.owncalendarserver.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;


@Component
public class JwtUtil {
    // Header KEY 값
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 사용자 권한 값의 KEY
    public static final String AUTHORIZATION_KEY = "auth";

    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";

    // 토큰 만료시간
    private final long TOKEN_TIME = 60 * 60 * 1000L; // 60분

    // application.properties 또는 application.yml 파일에서 jwt.secret.key 값을 가져와서 주입
    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey
    private String secretKey;

    private Key key;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct // 키 초기화 과정을 자동화 (빈이 생성될 때 자동으로 초기화)
    public void init() {
        // Base64로 인코딩된 secretKey를 디코딩하여 바이트 배열로 변환
        byte[] bytes = Base64.getDecoder().decode(secretKey);

        // 디코딩된 바이트 배열을 사용하여 HMAC-SHA 키 객체 생성
        key = Keys.hmacShaKeyFor(bytes);
    }



}
