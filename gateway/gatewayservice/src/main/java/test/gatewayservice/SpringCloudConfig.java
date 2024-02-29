package test.gatewayservice;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableDiscoveryClient
public class SpringCloudConfig {
    /**
     * Метод постройки маршрута, аналогичный cloud'у в yaml файле
     * RoutLocator = указатель маршрута
     * RouteLocatorBuilder = конструктор маршрутов
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("taskservice", r -> r.path("/tasks/**").uri("http://localhost:8081/"))
//                .route("userservice", r -> r.path("/users/**").uri("http://localhost:8082/"))
//                .build();
//    }
}
