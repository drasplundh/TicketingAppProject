package dev.duncan.controller;

import dev.duncan.domain.Role;
import dev.duncan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(value="/getRoles")
    public List<Role> getRoles() {
       return roleService.getAllRoles();
    }

    @GetMapping(value="/getRoleById")
    public Role getroleById(@RequestParam(value="roleId") int id) {
        return roleService.findRoleById(id);
    }

}
