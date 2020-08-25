package br.ufpb.dcx.apps4society.educapi.services;

import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.dto.user.UserLoginDTO;
import br.ufpb.dcx.apps4society.educapi.filter.TokenFilter;
import br.ufpb.dcx.apps4society.educapi.repositories.UserRepository;
import br.ufpb.dcx.apps4society.educapi.response.LoginResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_KEY = "EducAPIService-BY-Apps4Society-2020";

    public LoginResponse authenticate(UserLoginDTO userLoginDTO){
        Optional<User> userOptional = userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (userOptional.isPresent()){
            return new LoginResponse(generateToken(userLoginDTO));
        }

        return new LoginResponse("Invalid e-mail or password. Login not successful.");
    }

    private String generateToken(UserLoginDTO userLoginDTO){
        String token = Jwts.builder()
                .setSubject(userLoginDTO.getEmail())
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)).compact();
        return token;
    }

    public Optional<String> recoverUser(String header){
        if (header == null || !header.startsWith("Bearer ")){
            throw new SecurityException();
        }

        String token = header.substring(TokenFilter.TOKEN_INDEX);
        String subject = null;

        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        }catch (SignatureException error){
            throw new SecurityException("Token invalid or expired!");
        }

        return Optional.of(subject);
    }
}
