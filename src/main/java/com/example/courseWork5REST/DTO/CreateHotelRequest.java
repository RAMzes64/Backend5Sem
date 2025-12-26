package com.example.courseWork5REST.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateHotelRequest {
    private String name;
    private String address;
    private Integer houseNumber;
    private String city;
    private String description;
    private Map<String, String> img;
}
