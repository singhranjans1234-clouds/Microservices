//package com.tek.TEK_API_Gateway.config;
//
//import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
//import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
//import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.function.RequestPredicates;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.ServerResponse;
//
//@Configuration
//public class GatewayRoutesConfig {
//    @Bean
//    public RouterFunction<ServerResponse> userServiceRoute() {
//        return GatewayRouterFunctions.route("user_service")
//                .route(RequestPredicates.path("/api/v1/users/**"), HandlerFunctions.http())
//                .filter(LoadBalancerFilterFunctions.lb("TEK-UserService"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> orderServiceRoute() {
//        return GatewayRouterFunctions.route("order_service")
//                .route(RequestPredicates.path("/api/v1/orders/**"), HandlerFunctions.http())
//                .filter(LoadBalancerFilterFunctions.lb("TEK-OrderService"))
//                .build();
//    }
//}