package br.ufpb.dcx.apps4society.educandoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.apps4society.educandoapi.domain.User;
import br.ufpb.dcx.apps4society.educandoapi.repositories.UserRepository;
import br.ufpb.dcx.apps4society.educandoapi.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User search(Long id) throws ObjectNotFoundException {
		Optional<User> obgOptional = userRepository.findById(id);
		return obgOptional.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + User.class.getName()));
	}

}
