package com.example.courseWork5REST.DTO;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class StaffResponse {
    private Integer id;
    private String login;
    private String role;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Integer hotelId;
    private String hotelName;
}
