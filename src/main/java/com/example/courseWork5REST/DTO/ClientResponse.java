package com.example.courseWork5REST.DTO;

import com.example.courseWork5REST.Models.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ClientResponse {
    private String login;
    private String email;
    private List<Booking> bookings;
}
