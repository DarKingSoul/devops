package org.dev.devops.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dev.devops.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtManage implements ReactiveAuthenticationManager {

    private final JwtProvider jwtProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth ->  jwtProvider.getClaims(auth.getCredentials().toString()))
                .onErrorResume(e -> {
                    log.error("Error while authenticating: {}", e.getMessage());
                    return Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, "bad token"));
                })
                .map(claims -> new UsernamePasswordAuthenticationToken(claims, null, null));
    }

}
