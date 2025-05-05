package com.quanlydiemsinhvien.qldsv.filters;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

public class JwtAuthenticationKeycloakConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final String realm;

    public JwtAuthenticationKeycloakConverter(String realm) {
        this.realm = realm;
    }

    @Override
    public AbstractAuthenticationToken convert(@SuppressWarnings("null") Jwt source) {
        return new JwtAuthenticationToken(
                source,
                Stream.concat(
                        new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                        extractResourceRoles(source).stream()
                ).collect(Collectors.toSet())
        );
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        // Kiểm tra và ép kiểu đúng cho resource_access
        Object resourceAccessObj = jwt.getClaims().get("resource_access");

        if (resourceAccessObj instanceof Map) {
            Map<String, Object> resourceAccess = (Map<String, Object>) resourceAccessObj;

            // Kiểm tra "account" trong resourceAccess có phải là Map không
            Object accountObj = resourceAccess.get(realm);
            if (accountObj instanceof Map) {
                Map<String, List<String>> account = (Map<String, List<String>>) accountObj;
                List<String> roles = account.get("roles");

                if (roles != null) {
                    return roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")))
                            .collect(Collectors.toSet());
                }
            }
        }
        // Trả về rỗng nếu không có dữ liệu hoặc không khớp
        return Collections.emptySet();
    }

}
