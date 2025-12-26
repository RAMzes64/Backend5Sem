package com.example.courseWork5REST.DTO;

import lombok.Data;
import java.sql.Date;
import java.util.Map;

@Data
public class CreateOrderRequest {
    private Date checkInDate;
    private Date checkOutDate;
    private Map<String, Object> details;
    private Integer roomTypeId;
    private Integer hotelId;
}
