//package com.example.springsecurityapplication.security;
//
//
//import com.example.springsecurityapplication.services.PersonDetailsService;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//@Component
//public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
//
//    private final PersonDetailsService personDetailsService;
//
//    public AuthenticationProvider(PersonDetailsService personDetailsService) {
//        this.personDetailsService = personDetailsService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String login = authentication.getName();
//        UserDetails person = personDetailsService.loadUserByUsername(login);
//        String password = authentication.getCredentials().toString();
//
//        if(!password.equals(person.getPassword())){
//            throw new BadCredentialsException("Некорректный пароль");
//        }
//        return new UsernamePasswordAuthenticationToken(person, password, Collections.emptyList());
//    }
//
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
