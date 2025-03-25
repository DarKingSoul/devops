package org.dev.devops.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dev.devops.models.MessageRequest;
import org.dev.devops.service.DevOpsService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class DevOpsHandler {

    private final DevOpsService devOpsService;

    public Mono<ServerResponse> getResponse(ServerRequest request) {
        return request.bodyToMono(MessageRequest.class)
                .flatMap(devOpsService::getResponse)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> generateToken(ServerRequest request) {
        return devOpsService.generateToken()
                .flatMap(token -> ServerResponse.ok().bodyValue(token));
    }

}
