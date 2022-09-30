package ru.evdokimov.restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTemplateApplication {

	private static RestTemplateResult restTemplateResult;

	@Autowired
	public RestTemplateApplication(RestTemplateResult restTemplateResult) {
		this.restTemplateResult = restTemplateResult;
	}

	public static void main(String[] args) {

		SpringApplication.run(RestTemplateApplication.class, args);
		System.out.println(restTemplateResult.getResult());
	}

}
