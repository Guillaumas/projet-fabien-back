package fr.guigs.api.services;

import fr.guigs.api.models.Role;
import fr.guigs.api.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Long id, Role newRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(newRole.getName());
                    return roleRepository.save(role);
                })
                .orElseGet(() -> {
                    newRole.setId(id);
                    return roleRepository.save(newRole);
                });
    }

    public Role patch(Long id, Role newRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    if (newRole.getName() != null) {
                        role.setName(newRole.getName());
                    }
                    return roleRepository.save(role);
                })
                .orElseGet(() -> {
                    newRole.setId(id);
                    return roleRepository.save(newRole);
                });
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}