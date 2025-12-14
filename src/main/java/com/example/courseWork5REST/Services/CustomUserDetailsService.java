package com.example.courseWork5REST.Services;

import com.example.courseWork5REST.Models.Client;
import com.example.courseWork5REST.Models.Staff;
import com.example.courseWork5REST.Repositories.ClientRepo;
import com.example.courseWork5REST.Repositories.StaffRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StaffRepo staffRepo;
    private final ClientRepo clientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Staff staff = staffRepo.findByLogin(username).orElse(null);

        if (staff != null)
            return staff;

        Client client = clientRepo.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден " + username));

        return client;
    }
}
