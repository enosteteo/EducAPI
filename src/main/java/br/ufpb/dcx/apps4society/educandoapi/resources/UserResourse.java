package br.ufpb.dcx.apps4society.educandoapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.dcx.apps4society.educandoapi.domain.User;
import br.ufpb.dcx.apps4society.educandoapi.services.UserService;
import br.ufpb.dcx.apps4society.educandoapi.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/users")
public class UserResourse {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		User obj = userService.search(id);
		return ResponseEntity.ok().body(obj);
	}

}
