package com.example.courseWork5REST.DTO;

import com.example.courseWork5REST.Models.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomResponse {

    private Integer id;

    private Integer typeId;
    private String typeName;

    private Integer hotelId;
    private String hotelName;

    private RoomStatus status;
}
