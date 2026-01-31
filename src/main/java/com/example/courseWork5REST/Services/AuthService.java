package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.CreateStaffRequest;
import com.example.courseWork5REST.DTO.AuthRequest;
import com.example.courseWork5REST.DTO.AuthResponse;
import com.example.courseWork5REST.DTO.ClientRegistrationRequest;
import com.example.courseWork5REST.Models.Client;
import com.example.courseWork5REST.Models.Hotel;
import com.example.courseWork5REST.Models.Role;
import com.example.courseWork5REST.Models.Staff;
import com.example.courseWork5REST.Repositories.ClientRepo;
import com.example.courseWork5REST.Repositories.HotelRepo;
import com.example.courseWork5REST.Repositories.RoleRepo;
import com.example.courseWork5REST.Repositories.StaffRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final ClientRepo clientRepo;
    private final StaffRepo staffRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse clientRegister(
            ClientRegistrationRequest request
    ) throws Exception {
        if (clientRepo.existsByLogin(request.getLogin()) ||
                staffRepo.existsByLogin(request.getLogin()))
            throw new Exception("Username is already taken");

        if (clientRepo.existsByEmail(request.getEmail()) ||
                staffRepo.existsByEmail(request.getEmail()))
            throw new Exception("Email is already in use");

        Client client = request.toEntity();
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        clientRepo.save(client);

        return AuthResponse.builder()
                .token(jwtService.generateToken(client))
                .role("CLIENT")
                .build();
    }

    public AuthResponse authenticate(
            AuthRequest request
    ) throws UsernameNotFoundException {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        Optional<Staff> staff = staffRepo.findByLogin(request.getLogin());

        if (staff.isPresent())
            return AuthResponse.builder()
                    .token(jwtService.generateToken(staff.get()))
                    .role(staff.get().getRole().getName())
                    .build();

        Optional<Client> client = clientRepo.findByLogin(request.getLogin());
        if (client.isPresent())
            return AuthResponse.builder()
                    .token(jwtService.generateToken(client.get()))
                    .role("CLIENT")
                    .build();



        throw new UsernameNotFoundException("Пользователь не найден " + request.getLogin());
    }

    public boolean matchPasswords(String requestPassword, String currentPassword){
        return passwordEncoder.matches(requestPassword, currentPassword);
    }

    public void changePassword(Client client, String newPassword){
        client.setPassword(passwordEncoder.encode(newPassword));
        clientRepo.save(client);
    }
}


