package com.tqq.eggchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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
                //设置每个接口都有一个 传入token的地方
                .globalOperationParameters(setHeadreToken())
                .select()
                //配置接口类所在包
                .apis(RequestHandlerSelectors.basePackage("com.tqq.eggchat.controller"))
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
/**
 * @Description:  设置token参数
 * @Author: Tan
 * @Date: 2019/12/11
 * @return: java.util.List<springfox.documentation.service.Parameter>
 **/
    private List<Parameter> setHeadreToken(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("测试需要权限的接口必须传入 格式为 Bearer token  注意Bearer后面是有个空格 然后+token")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }



}
