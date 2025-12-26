package com.example.courseWork5REST.DTO;

import com.example.courseWork5REST.Models.RoomStatus;
import lombok.Data;

@Data
public class CreateRoomRequest {
    private Integer typeId;
    private Integer hotelId;
}
