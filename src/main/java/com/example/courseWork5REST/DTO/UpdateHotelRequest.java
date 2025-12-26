package com.example.courseWork5REST.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class UpdateHotelRequest {
    private Integer id;
    private String name;
    private String address;
    private Integer houseNumber;
    private String city;
    private Map<String, String> img;
    private BigDecimal rating;
    private String description;
}
