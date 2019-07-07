package br.ufpb.dcx.apps4society.educapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.apps4society.educapi.domain.Challenge;
import br.ufpb.dcx.apps4society.educapi.repositories.ChallengeRepository;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.ObjectNotFoundException;

@Service
public class ChallengeService {
	@Autowired
	private ChallengeRepository challengeRepository;
	
	public Challenge search(Long id) throws ObjectNotFoundException {
		Optional<Challenge> obgOptional = challengeRepository.findById(id);
		return obgOptional.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Challenge.class.getName()));
	}

}
