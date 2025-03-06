package aston.lab.clientserver.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Table(name = "okved")
@Entity
@Data
@NoArgsConstructor
public class ClientOkveds {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @NotNull
    private UUID id;

    @Column(name = "code", nullable = false)
    @NotNull
    private String code;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

}