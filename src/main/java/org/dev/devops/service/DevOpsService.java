package org.dev.devops.service;

import lombok.RequiredArgsConstructor;
import org.dev.devops.models.MessageRequest;
import org.dev.devops.models.MessageResponse;
import org.dev.devops.security.jwt.JwtProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DevOpsService {

    private final JwtProvider jwtProvider;

    public Mono<MessageResponse> getResponse(MessageRequest request) {
        String message = "Hello " + request.getTo() + ", your message will be send";
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
