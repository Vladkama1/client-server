package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.dto.request.ActivationStatusRequest;
import aston.lab.clientserver.dto.response.ActivationStatusResponse;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.service.ClientService;
import aston.lab.clientserver.service.converter.ClientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    @Override
    public ClientFindByInnAndOgrnResponseDto findClientByInnAndOgrn(String inn, String ogrn) {

        checkValidationInnAndOrgn(inn, ogrn);

        Client client = clientRepository.findByInnAndOgrn(inn, ogrn)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));
        log.info("Client is found: {}", client);
        return clientConverter.clientFindByInnAndOgrnResponseDto(client);
    }

    private void checkValidationInnAndOrgn(String inn, String ogrn) {

        int innLength = String.valueOf(inn).length();

        int ogrnLength = String.valueOf(ogrn).length();

        if ((innLength != 10 && innLength != 12) || (ogrnLength != 13 && ogrnLength != 15)) {
            throw new CheckValidationException("Неверные параметры запроса");
        }
    }

    @Override
    public ActivationStatusResponse updateActivationStatus(ActivationStatusRequest request) {
        List<Client> clients = clientRepository.findAllById(request.getUpdatedClients());

        // Проверяем, что ВСЕ UUID найдены
        List<UUID> foundClientIds = clients.stream().map(Client::getId).toList();
        List<UUID> missingClients = request.getUpdatedClients().stream()
                .filter(id -> !foundClientIds.contains(id))
                .toList();

        if (!missingClients.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректные параметры запроса");
        }

        clients.forEach(client -> client.setIsActive(request.getIsActive()));
        clientRepository.saveAll(clients);

        List<ActivationStatusResponse.ClientStatus> updatedClients = clients.stream()
                .map(client -> new ActivationStatusResponse.ClientStatus(client.getId(), client.getIsActive()))
                .collect(Collectors.toList());

        return ActivationStatusResponse.builder().updatedClients(updatedClients).build();
    }

}
