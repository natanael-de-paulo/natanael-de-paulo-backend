package br.java.social_network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Social_networkApplication {
	public static void main(String[] args) {
		SpringApplication.run(Social_networkApplication.class, args);
	}

}
