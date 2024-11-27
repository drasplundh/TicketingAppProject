package dev.duncan.service;

import dev.duncan.domain.Role;
import dev.duncan.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepo;

    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    public Role findRoleById(int id) {
        Optional<Role> existsRole = roleRepo.findById(id);
        if (existsRole.isEmpty()) {
            // TODO throw RoleNotFoundException
            System.out.println("Role not found");
        }
        return existsRole.get();
    }

//    public Role getRoleByUserId
}
