package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.CreateOrderRequest;
import com.example.courseWork5REST.DTO.OrderResponse;
import com.example.courseWork5REST.Models.*;
import com.example.courseWork5REST.Repositories.ClientRepo;
import com.example.courseWork5REST.Repositories.BookingRepo;
import com.example.courseWork5REST.Repositories.RoomRepo;
import com.example.courseWork5REST.Repositories.RoomTypeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;
    private final ClientRepo clientRepo;
    private final RoomTypeRepo roomTypeRepo;

    public void add(CreateOrderRequest request) throws EntityNotFoundException {
        if(!roomTypeRepo.existsById(request.getRoomTypeId()))
            throw new EntityNotFoundException("Room type is not found");

        Client client = getClientFromJWT();

        Date currentDate = new Date(System.currentTimeMillis());
        if (request.getCheckInDate().before(currentDate))
            throw new IllegalArgumentException("Check-in date cannot be in the past");

        Pageable firstRoom = PageRequest.of(0, 1, Sort.by("id").ascending());

        Page<Room> roomPage = roomRepo.findRoomByTypeAndHotel(
                request.getRoomTypeId(),
                request.getHotelId(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                firstRoom
        );

        if (!roomPage.hasContent())
            throw new EntityNotFoundException("No rooms");

        Booking booking = Booking
                .builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .details(request.getDetails())
                .client(client)
                .room(roomPage.getContent().get(0))
                .status(OrderStatus.PENDING)
                .orderDate(new Timestamp(System.currentTimeMillis()))
                .build();

        bookingRepo.save(booking);
    }

    public void changeStatus(Integer orderId, OrderStatus status) {
        Booking booking = bookingRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order  is not found"));

        booking.setStatus(status);
        bookingRepo.save(booking);
    }

    public void delete(Integer orderId) {
        if (!bookingRepo.existsById(orderId))
            throw new EntityNotFoundException("Order  is not found");

        bookingRepo.deleteById(orderId);
    }

    public List<OrderResponse> getAll() {
        return bookingRepo.findAllOrderResponse();
    }

    public List<OrderResponse> getAllByStatus(OrderStatus status) {
        return bookingRepo.findByStatusOrderResponse(status);
    }

    public OrderResponse getById(Integer id) throws EntityNotFoundException {
        return bookingRepo.findByIdOrderResponse(id).orElseThrow(() -> new EntityNotFoundException("Order  is not found"));
    }

    public List<OrderResponse> getByClientId(Integer id) throws EntityNotFoundException {
        if (!clientRepo.existsById(id))
            throw new EntityNotFoundException("Client is not found");

        return bookingRepo.findByClientIdOrderResponse(id);
    }

    private Client getClientFromJWT() throws EntityNotFoundException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated())
            return clientRepo.findByLogin(auth.getName())
                    .orElseThrow(() -> new EntityNotFoundException("Client is not found"));
        return null;
    }
}
