package com.questionaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
@SpringBootApplication(
	    exclude = {JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class QuestionaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionaireApplication.class, args);
	}
	
//	@Bean
//	public RestTemplate getRestTemplate()
//	{
//		return new RestTemplate();
//	}

}
