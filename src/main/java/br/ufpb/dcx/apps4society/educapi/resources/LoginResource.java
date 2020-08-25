package br.ufpb.dcx.apps4society.educapi.resources;

import br.ufpb.dcx.apps4society.educapi.dto.user.UserLoginDTO;
import br.ufpb.dcx.apps4society.educapi.response.LoginResponse;
import br.ufpb.dcx.apps4society.educapi.services.JWTService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth/")
@CrossOrigin("*")
public class LoginResource {

    @Autowired
    private JWTService jwtService;

    @ApiOperation("Returns a user authentication token.")
    @PostMapping("login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLoginDTO userLoginDTO){
        return new ResponseEntity<LoginResponse>(jwtService.authenticate(userLoginDTO), HttpStatus.OK);
    }
}
