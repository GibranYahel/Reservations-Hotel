package com.hotel.reservations.reservations;

import com.hotel.reservations.client.Client;
import com.hotel.reservations.client.ClientRepository;
import com.hotel.reservations.client.ClientService;
import com.hotel.reservations.rooms.Room;
import com.hotel.reservations.rooms.RoomRepository;
import com.hotel.reservations.rooms.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable("id") String id) {
        return reservationService.getReservationById(id).orElse(null);
    }

    @PostMapping("/{idClient}/create/{idRoom}")
    public Reservation save(
            @PathVariable("idClient") String idClient,
            @PathVariable("idRoom") String idRoom,
            @RequestBody Reservation reservation) {
        Client client = clientService.getClientById(idClient)
                .orElseThrow(() -> new RuntimeException("Client don't find"));
        Room room = roomService.getRoomById(idRoom)
                .orElseThrow(() -> new RuntimeException("Room don't find"));

        reservation.setClient(client);
        reservation.setRoom(room);

        return reservationService.saveReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") String id, @RequestBody Reservation reservationDetails) {
        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new RuntimeException("Reservation don=t found"));
        return reservationService.saveReservation(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") String id) {
        reservationService.deleteReservation(id);
    }
}
