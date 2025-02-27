package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.BusinessVolume;
import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.model.ClientRepresentativ;
import aston.lab.clientserver.data.model.FormOwnership;
import aston.lab.clientserver.data.model.Okved;
import aston.lab.clientserver.data.model.TelNumber;
import aston.lab.clientserver.data.repository.BusinessVolumeRepository;
import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.data.repository.ClientRepresentativRepository;
import aston.lab.clientserver.data.repository.FormOwnershipRepository;
import aston.lab.clientserver.data.repository.OkvedRepository;
import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import aston.lab.clientserver.exception.NotFoundException;
import aston.lab.clientserver.mapper.ClientMapper;
import aston.lab.clientserver.mapper.TelNumberMapper;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.service.ClientService;
import aston.lab.clientserver.service.TelNumberService;
import aston.lab.clientserver.service.converter.ClientConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final TelNumberService telNumberService;
    private final BusinessVolumeRepository businessVolumeRepository;
    private final ClientRepresentativRepository clientRepresentativRepository;
    private final FormOwnershipRepository formOwnershipRepository;
    private final OkvedRepository okvedRepository;
    private final ClientMapper clientMapper;
    private final TelNumberMapper telNumberMapper;
    private final ClientConverter clientConverter;


    @Override
    @Transactional
    public ResponseClientDto saveClient(RequestClientDto requestClientDto, String requestEmployeeId) {
        Client client = clientMapper.toEntity(requestClientDto);
        Okved okved = okvedRepository.findById(UUID.fromString(requestClientDto.getOkvedId()))
                .orElseThrow(() -> new NotFoundException("Okved not found"));
        BusinessVolume businessVolume = businessVolumeRepository.findById(UUID.fromString(requestClientDto.getBusinessVolId()))
                .orElseThrow(() -> new NotFoundException("BusinessVolume not found"));
        ClientRepresentativ clientRepresentativ = clientRepresentativRepository.findById(UUID.fromString(requestClientDto.getClientRepresentativId()))
                .orElseThrow(() -> new NotFoundException("ClientRepresentativ not found"));
        FormOwnership formOwnership = formOwnershipRepository.findById(UUID.fromString(requestClientDto.getFormOwnershipId()))
                .orElseThrow(() -> new NotFoundException("FormOwnership not found"));
        Client newClient = client.toBuilder()
                .formOwnershipId(formOwnership)
                .clientRepresentativId(clientRepresentativ)
                .okvedId(okved)
                .businessVolId(businessVolume)
                .employeeId(UUID.fromString(requestEmployeeId))
                .isActive(true)
                .build();
        Client savedClient = clientRepository.save(newClient);
        List<TelNumber> telNumbers = addAndGet(savedClient, requestClientDto.getTelNumber());

        ResponseClientDto responseClientDto = clientMapper.toDto(savedClient);
        responseClientDto.setTelNumber(requestClientDto.getTelNumber());
        return responseClientDto;
    }

    private List<TelNumber> addAndGet(Client client, List<String> telNumbers) {
        List<TelNumber> telNumberList = telNumberMapper.toListEntity(client, telNumbers);
        log.info("telNumberList: {}", telNumberList.get(0));
        return telNumberList.stream().map(telNumberService::saveTelNumber).toList();
    }

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
