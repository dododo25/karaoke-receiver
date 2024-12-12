package com.dododo.receiver;

import com.dododo.receiver.generator.CodeGenerator;
import com.dododo.receiver.model.GameDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KaraokeReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaraokeReceiverApplication.class, args);
	}

	@Bean
	public CodeGenerator getCodeGenerator() {
		return new CodeGenerator();
	}

	@Bean
	public GameDetails getDetails() {
		return new GameDetails();
	}
}
