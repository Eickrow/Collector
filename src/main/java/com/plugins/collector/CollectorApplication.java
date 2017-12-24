package com.plugins.collector;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = {"com.plugins.collector.bean"})
public class CollectorApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CollectorApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
