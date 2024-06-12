package com.crystalskin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("coolsms")
public class NurigoP {
	private String apikey;
	private String apisecret;
	private String fromnumber;
}
