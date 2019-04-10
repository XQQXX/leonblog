package tech.ychen.blog.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
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
 * @author leon
 * @date 2019-04-09 18:22
 */
@SuppressWarnings({"unused"})
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${swagger2.enable}") private boolean enable;

    @Bean("UserApis")
    public Docket userApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/api/v1/users.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    @Bean("ArticlesApis")
    public Docket customApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("文章模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/api/v1/articles.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }
    @Bean("adminApis")
    public Docket adminApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理员模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/admin.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("leonBlog接口文档")
                .description("提供用户模块/文章模块/管理员模块/文档")
                .termsOfServiceUrl("https://ychen.tech")
                .version("1.0")
                .build();
    }
}


