package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.UpdateClientPasswordRequest;
import com.example.courseWork5REST.DTO.UpdateClientRequest;
import com.example.courseWork5REST.Services.ClientService;
import com.example.courseWork5REST.Services.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/get")
    public ResponseEntity getData(){
        try {
            return ResponseEntity.ok(clientService.getData());
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(){
        try {
            clientService.delete();
            return ResponseEntity.ok("Client deleted");
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody UpdateClientRequest request){
        try {
            return ResponseEntity.ok(clientService.update(request));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity updatePassword(@RequestBody UpdateClientPasswordRequest request){
        try {
            clientService.updatePassword(request);
            return ResponseEntity.ok("Client data updated");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(clientService.getAll());
    }
}
