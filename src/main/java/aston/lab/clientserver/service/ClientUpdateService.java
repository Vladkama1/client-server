package aston.lab.clientserver.service;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.dto.request.ClientRequestDto;
import aston.lab.clientserver.dto.response.ClientResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface ClientUpdateService {
    Optional<Client> findById(UUID clientId);

    Client save(Client client);

    ClientResponseDto updateClient(UUID clientId, ClientRequestDto clientRequestDto);

}
