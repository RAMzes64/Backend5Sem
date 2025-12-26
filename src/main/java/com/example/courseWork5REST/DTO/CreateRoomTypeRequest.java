package com.example.courseWork5REST.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.util.*;


@Data
public class CreateRoomTypeRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer amountOfGuests;
    private Map<String, Object> details;
    private Map<String, String> img;
}
