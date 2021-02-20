package br.ufpb.dcx.apps4society.educapi.domain.builder;

import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.dto.user.UserLoginDTO;
import br.ufpb.dcx.apps4society.educapi.dto.user.UserRegisterDTO;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserBuilder {

    private final User user = new User();
    private final Random random = new Random();

    private final String name = "User";
    private final String email = "user@educapi.com";
    private final String differentEmail = "un" + random.ints(99) + "regt" + random.ints(99) + "@mail.com";
    private final String pasword = "usertestpassword";
    private final Long id = 2L;
    private final String invalidEmail = "in";

    public UserBuilder aUser() {
        this.user.setId(id);
        this.user.setName(name);
        this.user.setEmail(email);
        this.user.setPassword(pasword);
        return this;
    }

    public UserBuilder withDifferentEmail() {
        this.user.setEmail(differentEmail);
        return this;
    }

    public UserBuilder withInvalidEmail() {
        this.user.setEmail(invalidEmail);
        return this;
    }

    public UserBuilder withDifferentName() {
        this.user.setName("unregistered name");
        return this;
    }

    public UserBuilder withDifferentPassword() {
        this.user.setPassword("newpassword");
        return this;
    }

    public UserBuilder withNullId() {
        this.user.setId(null);
        return this;
    }


    public User buildToUser() {
        return this.user;
    }

    public UserRegisterDTO buildToUserRegisterDto() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(user.getName(), user.getEmail(), user.getPassword());
        return userRegisterDTO;
    }

    public UserLoginDTO buildToUserLoginDto() {
        UserLoginDTO userLoginDTO = new UserLoginDTO(user.getEmail(), user.getPassword());
        return userLoginDTO;
    }

}

class UserBuilderTest {

    @Test
    public void UserTest() {
        User user = new UserBuilder()
                .aUser().buildToUser();
        assertEquals(user.getEmail(), "user@educapi.com");
        assertEquals(user.getName(), "User");
        assertEquals(user.getPassword(), "usertestpassword");
    }

    @Test
    public void UserWithDifferentEmailTest() {
        User user = new UserBuilder()
                .aUser()
                .withDifferentEmail().buildToUser();
        assertNotEquals(user.getEmail(), "user@educapi.com");
        assertEquals(user.getName(), "User");
        assertEquals(user.getPassword(), "usertestpassword");
    }

    @Test
    public void UserLoginDtoWithAllDifferentTest() {
        UserLoginDTO user = new UserBuilder()
                .aUser()
                .withDifferentEmail()
                .withDifferentPassword().buildToUserLoginDto();
        assertNotEquals(user.getEmail(), "user@educapi.comm");
        assertEquals(user.getPassword(), "newpassword");
    }
}
