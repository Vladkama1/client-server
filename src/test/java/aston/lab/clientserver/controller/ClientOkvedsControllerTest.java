package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.ClientOkvedDTO;
import aston.lab.clientserver.service.ClientOkvedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ClientOkvedsControllerTest {
    @InjectMocks
    private ClientOkvedsController controller;
    @Mock
    private ClientOkvedService clientOkvedService;

    List<ClientOkvedDTO> okvedList;

    @BeforeEach
    void setUp() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        ClientOkvedDTO dto1 = new ClientOkvedDTO(uuid1, "codeOkved1", "nameOkded1");
        ClientOkvedDTO dto2 = new ClientOkvedDTO(uuid2, "codeOkved2", "nameOkded2");
        okvedList = Arrays.asList(dto1, dto2);
    }

    @Test
    @DisplayName("Проверяет получение списка ОКВЭД и HttpStatus")
    public void checkGettingOkvedsListAndHttpStatus() {
        when(clientOkvedService.getAllClientOkveds()).thenReturn(okvedList);

        ResponseEntity<?> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(okvedList, response.getBody());

        verify(clientOkvedService, times(1)).getAllClientOkveds();

    }

}
