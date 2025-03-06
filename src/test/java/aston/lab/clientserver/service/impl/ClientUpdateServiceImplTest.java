package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.utility.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static aston.lab.clientserver.utility.TestUtils.client;
import static aston.lab.clientserver.utility.TestUtils.clientId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientUpdateServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientUpdateServiceImpl clientUpdateService;

    TestUtils testUtils = new TestUtils();

    @Test
    void findById_ExistingClient_ReturnsOptionalWithClient() {
        // Arrange
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // Act
        Optional<Client> actualClient = clientUpdateService.findById(clientId);

        // Assert
        assertTrue(actualClient.isPresent());
        assertEquals(client, actualClient.get());
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void findById_NonExistingClient_ReturnsEmptyOptional() {
        // Arrange
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act
        Optional<Client> actualClient = clientUpdateService.findById(clientId);

        // Assert
        assertFalse(actualClient.isPresent());
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void save_ValidClient_ReturnsSavedClient() {
        // Arrange
        Client expectedClient = new Client();
        expectedClient.setId(clientId);

        when(clientRepository.save(client)).thenReturn(expectedClient);

        // Act
        Client actualClient = clientUpdateService.save(client);

        // Assert
        assertEquals(expectedClient, actualClient);
        verify(clientRepository, times(1)).save(client);
    }
}