package Validation.demo.repository;
import Validation.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ClientRepository extends JpaRepository<Client, String>{
    Optional<Client> findByClientname(String username);
    
}