package br.ufpb.dcx.apps4society.educapi.resources;

import java.util.List;

import javax.validation.Valid;

import br.ufpb.dcx.apps4society.educapi.dto.challenge.ChallengeRegisterDTO;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.InvalidUserException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufpb.dcx.apps4society.educapi.domain.Challenge;
import br.ufpb.dcx.apps4society.educapi.dto.challenge.ChallengeDTO;
import br.ufpb.dcx.apps4society.educapi.services.ChallengeService;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="/v1/api/")
public class ChallengeResource {
	@Autowired
	private ChallengeService challengeService;

	@ApiOperation("Returns a Challenge present in the service, if the token and the Challenge ID are valid.")
	@GetMapping("auth/challenges/{idChallenge}")
	public ResponseEntity<Challenge> find(@RequestHeader("Authorization") String token,
										  @PathVariable Long idChallenge) {
		try {
			return new ResponseEntity<Challenge>(challengeService.find(token, idChallenge), HttpStatus.OK);
		}catch (ObjectNotFoundException exception){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (InvalidUserException | SecurityException exception){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("Adds a new Challenge to a Context, if the token and the Context ID are valid.")
	@PostMapping("auth/challenges/{idContext}")
	public ResponseEntity<Challenge> insert(@RequestHeader("Authorization") String token,
											@Valid @RequestBody ChallengeRegisterDTO objDto,
											@PathVariable Long idContext){
		try {
			return new ResponseEntity<Challenge>(challengeService.insert(token, objDto, idContext), HttpStatus.CREATED);
		}catch (ObjectNotFoundException exception){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (InvalidUserException | SecurityException exception){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("Updates a User Challenge, if the token and the Challenge ID are valid.")
	@PutMapping("auth/challenges/{idChallenge}")
	public ResponseEntity<Challenge> update(@RequestHeader("Authorization") String token,
											@Valid @RequestBody ChallengeRegisterDTO objDto,
											@PathVariable Long idChallenge){
		try {
			return new ResponseEntity<Challenge>(challengeService.update(token, objDto, idChallenge), HttpStatus.OK);
		}catch (ObjectNotFoundException exception){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (InvalidUserException | SecurityException exception){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("Deletes a User Challenge, if the token and the Challenge ID are valid.")
	@DeleteMapping("auth/challenges/{idChallenge}")
	public ResponseEntity<Void> delete(@RequestHeader("Authorization") String token,
									   @PathVariable Long idChallenge){
		try {
			challengeService.delete(token, idChallenge);
			return ResponseEntity.ok().build();
		}catch (ObjectNotFoundException exception){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (InvalidUserException | SecurityException exception){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("Returns a list of all Challenges registered by the request User, if the token is valid.")
	@GetMapping("auth/challenges")
	public ResponseEntity<List<Challenge>> findAllByUser(@RequestHeader("Authorization") String token){
		try {
			return new ResponseEntity<List<Challenge>>(challengeService.findChallengesByCreator(token), HttpStatus.OK);
		}catch (ObjectNotFoundException exception){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (InvalidUserException exception){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("Resturns a list of all Challenges registered in the service.")
	@GetMapping("challenges")
	public ResponseEntity<List<Challenge>> findAllChallenges(){
		return new ResponseEntity<List<Challenge>>(challengeService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Page<ChallengeDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="word") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Challenge> list = challengeService.findPage(page, linesPerPage, orderBy, direction);
		Page<ChallengeDTO> listDto = list.map(obj -> new ChallengeDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
