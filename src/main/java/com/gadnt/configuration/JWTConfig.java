package com.gadnt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;

/**
 * @author gabriel.deaconu
 * @since November
 */
public class JWTConfig {
    private static final java.lang.String OAUTH2_JWT_SECRET = "security.oauth2.resource.jwt.key-value";

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() throws IOException {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String secretKey = System.getProperty(OAUTH2_JWT_SECRET);
        converter.setVerifierKey(secretKey);

        return converter;
    }
}
