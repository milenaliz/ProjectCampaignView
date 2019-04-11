package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${calc.baseurl}")
    private String baseUrl;
    public String getBaseUrl() {
        return baseUrl;
    }
}
