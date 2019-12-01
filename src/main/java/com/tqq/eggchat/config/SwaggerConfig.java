package com.tqq.eggchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: swagger2 配置类
 * @Author: Tan
 * @CreateDate: 2019/11/30
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * @Description:  配置接口信息等
     * @Author: Tan
     * @Date: 2019/11/30
     * @return: springfox.documentation.spring.web.plugins.Docket
     **/
    @Bean
    public Docket creatApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //配置接口类所在包
                .apis(RequestHandlerSelectors.basePackage("com.tqq.eggchat.controll"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @Description: 配置 api页面标题和描述 版本等
     * @Author: Tan
     * @Date: 2019/11/30
     * @return: springfox.documentation.service.ApiInfo
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("蛋蛋聊天室")
                .description("")
                .version("1.0")
                .build();
    }

}
