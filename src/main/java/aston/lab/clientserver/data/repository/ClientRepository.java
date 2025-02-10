package aston.lab.clientserver.data.repository;

import aston.lab.clientserver.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
