package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.DTO.CreateStaffRequest;
import com.example.courseWork5REST.DTO.StaffResponse;
import com.example.courseWork5REST.DTO.UpdateStaffRequest;
import com.example.courseWork5REST.Models.Hotel;
import com.example.courseWork5REST.Models.Role;
import com.example.courseWork5REST.Models.Staff;
import com.example.courseWork5REST.Repositories.ClientRepo;
import com.example.courseWork5REST.Repositories.HotelRepo;
import com.example.courseWork5REST.Repositories.RoleRepo;
import com.example.courseWork5REST.Repositories.StaffRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StaffService {
    private final StaffRepo staffRepo;
    private final ClientRepo clientRepo;
    private final RoleRepo roleRepo;
    private final HotelRepo hotelRepo;
    private final PasswordEncoder passwordEncoder;

    public void addStaff(CreateStaffRequest request) throws EntityNotFoundException {
        if (clientRepo.existsByLogin(request.getLogin()) ||
                staffRepo.existsByLogin(request.getLogin()))
            throw new EntityNotFoundException("Username is already taken");

        if (clientRepo.existsByEmail(request.getEmail()) ||
                staffRepo.existsByEmail(request.getEmail()))
            throw new EntityNotFoundException("Email is already in use");

        Role role = roleRepo.findByName(request.getRole()).orElseThrow(
                () -> new EntityNotFoundException("Role does not found"));

        Hotel hotel = hotelRepo.findById(request.getHotelId()).orElseThrow(
                () -> new EntityNotFoundException("Hotel does not found")
        );

        Staff staff = new Staff();
        staff.setLogin(request.getLogin());
        staff.setEmail(request.getEmail());
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setBirthDate(request.getBirthDate());
        staff.setPassword(passwordEncoder.encode(request.getPassword()));
        staff.setRole(role);
        staff.setHotel(hotel);

        staffRepo.save(staff);
    }

    public List<StaffResponse> getAll(){
        return staffRepo.findAll().stream().map(
                staff ->
                    StaffResponse.builder()
                            .email(staff.getEmail())
                            .login(staff.getLogin())
                            .birthDate(staff.getBirthDate())
                            .firstName(staff.getFirstName())
                            .lastName(staff.getLastName())
                            .hotelId(staff.getHotel().getId())
                            .hotelName(staff.getHotel().getName())
                            .role(staff.getRole().getName())
                            .build()
                ).collect(Collectors.toList());
    }

    public List<StaffResponse> getByHotel(Integer hotelId){
        return staffRepo.findByHotel(hotelId);
    }

    public void updateStaff(UpdateStaffRequest request){
        Staff staff = staffRepo.findById(request.getId()).orElseThrow(
                () -> new EntityNotFoundException("Staff does not found")
        );

        Role role = roleRepo.findByName(request.getRole()).orElseThrow(
                () -> new EntityNotFoundException("Role does not found")
        );

        Hotel hotel = hotelRepo.findById(request.getHotelId()).orElseThrow(
                () -> new EntityNotFoundException("Hotel does not found")
        );

        staff.setLogin(request.getLogin());
        staff.setEmail(request.getEmail());
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setBirthDate(request.getBirthDate());
        staff.setRole(role);
        staff.setHotel(hotel);

        staffRepo.save(staff);
    }

    public void deleteStaff(Integer id){
        if(!staffRepo.existsById(id))
            throw new EntityNotFoundException("Staff does not found");

        staffRepo.deleteById(id);
    }
}
