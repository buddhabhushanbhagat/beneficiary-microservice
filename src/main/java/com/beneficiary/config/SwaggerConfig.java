package com.beneficiary.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/beneficiary/**")).build().apiInfo(getInfo());
		
	}
private ApiInfo getInfo() {
		
		return new ApiInfo("Beneficiary Service API", null,"microservice", "beneficiary", new Contact("Buddhabhushan", "https://buddhabhushanbhagat.github.io/-/", "buddhabhushan.bhagat2@mindtree.com"), "beneficiary", "beneficiary",new ArrayList<>());
	}
}
