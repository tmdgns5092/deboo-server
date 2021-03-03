package com.restapi.deboo.security;

public class JwtProperties {
    // 암호화 키
    public static final String SECRET_KEY = "dsaidmosidqnwpqe";

    // 토큰 유효기간 15분
    public static final Long EXPIRATION_TIME = 24 * 3600 * 1000L;
}