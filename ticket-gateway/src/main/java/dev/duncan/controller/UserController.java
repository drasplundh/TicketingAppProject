package dev.duncan.controller;

import dev.duncan.domain.User;
import dev.duncan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value="/addUser")
    public User addUser(@RequestBody User user) {
        System.out.println("addUser called");
       return userService.saveUser(user);
    }

    @RequestMapping(value="/userForm.html")
    public String userForm() {
        return "userForm.html";
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam(value="userId", required = true) int id) {
        System.out.println("getUserById called");
        return userService.getUserById(id);
    }

    @GetMapping("/getUserEmailById")
    public String getUserEmailById(@RequestParam(value="userId", required = true) int id) {
        User user = userService.getUserById(id);
        String email = user.getEmail();
        System.out.println("user email: " + email);
        return email;
    }



    @GetMapping("/getUsername")
    public ResponseEntity<String> getUsername() {
        // Retrieve the username of the logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(username);
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<User> getUserByUsername(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserId")
    public int userId(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return user.getUserId();
    }
}
