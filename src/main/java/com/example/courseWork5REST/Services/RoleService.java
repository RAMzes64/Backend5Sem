package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.CreateRoleRequest;
import com.example.courseWork5REST.Models.Role;
import com.example.courseWork5REST.Repositories.RoleRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepo roleRepo;

    public void addRole(CreateRoleRequest request) {
        if (roleRepo.existsByName(request.getName())) throw new EntityExistsException("Role exists");

        Role role = Role.builder().name(request.getName()).build();
        roleRepo.save(role);
    }

    public void deleteRole(Integer id){
        Role role = roleRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role does not exists")
        );

        roleRepo.delete(role);
    }

    public List<Role> getAll(){
        return roleRepo.findAll();
    }

    public List<Role> getByHotel(Integer hotelId){
        return roleRepo.findByHotel(hotelId);
    }
}
