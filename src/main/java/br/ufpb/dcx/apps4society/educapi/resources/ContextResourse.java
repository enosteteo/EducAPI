package br.ufpb.dcx.apps4society.educapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.ContextNotEmptyException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.services.UserService;
import br.ufpb.dcx.apps4society.educapi.domain.Context;
import br.ufpb.dcx.apps4society.educapi.dto.ContextDTO;
import br.ufpb.dcx.apps4society.educapi.dto.ContextNewDTO;
import br.ufpb.dcx.apps4society.educapi.services.ContextService;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/contexts")
public class ContextResourse {
	@Autowired
	private ContextService service;
	
	@Autowired
	private ContextService service;
	
	 @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> find(@PathVariable Long id) throws ObjectNotFoundException {
        Context obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> insert(@Valid @RequestBody ContextNewDTO objDto) {
        Context obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> update(@Valid @RequestBody ContextDTO objDto, @PathVariable Long id) throws ObjectNotFoundException {
        Context obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ContextDTO>> findAll(@RequestParam(name = "user", required = false) Long idCreator) {
        if (idCreator != null) {
            User creator;
            try {
                creator = userService.find(idCreator);
            } catch (ObjectNotFoundException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            List<Context> contextsByCreator = service.findContextsByCreator(creator);
            if (contextsByCreator.size() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            List<ContextDTO> contextsByCreatorDTO = contextsByCreator.stream().map(obj -> new ContextDTO(obj)).collect(Collectors.toList());
            return ResponseEntity.ok().body(contextsByCreatorDTO);
        }
        List<Context> list = service.findAll();
        List<ContextDTO> listDto = list.stream().map(obj -> new ContextDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<ContextDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Context> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ContextDTO> listDto = list.map(obj -> new ContextDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
