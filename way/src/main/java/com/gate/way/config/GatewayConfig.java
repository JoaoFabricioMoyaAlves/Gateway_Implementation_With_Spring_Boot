package com.gate.way.config;

import java.net.URI;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {

        return GatewayRouterFunctions.route("exemplo-route")
                .route(
                        path("/api/**"),
                        http(URI.create("http://localhost:8080"))
                )

                 .route(
                        path("/apt/**"),
                        http(URI.create("http://localhost:8083"))
                )
                .build(); 

    }
}
