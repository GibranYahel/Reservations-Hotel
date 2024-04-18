package com.hotel.reservations.client;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "clients")
public class Client {

    @Id
    private String idClient;

    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }
}
