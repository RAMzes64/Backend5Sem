package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.AvailableRoomTypeRequest;
import com.example.courseWork5REST.DTO.CreateRoomTypeRequest;
import com.example.courseWork5REST.DTO.UpdateRoomTypeRequest;
import com.example.courseWork5REST.Models.RoomType;
import com.example.courseWork5REST.Services.RoomTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomType")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @PostMapping("/add")
    public ResponseEntity addRoomType(@RequestBody CreateRoomTypeRequest request){
        try{
            roomTypeService.addRoomType(request);
            return ResponseEntity.ok("Room type added");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateRoomType(@RequestBody UpdateRoomTypeRequest request){
        try{
            roomTypeService.updateRoomType(request);
            return ResponseEntity.ok("Room type updated");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteRoomType(
            @NonNull @PathVariable Integer id
    ){
        try {
            roomTypeService.deleteRoomType(id);
            return ResponseEntity.ok("Room type deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<RoomType> getAll(){
        return roomTypeService.getAll();
    }

    @RequestMapping("/getAvailable")
    public ResponseEntity getAvailable(@RequestBody AvailableRoomTypeRequest request){
        try {
            return ResponseEntity.ok(roomTypeService.getAvailableRoomsByHotel(request));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
