package com.example.courseWork5REST.DTO;
import com.example.courseWork5REST.Models.OrderStatus;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderResponse {
    private Integer id;
    private Timestamp orderDate;
    private Date checkInDate;
    private Date checkOutDate;
    private Map<String, Object> details;
    private Integer clientId;
    private Integer roomId;
    private Integer typeId;
    private OrderStatus status;
}
