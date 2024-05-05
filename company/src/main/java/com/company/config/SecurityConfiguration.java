package com.company.config;

import com.company.security.JWTConfigurer;
import com.company.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;

    public SecurityConfiguration(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/register").permitAll()
                .requestMatchers("/api/authenticate").permitAll()
                .requestMatchers("/api/authorities").permitAll()
                .requestMatchers("/api/employees/**").permitAll()
                .requestMatchers("/api/employees-delete/*").hasRole("DIRECTOR")
                .requestMatchers("/api/customers/**").permitAll()
                .requestMatchers("/api/customers-delete/*").hasAnyRole("DIRECTOR", "DEPARTMENT_HEAD")
                .requestMatchers("/api/sales/**").permitAll()
                .requestMatchers("/api/sales-delete/*").hasAnyRole("DIRECTOR", "DEPARTMENT_HEAD")
                .requestMatchers("/api/employee-statistics/**").hasAnyRole("DIRECTOR", "DEPARTMENT_HEAD")
                .requestMatchers("/api/customer-statistics/**").hasAnyRole("DIRECTOR", "DEPARTMENT_HEAD")
                .requestMatchers("/api/sales-statistics/**").hasAnyRole("DIRECTOR", "DEPARTMENT_HEAD")
                .requestMatchers("/api/departments/**").permitAll()
                .requestMatchers("/api/positions/**").permitAll()
                .requestMatchers("/api/customer-employees/**").permitAll()
                .requestMatchers("/api/passports/**").permitAll()
                .requestMatchers("/api/sales-employee/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .apply(configurer());
        return httpSecurity.build();
    }

    private JWTConfigurer configurer(){
        return new JWTConfigurer(tokenProvider);
    }
}
