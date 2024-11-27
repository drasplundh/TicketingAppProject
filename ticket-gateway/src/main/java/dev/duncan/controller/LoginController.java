package dev.duncan.controller;

import dev.duncan.util.AuthResponse;
import dev.duncan.util.JwtTokenProvider;
import dev.duncan.util.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class LoginController {

//    @RequestMapping(value = "/login")
//    public String loginForm() {
//        return "login.html";
//    }
//
//    @RequestMapping(value = "/home")
//    public String homepage() {
//        return "home.html";
//    }


        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtTokenProvider jwtTokenProvider;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            System.out.println("username: " + loginRequest.getUsername() + " password: " + loginRequest.getPassword());
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = jwtTokenProvider.generateToken(authentication);
                System.out.println("Token created! " + token);
                return ResponseEntity.ok(new AuthResponse(token));
            } catch (AuthenticationException ex) {
                System.out.println("Authentication failed: " + ex.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("DUNCAN'S MESSAGE FROM LOGIN CONTROLLER, FAILED");
            }
        }
    }

