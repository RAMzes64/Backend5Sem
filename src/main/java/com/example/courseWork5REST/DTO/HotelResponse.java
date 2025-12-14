package com.example.courseWork5REST.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class HotelResponse {
    private Integer id;
    private String name;
    private String address;
    private BigDecimal rating;
}
