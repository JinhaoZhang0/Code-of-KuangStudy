package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    @Bean //配置docket以配置Swagger具体参数
    public Docket docket(Environment environment) {
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("狂神")
                .enable(flag)//是否启动Swagger
                .select()
                //RequestHandlerSelectors: 配置要扫描接口的方式
                //basePackage: 指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                //过滤什么路径
//                .paths(PathSelectors.ant("/example/**"))
                .build();
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo() {
        Contact contact = new Contact("秦疆", "https//blog.kuangstudy.com", "24736743@qq.com");
        return new ApiInfo(
                "狂神的SwaggerAPI文档", // 标题
                "即使再小的帆也能远航", // 描述
                "v1.0", // 版本
                "https//blog.kuangstudy.com", // 组织链接
                contact, // 联系人信息
                "Apache 2.0", // 许可
                "http://www.apache.org/licenses/LICENSE-2.0", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
