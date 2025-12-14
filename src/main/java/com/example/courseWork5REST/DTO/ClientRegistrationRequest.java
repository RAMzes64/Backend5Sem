package com.example.courseWork5REST.DTO;

import com.example.courseWork5REST.Models.Client;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ClientRegistrationRequest {
    private String login;
    private String email;
    private String password;

    public Client toEntity(){
        Client client = new Client();
        client.setLogin(this.login);
        client.setEmail(this.email);
        client.setPassword(this.password);
        return client;
    }
}
