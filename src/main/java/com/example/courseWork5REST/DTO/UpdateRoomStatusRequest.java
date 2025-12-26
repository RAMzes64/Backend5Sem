package com.example.courseWork5REST.DTO;

import com.example.courseWork5REST.Models.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateRoomStatusRequest {
    private Integer id;
    private RoomStatus status;
}
