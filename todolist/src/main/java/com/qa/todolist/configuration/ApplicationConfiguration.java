package com.qa.todolist.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
@Configuration//specifies this is a config class and should be run as soon as the application starts
public class ApplicationConfiguration {

	@Bean
	@Scope("prototype")//make a bean that is scope prototype so that we get new beans every time we make the call
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);//setup the config
		return modelMapper;
	}
}
