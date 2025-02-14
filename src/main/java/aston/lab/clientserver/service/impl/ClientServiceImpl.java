package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.dto.ClientDto;
import aston.lab.clientserver.entity.Client;
import aston.lab.clientserver.mapper.ClientMapper;
import aston.lab.clientserver.repository.ClientRepository;
import aston.lab.clientserver.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDto saveClient(ClientDto clientDto) {
        log.info("Saving client: {}", clientDto);
        try {
            Client client = clientMapper.toEntity(clientDto);
            Client savedClient = clientRepository.save(client);
            return clientMapper.toDto(savedClient);
        } catch (Exception e) {
            log.error("Error saving client: {}", e.getMessage(), e);
            throw new RuntimeException("Ошибка при сохранении клиента", e);
        }    }
}
