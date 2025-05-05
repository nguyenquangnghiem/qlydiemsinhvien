/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import com.quanlydiemsinhvien.qldsv.filters.CustomAccessDeniedHandler;
import com.quanlydiemsinhvien.qldsv.filters.CustomSuccessHandler;
import com.quanlydiemsinhvien.qldsv.filters.JwtAuthenticationKeycloakConverter;
import com.quanlydiemsinhvien.qldsv.filters.RestAuthenticationEntryPoint;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${keycloak.client-id}")
    private String realm;

    @Value("${keycloak.jwk}")
    String jwkSetUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .authorizeRequests(request -> request
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                .antMatchers(HttpMethod.POST, "/api/login/", "/api/users/", "/api/send-code/", "/api/logout").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("GV", "SV", "GVU")
                .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("GV", "SV")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("GV", "SV")
                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("GV")
                .antMatchers("/giaovu/**").hasRole("GVU")
                .anyRequest().denyAll()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtAuthenticationKeycloakConverter(realm)))
            );
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    @Qualifier("customSuccessHandler")
    private CustomSuccessHandler customSuccessHandler;
}
