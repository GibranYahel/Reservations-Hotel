package com.hotel.reservations.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room habitacion) {
        return roomRepository.save(habitacion);
    }

    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }

}
