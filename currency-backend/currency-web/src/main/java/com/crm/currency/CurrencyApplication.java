package com.crm.currency;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties
@ServletComponentScan
public class CurrencyApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyApplication.class, args);
    }
}
