package com.example.courseWork5REST.DTO;

import lombok.Data;

@Data
public class UpdateClientRequest {
    private String login;
    private String email;
}
