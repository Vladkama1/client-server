package aston.lab.clientserver.data.repository;

import aston.lab.clientserver.data.model.ClientOkveds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientOkvedRepository extends JpaRepository<ClientOkveds, UUID> {

}
