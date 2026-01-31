package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.CreateStaffRequest;
import com.example.courseWork5REST.DTO.UpdateStaffRequest;
import com.example.courseWork5REST.Services.StaffService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/add")
    public ResponseEntity addStaff(@RequestBody CreateStaffRequest request) {
        try {
            staffService.addStaff(request);
            return ResponseEntity.ok("");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(staffService.getAll());
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity getByHotel(@PathVariable("id") Integer id){
        return ResponseEntity.ok(staffService.getByHotel(id));
    }

    @PatchMapping("/update")
    public ResponseEntity updateStaff(@RequestBody UpdateStaffRequest request){
        try {
            staffService.updateStaff(request);
            return ResponseEntity.ok("Staff data has been updated");
        }
        catch (EntityNotFoundException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStaff(@PathVariable("id") Integer id){
        try {
            staffService.deleteStaff(id);
            return ResponseEntity.ok("Staff data has been deleted");
        }
        catch (EntityNotFoundException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
