package org.dev.devops.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dev.devops.constants.Constants;
import org.dev.devops.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtFilter implements WebFilter {

    @Value("${apikey.value}")
    private String apikey_value;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();
        HttpMethod httpMethod = request.getMethod();

        String apikey = request.getHeaders().getFirst(Constants.APIKEY);
        if (apikey == null)
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, Constants.APIKEY_NOT_FOUND));
        else if (!apikey.equals(apikey_value))
            return Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, Constants.APIKEY_INVALID));

        if (path.equals(Constants.PATH_TOKEN))
            return chain.filter(exchange);

        if(path.contains(Constants.PATH_DEVOPS) && !httpMethod.equals(HttpMethod.POST)) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "ERROR"));
        }

        String jwt = request.getHeaders().getFirst(Constants.JWT_KEY);
        if(jwt == null)
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, Constants.JWT_KEY_NOT_FOUND));

        String token = jwt.replace("Bearer ", "");
        exchange.getAttributes().put(Constants.TOKEN, token);

        return chain.filter(exchange);
    }

}
