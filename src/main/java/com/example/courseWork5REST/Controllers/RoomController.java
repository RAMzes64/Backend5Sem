package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.CreateRoomRequest;
import com.example.courseWork5REST.DTO.RoomResponse;
import com.example.courseWork5REST.DTO.UpdateRoomRequest;
import com.example.courseWork5REST.DTO.UpdateRoomStatusRequest;
import com.example.courseWork5REST.Services.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CreateRoomRequest request){
        try{
            roomService.addRoom(request);
            return ResponseEntity.ok("Room added");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/updateStatus")
    public ResponseEntity updateStatus(
            @RequestBody UpdateRoomStatusRequest request
    ){
        try{
            roomService.updateStatus(request);
            return ResponseEntity.ok("Room status updated");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody UpdateRoomRequest request){
        try{
            roomService.updateRoom(request);
            return ResponseEntity.ok("Room data updated");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity delete(@NonNull @PathVariable Integer id){
        try{
            roomService.deleteRoom(id);
            return ResponseEntity.ok("Room deleted");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("getByHotel/{id}")
    public List<RoomResponse> getAllByHotel(@NonNull @PathVariable Integer id){
        return roomService.getAllByHotel(id);
    }

    @GetMapping("/{id}/get")
    public RoomResponse getById(@NonNull @PathVariable Integer id){
        return roomService.getById(id);
    }
}
