package com.example.courseWork5REST.DTO;

import com.example.courseWork5REST.Models.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateRoomRequest {
    private Integer id;
    private Integer typeId;
    private Integer hotelId;
    private RoomStatus status;
}
