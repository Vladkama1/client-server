package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.service.converter.ClientConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientConverter clientConverterMock;
    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    private final ClientFindByInnAndOgrnResponseDto responseDto = ClientFindByInnAndOgrnResponseDto.builder()
            .fullNameClient("Общество с ограниченной ответственностью ИнструментТекстиль")
            .nameClient("ООО ИнструментТекстиль")
            .inn("6321322525")
            .ogrn("1136320021512")
            .build();
    private final Client client = Client.builder()
            .nameClient("ООО ИнструментТекстиль")
            .fullNameClient("Общество с ограниченной ответственностью ИнструментТекстиль")
            .inn("6321322525")
            .ogrn("1136320021512")
            .build();


    @Test
    @DisplayName("Тест успешного нахождения клиента по ИНН и ОГРН")
    void testFindByInnAndOgrnClientSuccess() {

        String inn = "6321322525";
        String ogrn = "1136320021512";
        Optional<Client> clientOptional = Optional.of(client);
        when(clientConverterMock.clientFindByInnAndOgrnResponseDto(client)).thenReturn(responseDto);
        when(clientRepository.findByInnAndOgrn(inn, ogrn)).thenReturn(clientOptional);

        ClientFindByInnAndOgrnResponseDto findClient = clientServiceImpl.findClientByInnAndOgrn(inn, ogrn);

        assertEquals(responseDto, findClient);
        verify(clientRepository, times(1)).findByInnAndOgrn(inn, ogrn);
    }

    @Test
    @DisplayName("Тест ошибки где клиент не найден")
    void testNotFoundClientFailure() {
        String wrongInn = "6321322526";
        String ogrn = "1136320021512";
        doThrow(new ClientNotFoundException("Клиент не найден"))
                .when(clientRepository).findByInnAndOgrn(wrongInn, ogrn);

        assertThrows(ClientNotFoundException.class, () -> clientServiceImpl.findClientByInnAndOgrn(wrongInn, ogrn));
        verify(clientRepository, times(1)).findByInnAndOgrn(wrongInn, ogrn);
    }

    @Test
    @DisplayName("Тест ошибки ввода некорректных данных")
    void testNotValidClientFailure() {
        String wrongInn = "63213225252";
        String ogrn = "1136320021512";
        assertThrows(CheckValidationException.class, () -> clientServiceImpl.findClientByInnAndOgrn(wrongInn, ogrn));
    }
}