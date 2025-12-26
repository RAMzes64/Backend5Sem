package com.example.courseWork5REST.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class AvailableRoomTypeRequest {
    Integer hotelId;
    Date checkInDate;
    Date checkOutDate;
}
