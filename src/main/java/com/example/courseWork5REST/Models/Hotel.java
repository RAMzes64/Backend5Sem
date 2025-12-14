package com.example.courseWork5REST.Models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String address;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2) CHECK (rating > 0)")
    private BigDecimal rating;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Staff> staff = new ArrayList<>();
}
