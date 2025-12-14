package com.example.courseWork5REST.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AddHotelRequest {
    private String name;
    private String address;
}
