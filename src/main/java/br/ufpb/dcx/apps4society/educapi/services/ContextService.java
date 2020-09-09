package br.ufpb.dcx.apps4society.educapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.dto.context.ContextRegisterDTO;
import br.ufpb.dcx.apps4society.educapi.repositories.UserRepository;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpb.dcx.apps4society.educapi.domain.Context;
import br.ufpb.dcx.apps4society.educapi.dto.context.ContextDTO;
import br.ufpb.dcx.apps4society.educapi.repositories.ContextRepository;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.ObjectNotFoundException;

@Service
public class ContextService {
	@Autowired
	private JWTService jwtService;

	@Autowired
	private ContextRepository contextRepository;

	@Autowired
	private UserRepository userRepository;

	public Context find(String token, Long id) throws ObjectNotFoundException, InvalidUserException {
		Optional<String> usuarioId = jwtService.recoverUser(token);
		if (usuarioId.isEmpty()){
			throw new InvalidUserException();
		}

		Optional<Context> obgOptional = contextRepository.findById(id);
		if (obgOptional.isEmpty()){
			throw new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Context.class.getName());
		}
		return obgOptional.get();
	}

	@Transactional
	public ContextDTO insert(String token, ContextRegisterDTO contextRegisterDTO) throws ObjectNotFoundException, InvalidUserException {
		User user = validateUser(token);

		Context context = contextRegisterDTO.toContext();

		context.setCreator(user);
		contextRepository.save(context);
		return new ContextDTO(context);
	}

	public ContextDTO update(String token, ContextRegisterDTO contextRegisterDTO, Long id) throws ObjectNotFoundException, InvalidUserException {
		User user = validateUser(token);

		Context newObj = find(token, id);
		if (!newObj.getCreator().equals(user)){
			throw new InvalidUserException();
		}

		updateData(newObj, contextRegisterDTO.toContext());
		contextRepository.save(newObj);
		return new ContextDTO(newObj);
	}

	public ContextDTO delete(String token, Long id) throws ObjectNotFoundException, InvalidUserException {
		User user = validateUser(token);

		Context context = find(token, id);
		if (!context.getCreator().equals(user)){
			throw new InvalidUserException();
		}
		contextRepository.deleteById(id);
		return new ContextDTO(context);
	}

	public List<ContextDTO> findAll(){
		List<Context> contextList = contextRepository.findAll();

		return contextList.stream().map(ContextDTO::new).collect(Collectors.toList());
	}

	public List<ContextDTO> findContextsByCreator(String token) throws ObjectNotFoundException, InvalidUserException {
		User user = validateUser(token);

		List<Context> contextListByCreator = contextRepository.findContextsByCreator(user);

		return contextListByCreator.stream().map(ContextDTO::new).collect(Collectors.toList());
	}

	public List<Context> findContextsByEmail(String email) throws InvalidUserException {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()){
			throw new InvalidUserException();
		}

		return new ArrayList<>(userOptional.get().getContexts());
	}

	public List<Context> findContextsByNamePrefix(String name) throws ObjectNotFoundException {
		Optional<List<Context>> optionalContexts = contextRepository.findByNameStartsWithIgnoreCase(name);
		if (optionalContexts.isEmpty()){
			throw new ObjectNotFoundException();
		}

		return optionalContexts.get();
	}

	public Page<Context> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return contextRepository.findAll(pageRequest);
	}

	private void updateData(Context newObj, Context obj) {
		newObj.setName(obj.getName());
		newObj.setImageUrl(obj.getImageUrl());
		newObj.setSoundUrl(obj.getSoundUrl());
		newObj.setVideoUrl(obj.getVideoUrl());
	}

	private User validateUser(String token) throws ObjectNotFoundException, InvalidUserException {
		Optional<String> userEmail = jwtService.recoverUser(token);
		if (userEmail.isEmpty()){
			throw new InvalidUserException();
		}

		Optional<User> userOptional = userRepository.findByEmail(userEmail.get());
		if (userOptional.isEmpty()){
			throw new ObjectNotFoundException();
		}

		return userOptional.get();
	}

}