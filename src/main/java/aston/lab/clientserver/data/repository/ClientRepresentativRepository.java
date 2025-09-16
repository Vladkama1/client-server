package aston.lab.clientserver.data.repository;

import aston.lab.clientserver.data.model.ClientRepresentativ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepresentativRepository extends JpaRepository<ClientRepresentativ, UUID> {
}
