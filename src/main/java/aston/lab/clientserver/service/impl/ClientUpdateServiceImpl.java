package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.model.TelNumber;
import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.dto.request.ClientRequestDto;
import aston.lab.clientserver.dto.response.ClientResponseDto;
import aston.lab.clientserver.service.ClientUpdateService;
import aston.lab.clientserver.service.converter.ClientConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientUpdateServiceImpl implements ClientUpdateService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    @Override
    public Optional<Client> findById(UUID clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public ClientResponseDto updateClient(UUID  clientId, ClientRequestDto clientRequestDto) {
        Client existingClient = clientRepository.findById(clientId).orElseThrow(() -> {
            log.warn("Клиент с ID {} не найден.", clientId);
            return new NoSuchElementException("Клиент не найден");
        });

        clientConverter.updateClientFromDto(clientRequestDto, existingClient);

        updateTelNumbers(existingClient, clientRequestDto.getTelNumber());

        return clientConverter.clientToClientResponseDto(clientRepository.save(existingClient));
    }

    private void updateTelNumbers(Client client, List<String> telNumbers) {
        if (!telNumbers.isEmpty()) {
            client.getTelNumbers().clear();
            for (String telNumberString : telNumbers) {
                TelNumber telNumber = new TelNumber();
                telNumber.setNumber(telNumberString);
                telNumber.setClient(client);
                client.getTelNumbers().add(telNumber);
            }
        }
    }
}
