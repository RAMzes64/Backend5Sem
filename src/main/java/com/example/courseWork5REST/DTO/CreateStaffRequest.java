package com.example.courseWork5REST.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateStaffRequest {
    private String login;
    private String password;
    private String role;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Integer hotelId;
}
