package com.kemal.spring.configuration;

import com.kemal.spring.service.userDetails.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    // Bean for Password Encoder
    @Bean(name = "mainBCryptPasswordEncoder") // Change the name here
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // Bean for DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    // Configure SecurityFilterChain instead of extending WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/plugins/**", "/images/**", "/index", "/", "/register", "/excel", "/csv", "/submit-registration", "/submit-survey/**").permitAll()
                        .requestMatchers("/adminPage/**").hasRole("ADMIN")
                        .requestMatchers("/adminPage/**").hasRole("COLLECTION")
                        .requestMatchers("/userPage/**").hasRole("USER")
                        .requestMatchers("/sudPage/**").hasRole("SUD_USER")
                        .requestMatchers("/sudadminPage/**").hasRole("SUD_ADMIN")
                        .requestMatchers("/surveyuserPage/**").hasRole("SURVEY_ADMIN")
                        .requestMatchers("/surveyadminPage/**").hasRole("SURVEY_USER")
                        .requestMatchers("/administratoradminPage/**").hasRole("ADMINISTRATOR_ADMIN")
                        .requestMatchers("/administratoruserPage/**").hasRole("ADMINISTRATOR_USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/perform-login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login-error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }

    // Bean for AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
