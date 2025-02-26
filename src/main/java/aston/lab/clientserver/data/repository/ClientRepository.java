package aston.lab.clientserver.data.repository;

import aston.lab.clientserver.data.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @EntityGraph(value = "Client.fullInfo",type = EntityGraph.EntityGraphType.LOAD)
    Optional<Client> findByInnAndOgrn(Long inn, Long ogrn);
}
