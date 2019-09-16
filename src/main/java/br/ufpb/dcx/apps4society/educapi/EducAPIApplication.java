package br.ufpb.dcx.apps4society.educapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufpb.dcx.apps4society.educapi.domain.Challenge;
import br.ufpb.dcx.apps4society.educapi.domain.Context;
import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.repositories.ChallengeRepository;
import br.ufpb.dcx.apps4society.educapi.repositories.ContextRepository;
import br.ufpb.dcx.apps4society.educapi.repositories.UserRepository;

@Controller
@SpringBootApplication
public class EducAPIApplication implements CommandLineRunner {
	@Autowired
	private ChallengeRepository challengeRepository;
	@Autowired 
	private ContextRepository contextRepository;
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EducAPIApplication.class, args);	
	}
	
	@RequestMapping("/")
    @ResponseBody
	String home() {
      return "Hello World!";
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
