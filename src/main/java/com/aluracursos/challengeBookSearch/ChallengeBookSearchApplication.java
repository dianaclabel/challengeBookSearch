package com.aluracursos.challengeBookSearch;

import com.aluracursos.challengeBookSearch.principal.Principal;
import com.aluracursos.challengeBookSearch.services.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeBookSearchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBookSearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal menu = new Principal();
		menu.interfaz();
	}
}
