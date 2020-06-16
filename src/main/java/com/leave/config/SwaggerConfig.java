package com.leave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.leave.controller";//指定要扫描的包
    public static final String SWAGGER_SCAN_TEST_PACKAGE = "com.leave.page";//测试要扫描的包
    public static final String VERSION = "0.0.1";

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    //配置swagger的docket的bean实例
    @Bean
    public Docket createApi(){
//        System.out.println("swaggerEnabled："+swaggerEnabled);
        ParameterBuilder builder = new ParameterBuilder();
        builder.parameterType("header").name(TokenFilter.TOKEN_KEY)
                .description("header参数")
                .required(false)
                .modelRef(new ModelRef("string")); // 在swagger里显示header

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("leave")
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()// 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())// 过滤路径,PathSelectors.any()代表所有的路径
                .build();
    }

    //配置swagger信息apiInfo
    private ApiInfo apiInfo() {
        Contact contact = new Contact("覃乐怡","https://blog.csdn.net/qq_42677329","836955157@qq.com");

        return new ApiInfoBuilder()
                .title("LEAVE之二手信息交流与线下交易平台 api文档")
                .description("good good study，day day up!")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(contact)
                .build();
    }
}
