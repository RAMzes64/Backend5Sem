package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.AddHotelRequest;
import com.example.courseWork5REST.DTO.DelHotelRequest;
import com.example.courseWork5REST.DTO.HotelResponse;
import com.example.courseWork5REST.Models.Hotel;
import com.example.courseWork5REST.Repositories.HotelRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepo hotelRepo;

    public void addHotel(AddHotelRequest request) throws Exception{
        if (hotelRepo.existsByName(request.getName()))
            throw new Exception("Hotel name is already taken");

        if (hotelRepo.existsByAddress(request.getAddress()))
            throw new Exception("There is already a hotel with this address");

        Hotel hotel = Hotel
                .builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
        hotelRepo.save(hotel);
    }

    public void deleteHotel(DelHotelRequest request) throws Exception {
        if (!hotelRepo.existsById(request.getId()))
            throw new Exception("There is no such hotel");

        hotelRepo.deleteById(request.getId());
    }

    public List<HotelResponse> getAllHotels(){
        return hotelRepo.findAll().stream().map(
                hotel -> new HotelResponse(
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getAddress(),
                        hotel.getRating()
                ))
                .toList();
    }
}
