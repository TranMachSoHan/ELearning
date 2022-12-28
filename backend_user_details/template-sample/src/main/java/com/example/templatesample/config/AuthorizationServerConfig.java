//package com.example.templatesample.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    private static final String CLIENT_ID = "app-client";
//    private static final String CLIENT_SECRET = "app-secret";
//    private static final String GRANT_TYPE = "password";
//    private static final String AUTHORIZATION_CODE = "authorization_code";
//    private static final String REFRESH_TOKEN = "refresh_token";
//    private static final String IMPLICIT = "implicit";
//    private static final String READ = "read";
//    private static final String WRITE = "write";
//    private static final String TRUST = "trust";
//
//    @Autowired
//    public AuthenticationManager authenticationManager;
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//        tokenConverter.setSigningKey("123456");
//        return tokenConverter;
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
//        clientDetailsServiceConfigurer
//                .inMemory()
//                .withClient(CLIENT_ID)
//                .secret(CLIENT_SECRET)
//                .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
//                .scopes(READ,WRITE,TRUST);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) {
//        authorizationServerEndpointsConfigurer
//                .tokenStore(tokenStore())
//                .authenticationManager(authenticationManager)
//                .accessTokenConverter(jwtAccessTokenConverter());
//    }
//}
