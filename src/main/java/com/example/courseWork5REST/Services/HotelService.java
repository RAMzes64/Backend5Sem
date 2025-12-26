package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.CreateHotelRequest;
import com.example.courseWork5REST.DTO.HotelResponse;
import com.example.courseWork5REST.DTO.UpdateHotelRequest;
import com.example.courseWork5REST.Models.Hotel;
import com.example.courseWork5REST.Repositories.HotelRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepo hotelRepo;

    public void addHotel(CreateHotelRequest request) throws RuntimeException{
        if (hotelRepo.existsByName(request.getName()))
            throw new EntityNotFoundException("Hotel name is already taken");

        if (hotelRepo.existsByAddressAndHouseNumber(request.getAddress(), request.getHouseNumber()))
            throw new EntityNotFoundException("There is already a hotel with this address");

        Hotel hotel = Hotel
                .builder()
                .name(request.getName())
                .address(request.getAddress())
                .houseNumber(request.getHouseNumber())
                .city(request.getCity())
                .img(request.getImg())
                .description(request.getDescription())
                .build();
        hotelRepo.save(hotel);
    }

    public void deleteHotel(Integer id) throws RuntimeException {
        if (!hotelRepo.existsById(id))
            throw new EntityNotFoundException("There is no such hotel");

        hotelRepo.deleteById(id);
    }

    public List<HotelResponse> getAllHotels(){
        return hotelRepo.findAll().stream().map(
                hotel -> HotelResponse
                        .builder()
                        .id(hotel.getId())
                        .name(hotel.getName())
                        .address(hotel.getAddress())
                        .city(hotel.getCity())
                        .houseNumber(hotel.getHouseNumber())
                        .rating(hotel.getRating())
                        .img(hotel.getImg())
                        .description(hotel.getDescription())
                        .build())
                .toList();
    }

    public void updateHotel(UpdateHotelRequest request) throws RuntimeException {
        Hotel hotel = hotelRepo.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("There is no such hotel"));

        hotel.setName(request.getName());
        hotel.setAddress(request.getAddress());
        hotel.setRating(request.getRating());
        hotel.setHouseNumber(request.getHouseNumber());
        hotel.setImg(request.getImg());
        hotel.setCity(request.getCity());
        hotel.setDescription(request.getDescription());

        hotelRepo.save(hotel);
    }

    public void updateRating(
            BigDecimal newRating,
            Integer id
    ) throws RuntimeException{
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no such hotel"));

        hotel.setRating(newRating);

        hotelRepo.save(hotel);
    }

    public List<HotelResponse> getByCity(
            String city
    ) throws EntityNotFoundException {
        return hotelRepo.findAllByCity(city);
    }

    public List<String> getCities(){
        return hotelRepo.findDistinctCities();
    }

    public HotelResponse getById(Integer id) throws EntityNotFoundException{
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel is not found"));
        HotelResponse response = HotelResponse
                .builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .address(hotel.getAddress())
                .city(hotel.getCity())
                .houseNumber(hotel.getHouseNumber())
                .rating(hotel.getRating())
                .img(hotel.getImg())
                .description(hotel.getDescription())
                .build();
        return response;
    }
}
