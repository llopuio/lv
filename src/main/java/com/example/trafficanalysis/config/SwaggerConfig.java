package com.example.trafficanalysis.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration   //通过@Configuration注解，让Spring来加载该类配置
@EnableSwagger2  //通过@EnableSwagger2注解来启用swagger2
public class SwaggerConfig {
    public Docket swaggerSpringMvcPlugin(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("lzm")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.trafficanalysis.controller"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("测试API文档")
                .description("测试api接口文档描述")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }
}
