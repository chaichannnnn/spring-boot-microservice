package com.chaichannnnn.department;

import feign.RequestInterceptor;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignTracingConfig {

    @Bean
    public RequestInterceptor tracingRequestInterceptor(Tracer tracer) {
        return requestTemplate -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                requestTemplate.header("x-b3-traceid", currentSpan.context().traceId());
                requestTemplate.header("x-b3-spanid", currentSpan.context().spanId());
                requestTemplate.header("x-b3-sampled", currentSpan.context().sampled() ? "1" : "0");
            }
        };
    }
}
