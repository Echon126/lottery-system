package com.wen.web.lotterysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@EnableAutoConfiguration
@SpringBootApplication
public class LotterySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotterySystemApplication.class, args);
    }

    @Bean
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource ba = new ReloadableResourceBundleMessageSource();
        ba.setBasename("classpath:org/springframework/security/messages_zh_CN");
        return ba;
    }
}
