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
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.exception.NotFoundException;
import aston.lab.clientserver.mapper.ClientMapper;
import aston.lab.clientserver.mapper.TelNumberMapper;
import aston.lab.clientserver.service.TelNumberService;
import aston.lab.clientserver.service.converter.ClientConverter;
import aston.lab.clientserver.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тестирование ClientService")
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private TelNumberService telNumberService;
    @Mock
    private BusinessVolumeRepository businessVolumeRepository;
    @Mock
    private ClientRepresentativRepository clientRepresentativRepository;
    @Mock
    private FormOwnershipRepository formOwnershipRepository;
    @Mock
    private OkvedRepository okvedRepository;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private TelNumberMapper telNumberMapper;
    @Mock
    private ClientConverter clientConverterMock;


    @Test
    @DisplayName("Тест на успешное создание клиента")
    void saveClient_success() {
        UUID clientId = UUID.randomUUID();
        UUID okvedId = UUID.randomUUID();
        UUID businessVolId = UUID.randomUUID();
        UUID clientRepresentativId = UUID.randomUUID();
        UUID formOwnershipId = UUID.randomUUID();
        UUID employeeId = UUID.randomUUID();
        RequestClientDto requestClientDto = new RequestClientDto();
        requestClientDto.setOkvedId(okvedId.toString());
        requestClientDto.setBusinessVolId(businessVolId.toString());
        requestClientDto.setClientRepresentativId(clientRepresentativId.toString());
        requestClientDto.setFormOwnershipId(formOwnershipId.toString());
        requestClientDto.setTelNumber(List.of("123-456-7890"));

        Client client = new Client();
        client.setId(clientId);

        when(clientMapper.toEntity(requestClientDto)).thenReturn(client);
        when(okvedRepository.findById(okvedId)).thenReturn(Optional.of(new Okved()));
        when(businessVolumeRepository.findById(businessVolId)).thenReturn(Optional.of(new BusinessVolume()));
        when(clientRepresentativRepository.findById(clientRepresentativId)).thenReturn(Optional.of(new ClientRepresentativ()));
        when(formOwnershipRepository.findById(formOwnershipId)).thenReturn(Optional.of(new FormOwnership()));

        TelNumber telNumber = new TelNumber();
        telNumber.setId(UUID.randomUUID());
        List<TelNumber> telNumberList = List.of(telNumber);
        when(telNumberMapper.toListEntity(any(Client.class), anyList())).thenReturn(telNumberList);
        when(telNumberService.saveTelNumber(any(TelNumber.class))).thenReturn(telNumber);
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toDto(any(Client.class))).thenReturn(new ResponseClientDto());

        ResponseClientDto result = clientService.saveClient(requestClientDto, employeeId.toString());

        assertNotNull(result);
    }

    @Test
    @DisplayName("Тест на исключение NotFoundException")
    void saveClient_notFoundException() {
        RequestClientDto requestClientDto = new RequestClientDto();
        requestClientDto.setOkvedId(UUID.randomUUID().toString());
        when(okvedRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clientService.saveClient(requestClientDto, UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("Тест успешного нахождения клиента по ИНН и ОГРН")
    void testFindByInnAndOgrnClientSuccess() {

        long inn = 6321322525L;
        long ogrn = 1136320021512L;
        Optional<Client> clientOptional = Optional.of(TestUtils.client);
        when(clientConverterMock.clientFindByInnAndOgrnResponseDto(TestUtils.client)).thenReturn(TestUtils.responsesDto);
        when(clientRepository.findByInnAndOgrn(inn, ogrn)).thenReturn(clientOptional);

        ClientFindByInnAndOgrnResponseDto findClient = clientService.findClientByInnAndOgrn(inn, ogrn);

        assertEquals(TestUtils.responsesDto, findClient);
        verify(clientRepository, times(1)).findByInnAndOgrn(inn, ogrn);
    }

    @Test
    @DisplayName("Тест ошибки где клиент не найден")
    void testNotFoundClientFailure() {
        long wrongInn = 6321322526L;
        long ogrn = 1136320021512L;
        doThrow(new ClientNotFoundException("Клиент не найден"))
                .when(clientRepository).findByInnAndOgrn(wrongInn, ogrn);

        assertThrows(ClientNotFoundException.class, () -> clientService.findClientByInnAndOgrn(wrongInn, ogrn));
        verify(clientRepository, times(1)).findByInnAndOgrn(wrongInn, ogrn);
    }

    @Test
    @DisplayName("Тест ошибки ввода некорректных данных")
    void testNotValidClientFailure() {
        long wrongInn = 63213225252L;
        long ogrn = 1136320021512L;
        assertThrows(CheckValidationException.class, () -> clientService.findClientByInnAndOgrn(wrongInn, ogrn));
    }
}
