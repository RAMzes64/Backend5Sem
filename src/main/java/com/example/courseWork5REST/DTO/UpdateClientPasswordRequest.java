package com.example.courseWork5REST.DTO;

import lombok.Data;

@Data
public class UpdateClientPasswordRequest {
    private String newPassword;
    private String oldPassword;
}
