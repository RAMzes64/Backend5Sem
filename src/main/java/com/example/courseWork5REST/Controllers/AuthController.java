package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.AuthRequest;
import com.example.courseWork5REST.DTO.ClientRegistrationRequest;
import com.example.courseWork5REST.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @RequestMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientRegistrationRequest request){
        try {
            return ResponseEntity.ok(authService.clientRegister(request));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request){
        try{
            return ResponseEntity.ok(authService.authenticate(request));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
