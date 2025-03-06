package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.response.ClientResponseDto;
import aston.lab.clientserver.service.impl.ClientUpdateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import static aston.lab.clientserver.utility.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientUpdateControllerTest {

    @Mock
    private ClientUpdateServiceImpl clientUpdateService;

    @InjectMocks
    private ClientUpdateController clientUpdateController;

    @Test
    void updateClient_Success() throws Exception {
        // Arrange
        when(clientUpdateService.updateClient(clientId, clientRequestDto)).thenReturn(clientResponseDto);

        // Act
        ResponseEntity<ClientResponseDto> responseEntity = clientUpdateController.updateClient(clientId, clientRequestDto, userId, userRole);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientResponseDto, responseEntity.getBody());
    }

    @Test
    void updateClient_InternalServerError() throws Exception {
        // Arrange
        when(clientUpdateService.updateClient(clientId, clientRequestDto))
                .thenThrow(new RuntimeException("Что-то пошло не так."));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            clientUpdateController.updateClient(clientId, clientRequestDto, userId, userRole);
        }, "Ожидаемое исключение RuntimeException не было выдано");
    }

    @Test
    void updateClient_BadRequest_InvalidData() throws Exception {
        // Arrange
        when(clientUpdateService.updateClient(clientId, clientRequestDto))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректные параметры запроса"));

        // Act & Assert
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            clientUpdateController.updateClient(clientId, clientRequestDto, userId, userRole);
        }, "Ожидаемое исключение ResponseStatusException не было выдано");

        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
        assertEquals("Некорректные параметры запроса", thrown.getReason());
    }

    @Test
    void updateClient_NoSuchElementException() throws Exception {
        // Arrange
        when(clientUpdateService.updateClient(clientId, clientRequestDto))
                .thenThrow(new NoSuchElementException("Клиент не найден"));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            clientUpdateController.updateClient(clientId, clientRequestDto, userId, userRole);
        }, "Ожидаемое исключение NoSuchElementException не было выдано");
    }
}