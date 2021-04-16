package br.com.banco.dsweb.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConvert {

	@Bean
	public static ModelMapper convertDto() {
		return new ModelMapper();
	}
}
