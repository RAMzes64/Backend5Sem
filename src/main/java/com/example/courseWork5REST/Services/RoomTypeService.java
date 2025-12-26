package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.AvailableRoomTypeRequest;
import com.example.courseWork5REST.DTO.CreateRoomTypeRequest;
import com.example.courseWork5REST.DTO.UpdateRoomTypeRequest;
import com.example.courseWork5REST.Models.RoomType;
import com.example.courseWork5REST.Repositories.HotelRepo;
import com.example.courseWork5REST.Repositories.RoomTypeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService {

    private final RoomTypeRepo roomTypeRepo;
    private final HotelRepo hotelRepo;

    public void addRoomType(CreateRoomTypeRequest request) throws RuntimeException {
        if (roomTypeRepo.existsByName(request.getName()))
            throw new EntityNotFoundException("Room type name is already taken");

        RoomType roomType = RoomType
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .amountOfGuests(request.getAmountOfGuests())
                .details(request.getDetails())
                .img(request.getImg())
                .build();

        roomTypeRepo.save(roomType);
    }

    public void deleteRoomType(Integer id) throws RuntimeException {
        if (!roomTypeRepo.existsById(id))
            throw new EntityNotFoundException("Room type is not exist");

        roomTypeRepo.deleteById(id);
    }

    public void updateRoomType(UpdateRoomTypeRequest request) throws RuntimeException {
        RoomType roomType = roomTypeRepo.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Room type is not exist"));

        roomType.setId(request.getId());
        roomType.setName(request.getName());
        roomType.setDescription(request.getDescription());
        roomType.setPrice(request.getPrice());
        roomType.setDetails(request.getDetails());
        roomType.setImg(request.getImg());

        roomTypeRepo.save(roomType);
    }

    public List<RoomType> getAll() {
        return roomTypeRepo.findAll();
    }

    public List<RoomType> getAvailableRoomsByHotel(
            AvailableRoomTypeRequest request
    ) throws EntityNotFoundException {
        if (!hotelRepo.existsById(request.getHotelId()))
            throw new EntityNotFoundException("Hotel is not found");

        return roomTypeRepo.getAvailableByHotel(
                request.getHotelId(),
                request.getCheckInDate(),
                request.getCheckOutDate()
        );
    }
}
