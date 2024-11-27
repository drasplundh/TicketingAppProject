package dev.duncan.config;

import dev.duncan.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                .csrf().disable()
                .authorizeHttpRequests()
//                .requestMatchers("/", "/login", "/html/userForm.html", "/addUser", "/html/login.html", "/getUserEmailById", "/getUserById").permitAll()
                    .anyRequest().permitAll()
                 .and()
                 .anonymous()
                .and()
                .formLogin()
                .loginPage("/html/login.html") // Redirect to custom login page
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/html/home.html", true)
                .and()
                .logout().permitAll();


        return http.build();

    }

}
