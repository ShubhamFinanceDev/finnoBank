package com.kemal.spring.configuration;


import com.kemal.spring.service.userDetails.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    //Beans
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

//    @Bean
//    public RememberMeServices rememberMeServices() {
//        return new CustomRememberMeServices("theKey",
//                userDetailsServiceImpl, new InMemoryTokenRepositoryImpl());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/css/**","/js/**","/js/**", "/plugins/**","/images/**",
                        "/index", "/", "/register","/excel","/csv", "/submit-registration","/submit-survey/**").permitAll()
                .antMatchers("/adminPage/**").hasRole("ADMIN")
                .antMatchers("/adminPage/**").hasRole("COLLECTION")
                .antMatchers("/userPage/**").hasRole("USER")
                .antMatchers("/sudPage/**").hasRole("SUD_USER")
                .antMatchers("/sudadminPage/**").hasRole("SUD_ADMIN")
                .antMatchers("/surveyuserPage/**").hasRole("SURVEY_ADMIN")
                .antMatchers("/surveyadminPage/**").hasRole("SURVEY_USER")
                .antMatchers("/administratoradminPage/**").hasRole("ADMINISTRATOR_ADMIN")
                .antMatchers("/administratoruserPage/**").hasRole("ADMINISTRATOR_USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/perform-login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login-error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

}
