package br.ufpb.dcx.apps4society.educapi.service;


import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.domain.builder.UserBuilder;
import br.ufpb.dcx.apps4society.educapi.dto.user.UserLoginDTO;
import br.ufpb.dcx.apps4society.educapi.repositories.UserRepository;
import br.ufpb.dcx.apps4society.educapi.response.LoginResponse;
import br.ufpb.dcx.apps4society.educapi.services.JWTService;
import br.ufpb.dcx.apps4society.educapi.services.exceptions.InvalidUserException;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class JWTServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    JWTService service;

    private UserBuilder userBuilder = new UserBuilder().aUser();
    private UserLoginDTO userLoginDto = userBuilder.buildToUserLoginDto();
    private Optional<User> user = Optional.ofNullable(userBuilder.buildToUser());

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(service, "TOKEN_KEY", "it's a token key");

    }

    @Test
    public void authenticateTest() throws InvalidUserException {

        Mockito.when(userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())).thenReturn(user);

        LoginResponse response = service.authenticate(userLoginDto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getToken());
    }


    @Test
    public void authenticateInvalidUserTest() {
        Assertions.assertThrows(InvalidUserException.class, () -> {
            UserBuilder userBuilder = new UserBuilder().aUser();
            UserLoginDTO userLoginDto = userBuilder.buildToUserLoginDto();
            service.authenticate(userLoginDto);
        });
    }

    @Test
    public void recoverUserWithNullTest() {
        Assertions.assertThrows(SecurityException.class, () -> {
            service.recoverUser(null);
        });
    }

    @Test
    public void recoverUserWithAleatoryTest() {
        Assertions.assertThrows(SecurityException.class, () -> {
            service.recoverUser("its first if");
        });
    }

    @Test
    public void recoverUserWithTokenInvalid() {
        Assertions.assertThrows(MalformedJwtException.class, () -> {
            service.recoverUser("Bearer its invalid token");
        });
    }

    @Test
    public void recoverUserValidTest() throws InvalidUserException {
        Mockito.when(userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())).thenReturn(user);
        LoginResponse response = service.authenticate(userLoginDto);

        Optional<String> userRecovered = service.recoverUser("Bearer " + response.getToken());
        Assertions.assertEquals(userRecovered.get(), userLoginDto.getEmail());
    }
}
