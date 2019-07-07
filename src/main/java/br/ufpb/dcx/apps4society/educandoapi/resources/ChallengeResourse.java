package br.ufpb.dcx.apps4society.educandoapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.dcx.apps4society.educandoapi.domain.Challenge;
import br.ufpb.dcx.apps4society.educandoapi.services.ChallengeService;
import br.ufpb.dcx.apps4society.educandoapi.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/challenges")
public class ChallengeResourse {
	@Autowired
	private ChallengeService challengeService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) throws ObjectNotFoundException {
		Challenge obj = challengeService.search(id);
		return ResponseEntity.ok().body(obj);
	}

}
