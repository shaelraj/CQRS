package com.javamonks.bank;

import com.javamonks.bank.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfig.class)
@EntityScan(basePackages = "com.javamonks.bank.models")
public class BankQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankQueryApplication.class, args);
	}

}
