package br.com.banco.dsweb.config;

import org.springframework.context.annotation.Configuration;

import br.com.banco.dsweb.util.ConstantUtil;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage(ConstantUtil.BASE_PACKAGE))
			.paths(PathSelectors.ant(ConstantUtil.PATHS))
			.build()
			.useDefaultResponseMessages(false);
	}
}
