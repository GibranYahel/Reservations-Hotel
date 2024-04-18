package com.hotel.reservations.client;

import com.hotel.reservations.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClient() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable String id) {
        return clientService.getClientById(id).orElse(null);
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable("id") String id, @RequestBody Client clientDetails){
        Client client = clientService.getClientById(id)
                .orElseThrow(() -> new RuntimeException("Client don=t found"));
        return clientService.saveClient(client);
    }

    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody LoginRequest loginRequest) {
        Optional<Client> clientOptional = clientService.getClientByEmail(loginRequest.getEmail());

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            if (client.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(client);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
    }

}
