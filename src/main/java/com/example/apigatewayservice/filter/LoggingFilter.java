/*
 * Created by Wonuk Hwang on 2023/02/05
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by infra Team <wonuk_hwang@bigin.io>, 2023/02/05
 */
package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

  public LoggingFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    // Custom Pre Filter
    // exchange : Webflux에서 HTTPServlet을 지원하지 않고 ServerHttpRequest, Reponse를 사용한다
    // chain : gateway filter 다양한 필터를 pre, Post 등 사용할 수 있게 만들어준다.
//    return (exchange, chain) -> {
//      ServerHttpRequest request = exchange.getRequest();
//      ServerHttpResponse response = exchange.getResponse();
//
//      log.info("Global Filter baseMessage: {}", config.getBaseMessage());
//
//      if (config.isPreLogger()) {
//        log.info("Global Filter Start: request id -> {}", request.getId());
//      }
//      // Custom Post Filter
//      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//        if (config.isPostLogger()) {
//          log.info("Global Filter End: response code -> {}", response.getStatusCode());
//        }
//      }));
//    };
    GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();
      ServerHttpResponse response = exchange.getResponse();

      log.info("Logging Filter baseMessage: {}", config.getBaseMessage());

      if (config.isPreLogger()) {
        log.info("Logging PRE Filter: request uri -> {}", request.getURI());
      }
      // Custom Post Filter
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        if (config.isPostLogger()) {
          log.info("Logging POST Filter: response code -> {}", response.getStatusCode());
        }
      }));
    }, Ordered.LOWEST_PRECEDENCE); // 지금 만들고있는 필터의 우선순서를 설정가능

    return filter;
  }

  @Data
  public static class Config {

    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
  }
}
