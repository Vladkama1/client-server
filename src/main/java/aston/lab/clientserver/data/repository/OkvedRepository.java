package aston.lab.clientserver.data.repository;

import aston.lab.clientserver.data.model.Okved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OkvedRepository extends JpaRepository<Okved, UUID> {
}
