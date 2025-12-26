package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.*;
import com.example.courseWork5REST.Models.Hotel;
import com.example.courseWork5REST.Models.Room;
import com.example.courseWork5REST.Models.RoomStatus;
import com.example.courseWork5REST.Models.RoomType;
import com.example.courseWork5REST.Repositories.HotelRepo;
import com.example.courseWork5REST.Repositories.RoomRepo;
import com.example.courseWork5REST.Repositories.RoomTypeRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepo roomRepo;
    private final RoomTypeRepo roomTypeRepo;
    private final HotelRepo hotelRepo;

    @Transactional
    public void addRoom(CreateRoomRequest request) throws EntityNotFoundException{
        RoomType roomType = roomTypeRepo.findById(request.getTypeId())
                .orElseThrow(() ->new EntityNotFoundException("Room type not found"));

        Hotel hotel = hotelRepo.findById(request.getHotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        Room room = Room
                .builder()
                .type(roomType)
                .hotel(hotel)
                .status(RoomStatus.AVAILABLE)
                .build();
        roomRepo.save(room);
    }

    public void updateStatus(
            UpdateRoomStatusRequest request
    ) throws EntityNotFoundException{
        Room room = roomRepo.findById(request.getId())
                .orElseThrow( () -> new EntityNotFoundException("Room not found"));

        room.setStatus(request.getStatus());
        roomRepo.save(room);
    }

    public void updateRoom(UpdateRoomRequest request) throws EntityNotFoundException{
        Room room = roomRepo.findById(request.getId())
                .orElseThrow( () -> new EntityNotFoundException("Room not found"));

        Hotel hotel = hotelRepo.findById(request.getHotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        RoomType roomType = roomTypeRepo.findById(request.getTypeId())
                .orElseThrow(() ->new EntityNotFoundException("Room type not found"));

        room.setHotel(hotel);
        room.setType(roomType);
        room.setStatus(request.getStatus());

        roomRepo.save(room);
    }

    public void deleteRoom(Integer id) throws EntityNotFoundException{
        if(!roomRepo.existsById(id))
            throw new EntityNotFoundException("Room not found");

        roomRepo.deleteById(id);
    }

    public List<RoomResponse> getAllByHotel(Integer id) throws  EntityNotFoundException{
        if(!hotelRepo.existsById(id)) {
            throw new EntityNotFoundException("Hotel not found");
        }

        return roomRepo.findRoomResponseByHotelId(id);
    }

    public RoomResponse getById(
            Integer id
    ) throws EntityNotFoundException {
        if(!roomRepo.existsById(id)) {
            throw new EntityNotFoundException("Room not found");
        }
        return roomRepo.findRoomResponseById(id);
    }
}
