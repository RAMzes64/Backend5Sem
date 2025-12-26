package com.example.courseWork5REST.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class UpdateRoomTypeRequest {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Map<String, Object> details;
    private Map<String, String> img;
}
