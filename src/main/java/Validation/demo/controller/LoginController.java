package Validation.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Validation.demo.entity.Client;
import Validation.demo.repository.ClientRepository;
import Validation.demo.response.LoginResponse;

@RestController
public class LoginController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> validateLogin(@RequestBody Client client) {
       
        Client existingClient = clientRepository.findByClientname(client.getClientname()).orElse(null);

      
        if (existingClient != null && existingClient.getPassword().equals(client.getPassword())) {
          
            if (existingClient.getStatus() == 1) {
                LoginResponse response = new LoginResponse("Authentication successful", true);
                return new ResponseEntity<>(response, HttpStatus.OK);  
            } else {
                LoginResponse response = new LoginResponse("The client is inactive", false);
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN); 
            }
        }

        
        LoginResponse response = new LoginResponse("Invalid credentials", false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); 
    }
}
