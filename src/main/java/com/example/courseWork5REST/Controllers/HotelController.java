package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.AddHotelRequest;
import com.example.courseWork5REST.DTO.DelHotelRequest;
import com.example.courseWork5REST.DTO.HotelRequest;
import com.example.courseWork5REST.DTO.HotelResponse;
import com.example.courseWork5REST.Services.HotelService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/addHotels")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addHotel(@NonNull @RequestBody AddHotelRequest request){
        try {
            hotelService.addHotel(request);
            return ResponseEntity.ok("Hotel added");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteHotels")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteHotel(@NonNull @RequestBody DelHotelRequest request){
        try{
            hotelService.deleteHotel(request);
            return ResponseEntity.ok("Hotel deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllHotels")
    public List<HotelResponse> getAllHotels(){
        return hotelService.getAllHotels();
    }
}
