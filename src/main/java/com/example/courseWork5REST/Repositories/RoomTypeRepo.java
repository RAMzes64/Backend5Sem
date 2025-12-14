package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.Models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType, Integer> {
}
