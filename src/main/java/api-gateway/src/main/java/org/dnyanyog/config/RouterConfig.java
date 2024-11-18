package org.dnyanyog.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            "directory_login_route",
            r -> r.path("/api/v1/directory/validate/**").uri("http://localhost:8081"))
        .route(
            "directory_add_route", r -> r.path("/api/v1/directory/**").uri("http://localhost:8081"))
        .route("patient_add_route", r -> r.path("/api/v1/patient/**").uri("http://localhost:8082"))
        .route(
            "appointment_add_route",
            r -> r.path("/api/v1/appointment/**").uri("http://localhost:8085"))
        .route("case_add_route", r -> r.path("/api/v1/case/**").uri("http://localhost:8084"))
        .build();
  }
}
