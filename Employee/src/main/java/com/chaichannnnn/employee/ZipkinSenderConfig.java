package com.chaichannnnn.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.Sender;
import zipkin2.reporter.urlconnection.URLConnectionSender;

@Configuration
public class ZipkinSenderConfig {

    @Value("${zipkin.base-url}")
    private String zipkinBaseUrl;

    @Bean
    public Sender sender() {
        String endpoint = zipkinBaseUrl + "/api/v2/spans";
        return URLConnectionSender.create(endpoint);
    }
}

