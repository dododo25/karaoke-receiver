package com.dododo.receiver;

import com.dododo.receiver.holder.SessionsHolder;
import com.dododo.receiver.holder.TokensHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class KaraokeReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaraokeReceiverApplication.class, args);
	}

	@Bean
	public SessionsHolder sessionsHolder() {
		return new SessionsHolder();
	}

	@Bean
	public TokensHolder tokensHolder() throws NoSuchAlgorithmException {
		return new TokensHolder();
	}
}
