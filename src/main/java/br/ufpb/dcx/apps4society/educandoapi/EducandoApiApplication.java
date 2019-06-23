package br.ufpb.dcx.apps4society.educandoapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufpb.dcx.apps4society.educandoapi.domain.Challenge;
import br.ufpb.dcx.apps4society.educandoapi.domain.Context;
import br.ufpb.dcx.apps4society.educandoapi.domain.User;
import br.ufpb.dcx.apps4society.educandoapi.repositories.ChallengeRepository;
import br.ufpb.dcx.apps4society.educandoapi.repositories.ContextRepository;
import br.ufpb.dcx.apps4society.educandoapi.repositories.UserRepository;

@SpringBootApplication
public class EducandoApiApplication implements CommandLineRunner {
	@Autowired
	private ChallengeRepository challengeRepository;
	@Autowired 
	private ContextRepository contextRepository;
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EducandoApiApplication.class, args);	
	}
	
	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Emerson", "emerson.ruan@dce.ufpb.br", "12345678");
		
		Context c1 = new Context(null, "Natureza", user1, null, null, null);
		Context c2 = new Context(null, "Casa", user1, null, null, null);
		
		Challenge ch1 = new Challenge(null, "flor", user1, null, null, null);
		Challenge ch2 = new Challenge(null, "cadeira", user1, null, null, null);
		Challenge ch3 = new Challenge(null, "árvore", user1, null, null, null);
		Challenge ch4 = new Challenge(null, "geladeira", user1, null, null, null);
		
		c1.getChallenges().addAll(Arrays.asList(ch1, ch3));
		c2.getChallenges().addAll(Arrays.asList(ch2, ch4));
		
		ch1.getContexts().addAll(Arrays.asList(c1));
		ch2.getContexts().addAll(Arrays.asList(c2));
		ch3.getContexts().addAll(Arrays.asList(c1));
		ch4.getContexts().addAll(Arrays.asList(c2));
		
		userRepository.saveAll(Arrays.asList(user1));
		challengeRepository.saveAll(Arrays.asList(ch1, ch2, ch3, ch4));
		contextRepository.saveAll(Arrays.asList(c1, c2));
	}

}
