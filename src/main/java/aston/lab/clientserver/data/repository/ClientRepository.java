package aston.lab.clientserver.data.repository;

import aston.lab.clientserver.data.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @EntityGraph(value = "Client.fullInfo",type = EntityGraph.EntityGraphType.LOAD)

    Optional<Client> findById(UUID uuid);

    Optional<Client> findByInnAndOgrn(String inn, String ogrn);

    List<Client> findAllById(Iterable<UUID> ids);

}
