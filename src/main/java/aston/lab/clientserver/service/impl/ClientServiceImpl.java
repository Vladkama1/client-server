package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.service.ClientService;
import aston.lab.clientserver.service.converter.ClientConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;


    @Override
    public ClientFindByInnAndOgrnResponseDto findClientByInnAndOgrn(Long inn, Long ogrn) {

        checkValidationInnAndOrgn(inn, ogrn);

        Client client = clientRepository.findByInnAndOgrn(inn, ogrn)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));
        log.info("Client is found: {}", client);
        return clientConverter.clientFindByInnAndOgrnResponseDto(client);
    }

    private void checkValidationInnAndOrgn(Long inn, Long ogrn) {

        int innLength = String.valueOf(inn).length();

        int ogrnLength = String.valueOf(ogrn).length();

        if ((innLength != 10 && innLength != 12) || (ogrnLength != 13 && ogrnLength != 15)) {
            throw new CheckValidationException("Неверные параметры запроса");
        }
    }

}
