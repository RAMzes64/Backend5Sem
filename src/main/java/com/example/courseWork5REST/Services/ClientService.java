package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.AuthResponse;
import com.example.courseWork5REST.DTO.ClientResponse;
import com.example.courseWork5REST.DTO.UpdateClientPasswordRequest;
import com.example.courseWork5REST.DTO.UpdateClientRequest;
import com.example.courseWork5REST.Models.Client;
import com.example.courseWork5REST.Repositories.ClientRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepo clientRepo;
    private final AuthService authService;
    private final JwtService jwtService;

    public ClientResponse getData() throws EntityNotFoundException{
        Client client = getClientFromJWT();
        return ClientResponse.builder()
                .login(client.getLogin())
                .email(client.getEmail())
                .bookings(client.getBookings())
                .build();
    }

    public void delete() throws EntityNotFoundException{
        Client client = getClientFromJWT();
        clientRepo.deleteById(client.getId());
    }

    public AuthResponse update(UpdateClientRequest request) throws Exception {
        Client client = getClientFromJWT();

        client.setEmail(request.getEmail());
        client.setLogin(request.getLogin());
        clientRepo.save(client);

        return AuthResponse.builder().token(jwtService.generateToken(client)).build();
    }

    public void updatePassword(UpdateClientPasswordRequest request) throws Exception{
        Client client = getClientFromJWT();


        if (authService.matchPasswords(request.getOldPassword(), client.getPassword()))
            authService.changePassword(client, request.getNewPassword());
        else throw new Exception("Неверный текущий пароль");
    }

    private Client getClientFromJWT() throws EntityNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated())
            return clientRepo.findByLogin(auth.getName())
                    .orElseThrow(() -> new EntityNotFoundException("Client is not found"));
        return null;
    }

    public List<ClientResponse> getAll(){
        return clientRepo.findAll().stream()
                .map(
                        (client) -> ClientResponse.builder()
                                .bookings(client.getBookings())
                                .email(client.getEmail())
                                .login(client.getEmail())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
