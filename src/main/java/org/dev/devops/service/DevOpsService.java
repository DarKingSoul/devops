package org.dev.devops.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dev.devops.models.MessageRequest;
import org.dev.devops.models.MessageResponse;
import org.dev.devops.security.jwt.JwtProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class DevOpsService {

    private final JwtProvider jwtProvider;

    public Mono<MessageResponse> getResponse(MessageRequest request) {
        String message = "Hello " + request.getTo() + ", your message will be send";
        log.info(message);
        MessageResponse response = MessageResponse.builder()
                .message(message)
                .build();
        return Mono.just(response);
    }

    public Mono<String> generateToken() {
        String token = jwtProvider.generateToken();
        return Mono.just(token);
    }
}
