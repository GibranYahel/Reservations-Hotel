package com.hotel.reservations.reservations;

import com.hotel.reservations.client.Client;
import com.hotel.reservations.rooms.Room;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "reservations")
public class Reservation {
    @Id
    private String _id;

    @DBRef
    private Client client;
    @DBRef
    private Room room;

    private String state;
    private double totalPrice;
    private String observations;


    public String get_id() {
        return _id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
