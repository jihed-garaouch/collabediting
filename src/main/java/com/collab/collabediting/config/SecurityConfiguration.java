package com.collab.collabediting.config;

import com.collab.collabediting.handlers.CustomAuthenticationSuccessHandler;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration{



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) ->
                        authorize .requestMatchers("/hello").authenticated()
                                .requestMatchers("/**").permitAll()

                                )
                    .oauth2Login(oauth2 -> oauth2.loginPage("/oauth2/authorization/github")
                .successHandler(new CustomAuthenticationSuccessHandler("http://localhost:3000/home")))
                .logout(l -> l.logoutSuccessUrl("/").permitAll())


        ;

      return   http.build();
    }
}
