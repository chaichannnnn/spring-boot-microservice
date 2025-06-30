package com.chaichannnnn.apigateway;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import reactor.core.publisher.Mono;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

//    @PostConstruct
//    public void testRedisConnection() {
//        redisTemplate.opsForValue().set("hello", "world")
//                .then(redisTemplate.opsForValue().get("hello"))
//                .doOnNext(val -> System.out.println("Redis Value = " + val))
//                .subscribe();
//    }

    // Rate Limiting KeyResolver (IP-based)
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
        );
    }

    @Bean
    public RedisRateLimiter employeeRateLimiter() {
        return new RedisRateLimiter(5, 10); // replenishRate = 3, burstCapacity = 5
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // 👷 EMPLOYEE SERVICE
                .route("employee", r -> r.path("/employees/**")
                        .filters(f -> f
                                .rewritePath("/employees/(?<path>.*)", "/employees/${path}") // ตัด /employees/ ออกก่อนส่ง
                                .requestRateLimiter(config -> {
                                    config.setRateLimiter(employeeRateLimiter());
                                    config.setKeyResolver(ipKeyResolver()); // ใช้ IP เป็น key
                                })
                        )
                        .uri("lb://EMPLOYEE-SERVICE")
                )

                // 🏢 DEPARTMENT SERVICE
                .route("department", r -> r.path("/departments/**")
                        .filters(f -> f
                                .rewritePath("/departments/(?<path>.*)", "/departments/${path}")
                        )
                        .uri("lb://DEPARTMENT-SERVICE")
                )

                // 🏛 ORGANIZATION SERVICE
                .route("organization", r -> r.path("/organizations/**")
                        .filters(f -> f
                                .rewritePath("/organizations/(?<path>.*)", "/organizations/${path}")
                        )
                        .uri("lb://ORGANIZATION-SERVICE")
                )

                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
