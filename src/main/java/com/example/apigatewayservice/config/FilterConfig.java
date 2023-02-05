/*
 * Created by Wonuk Hwang on 2023/02/05
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by infra Team <wonuk_hwang@bigin.io>, 2023/02/05
 */
package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create on 2023/02/05. create by IntelliJ IDEA.
 *
 * <p> </p>
 * <p> {@link } and {@link } </p> *
 *
 * @author wonukHwang
 * @version 1.0
 * @see
 * @since (ex : 5 + 5)
 */
//@Configuration
public class FilterConfig {

//  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(
            r -> r.path("/first-service/**")
                .filters(
                    f -> f.addRequestHeader("first-request", "first-request-header")
                        .addResponseHeader("first-response", "first-response-header"))
                .uri("http://localhost:8081"))
        .route(
            r -> r.path("/second-service/**")
                .filters(
                    f -> f.addRequestHeader("second-request", "second-request-header")
                        .addResponseHeader("second-response", "second-response-header"))
                .uri("http://localhost:8082"))
        .build();
  }
}
