package com.company.security;

import com.company.entity.Authority;
import com.company.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String lowerCaseUsername = username.toLowerCase();

        return userRepository
                .findByUsername(lowerCaseUsername)
                .map(user -> createSpringSecurityUser(lowerCaseUsername, user))
                .orElseThrow(() -> new OptionalCaseException("Siz registratsiyadan o'tmagansiz!"));
    }

    private User createSpringSecurityUser(String username, com.company.entity.User user) {

        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(Authority::getPositionName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new User(username, user.getPassword(), grantedAuthorities);
    }
}
