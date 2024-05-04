package com.company.web.rest;

import com.company.security.TokenProvider;
import com.company.web.rest.vm.LoginVM;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final Logger logger = LoggerFactory.getLogger(UserJWTController.class);

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@RequestBody LoginVM loginVM){

        logger.debug("'/authenticate' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'authorize' method-i ishga tushdi");

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, loginVM.isRememberMe());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwt);
            logger.info(loginVM.getUsername() + " username uchun token yaratildi va 'header'-ga yuklandi");
            return new ResponseEntity<>(new JWTToken(jwt), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(loginVM.getUsername() + " username uchun token yaratilmadi va 'header'-ga yuklanmadi");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    static class JWTToken{

        private String token;

        public JWTToken(String token) {
            this.token = token;
        }

        @JsonProperty("jwt_token")
        public String getToken() {
            return token;
        }
    }
}
