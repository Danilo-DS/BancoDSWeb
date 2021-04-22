package br.com.banco.dsweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.banco.dsweb.util.ConstantUtil;
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
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage(ConstantUtil.BASE_PACKAGE))
			.paths(PathSelectors.any())
			.build()
			.useDefaultResponseMessages(false)
			.apiInfo(infoApi());
	}
	
	public ApiInfo infoApi() {
		return new ApiInfoBuilder()
				.title(ConstantUtil.TITLE)
				.description(ConstantUtil.DESCRIPTION)
				.license(ConstantUtil.LICENSE)
				.contact(new Contact(ConstantUtil.CONTACT_NAME, ConstantUtil.CONTACT_URL, ConstantUtil.CONTACT_EMAIL))
				.build();
	}
}
