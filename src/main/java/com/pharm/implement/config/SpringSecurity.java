package com.pharm.implement.config;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers("/registration/**").permitAll()
                    .requestMatchers("/home/**").permitAll()
                    .requestMatchers("/products/**").hasAuthority("ROLE_ADMIN")
                    .requestMatchers("/cart/**").hasAuthority("ROLE_USER")
                    .requestMatchers("/bill/**").permitAll()
                    .requestMatchers("/AdminHome").hasAuthority("ROLE_ADMIN")
                    .requestMatchers("/error").permitAll()
              
            )
            .formLogin(form ->
                form
                    .loginPage("/login").permitAll()
                    .successHandler(customAuthenticationSuccessHandler())
                    .failureUrl("/login?error=true")
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true) // Clear the user's session
                    .deleteCookies("JSESSIONID") // Clear the JSESSIONID cookie
                    .permitAll()
            );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Get the roles of the authenticated user
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // Redirect based on user role
            if (authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/AdminHome");
            } else if (authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"))) {
                response.sendRedirect("/home");
            } else {
                response.sendRedirect("/index"); // Default redirect if no specific role is matched
            }
        };
    
}
}
