package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.CreateRoleRequest;
import com.example.courseWork5REST.Services.RoleService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity addRole(@RequestBody CreateRoleRequest request){
        try {
            roleService.addRole(request);
            return ResponseEntity.ok("Role created");
        }
        catch (EntityExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") Integer id){
        try {
            roleService.deleteRole(id);
            return ResponseEntity.ok("Role deleted");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity getByHotel(@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(roleService.getByHotel(id));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(roleService.getAll());
    }
}
