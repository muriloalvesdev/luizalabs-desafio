package br.com.schedule.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${developer.name}")
  private String name;

  @Value("${developer.linkedin}")
  private String linkedin;

  @Value("${developer.email}")
  private String email;

  @Value("${title.swagger}")
  private String title;

  @Value("${description.swagger}")
  private String description;

  @Value("${license.swagger}")
  private String license;

  @Value("${license.url.swagger}")
  private String licenseUrl;

  @Value("${version.software}")
  private String version;


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(apis()).paths(PathSelectors.any())
        .build().apiInfo(apiInfo());
  }

  private Predicate<RequestHandler> apis() {
    return RequestHandlerSelectors.basePackage("br.com.schedule");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().contact(new Contact(name, linkedin, email)).description(description)
        .license(license).licenseUrl(licenseUrl).version(version).title(title).build();
  }

}
