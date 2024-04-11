package com.example.modeljson.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalAppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
