package dev.duncan.service;

import dev.duncan.domain.Role;
import dev.duncan.domain.User;
import dev.duncan.repository.RoleRepository;
import dev.duncan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleService roleService;

    public User saveUser(User user) {
        List<Role> passedRoles = user.getRoles();
        List<Role> giveRoles = new ArrayList<>();
        for (Role role : passedRoles) {
            giveRoles.add(roleService.findRoleById(role.getRoleId()));
        }
        for (Role role : giveRoles) {
            System.out.println("Roles found: " + role);
        }
        user.setRoles(giveRoles);
        return userRepo.save(user);
    }

    public User getUserById(int id) {
        // todo add error checking
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
