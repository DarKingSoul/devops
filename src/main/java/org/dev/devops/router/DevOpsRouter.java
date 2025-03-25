package org.dev.devops.router;

import org.dev.devops.constants.Constants;
import org.dev.devops.handler.DevOpsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class DevOpsRouter {

    @Bean
    public RouterFunction<ServerResponse> devOpsRouters(DevOpsHandler handler) {
        return RouterFunctions.route()
                .POST(Constants.PATH_DEVOPS, handler::getResponse)
                .GET(Constants.PATH_TOKEN, handler::generateToken)
                .build();
    }

}
