package fr.guigs.api.controllers;

import fr.guigs.api.services.RoleService;
import fr.guigs.api.models.Role;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/roles/")
public class RoleWriteController {

    private final RoleService roleService;

    public RoleWriteController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @PutMapping("{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.update(id, role);
    }

    @PatchMapping("{id}")
    public Role patchRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.patch(id, role);
    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.delete(id);
    }
}