package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.CreateHotelRequest;
import com.example.courseWork5REST.DTO.HotelResponse;
import com.example.courseWork5REST.DTO.UpdateHotelRequest;
import com.example.courseWork5REST.Services.HotelService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity addHotel(@NonNull @RequestBody CreateHotelRequest request) {
        try {
            hotelService.addHotel(request);
            return ResponseEntity.ok("Hotel added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteHotel(@NonNull @PathVariable Integer id) {
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.ok("Hotel deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/get")
    public ResponseEntity getById(@NonNull @PathVariable Integer id){
        try {
            return ResponseEntity.ok(hotelService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<HotelResponse> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping("/update")
    public ResponseEntity updateHotel(
            @NonNull @RequestBody UpdateHotelRequest request
    ) {
        try {
            hotelService.updateHotel(request);
            return ResponseEntity.ok("Hotel data updated");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/updateRating")
    public ResponseEntity updateRating(
            @NonNull @RequestBody BigDecimal newRating,
            @NonNull @PathVariable Integer id){
        try {
            hotelService.updateRating(newRating, id);
            return ResponseEntity.ok("Rating updated");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCities")
    public ResponseEntity getCities(){
        try {
            return ResponseEntity.ok(hotelService.getCities());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{city}/getByCity")
    public ResponseEntity getHotelsByCiti(@PathVariable String city){
        try {
            return ResponseEntity.ok(hotelService.getByCity(city));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
