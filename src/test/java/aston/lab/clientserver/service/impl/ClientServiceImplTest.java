package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.dto.ClientDto;
import aston.lab.clientserver.entity.Client;
import aston.lab.clientserver.mapper.ClientMapper;
import aston.lab.clientserver.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Test
    void testSaveClientSuccess() {
        ClientDto clientDto = new ClientDto();
        Client clientEntity = new Client();
        when(clientMapper.toEntity(clientDto)).thenReturn(clientEntity);
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        when(clientMapper.toDto(clientEntity)).thenReturn(clientDto);


        ClientDto savedClientDto = clientServiceImpl.saveClient(clientDto);

        assertEquals(clientDto, savedClientDto);
        verify(clientRepository, times(1)).save(clientEntity);
    }

    @Test
    void testSaveClientFailure() {
        ClientDto clientDto = new ClientDto();
        when(clientMapper.toEntity(clientDto)).thenReturn(new Client());
        doThrow(new RuntimeException("Database error")).when(clientRepository).save(any(Client.class));

        assertThrows(RuntimeException.class, () -> clientServiceImpl.saveClient(clientDto));
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}