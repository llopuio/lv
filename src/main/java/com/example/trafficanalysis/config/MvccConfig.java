package com.example.trafficanalysis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class MvccConfig extends WebMvcConfigurationSupport {
    //@Bean
    public FastJsonHttpMessageConverterEx fastJsonHttpMessageConverterEx(){
        return new FastJsonHttpMessageConverterEx();
    }
}
