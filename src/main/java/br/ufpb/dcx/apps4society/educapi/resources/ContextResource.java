package br.ufpb.dcx.apps4society.educapi.resources;

import java.util.List;
import javax.validation.Valid;

import br.ufpb.dcx.apps4society.educapi.dto.context.ContextRegisterDTO;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.InvalidUserException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufpb.dcx.apps4society.educapi.domain.Context;
import br.ufpb.dcx.apps4society.educapi.dto.context.ContextDTO;
import br.ufpb.dcx.apps4society.educapi.services.ContextService;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/v1/api/")
@CrossOrigin("*")
public class ContextResource {

    @Autowired
    private ContextService contextService;

    @ApiOperation("Returns a Context, if the Context ID are valid.")
    @GetMapping("contexts/{idContext}")
    public ResponseEntity<Context> find(@PathVariable Long idContext) {
        try {
            return new ResponseEntity<Context>(contextService.find(idContext), HttpStatus.OK);
        }catch (ObjectNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Adds a new Context to the service, if the token is valid.")
    @PostMapping("auth/contexts")
    public ResponseEntity<ContextDTO> insert(@RequestHeader("Authorization") String token,
                                             @Valid @RequestBody ContextRegisterDTO objDto) {
        try {
            return new ResponseEntity<ContextDTO>(contextService.insert(token, objDto), HttpStatus.CREATED);
        }catch (ObjectNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (InvalidUserException | SecurityException exception){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation("Updates a User Context, if the token and the Context ID are valid.")
    @PutMapping("auth/contexts/{idContext}")
    public ResponseEntity<ContextDTO> update(@RequestHeader("Authorization") String token,
                                             @Valid @RequestBody ContextRegisterDTO objDto,
                                             @PathVariable Long idContext) {
        try {
            return new ResponseEntity<ContextDTO>(contextService.update(token, objDto, idContext), HttpStatus.OK);
        }catch (ObjectNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (InvalidUserException | SecurityException exception){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation("Deletes a User Context from the service, if the token and the Context ID are valid.")
    @DeleteMapping("auth/contexts/{idContext}")
    public ResponseEntity<ContextDTO> delete(@RequestHeader("Authorization") String token,
                                             @PathVariable Long idContext) {
        try {
            return new ResponseEntity<ContextDTO>(contextService.delete(token, idContext), HttpStatus.OK);
        }catch (ObjectNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (InvalidUserException | SecurityException exception){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation("Returns a list of all Contexts registered by the request User, if the token is valid.")
    @GetMapping("auth/contexts")
    public ResponseEntity<List<ContextDTO>> findAllByUser(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<List<ContextDTO>>(contextService.findContextsByCreator(token), HttpStatus.OK);
        }catch (ObjectNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (InvalidUserException | SecurityException exception){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation("Returns a list of Contexts registered in the service.")
    @GetMapping("contexts")
    public ResponseEntity<Page<Context>> findAllContexts(@RequestParam(value = "size", defaultValue = "20") Integer size,
                                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         Pageable pageable){
        return new ResponseEntity<Page<Context>>(contextService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation("Returns a list of all the user's contexts with email passed in the request.")
    @GetMapping("contexts/email")
    public ResponseEntity<List<Context>> getContextsByEmail(@RequestParam(value = "email") String email){
        try {
            return new ResponseEntity<List<Context>>(contextService.findContextsByEmail(email), HttpStatus.OK);
        }catch (InvalidUserException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Returns a list of Contexts started with the word sent in the request.")
    @GetMapping("contexts/prefix")
    public ResponseEntity<Page<Context>> getContextsByPrefix(@RequestParam(value = "prefix") String prefix,
                                                             @RequestParam(value = "size", defaultValue = "20") Integer size,
                                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             Pageable pageable){
        return new ResponseEntity<Page<Context>>(contextService.findContextsByNamePrefix(prefix, pageable), HttpStatus.OK);
    }

}