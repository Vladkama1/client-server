package aston.lab.clientserver.controller;


import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import aston.lab.clientserver.exception.BadRequestException;
import aston.lab.clientserver.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@DisplayName("Модульное тестирование ClientController")
public class ClientControllerTest {

    @MockitoBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final RequestClientDto requestClientDto = RequestClientDto.builder()
            .inn(6321323773L)
            .ogrn(11363200L)
            .formOwnershipId("123e4567-e89b-12d3-a456-426614174001")
            .fullNameClient("ИнструментТекстиль")
            .nameClient("ООО ИТекстиль")
            .okvedId("123e4567-e89b-12d3-a456-426614174020")
            .businessVolId("123e4567-e89b-12d3-a456-426614174003")
            .curAccId("55084002941471644000")
            .addressLegal("Самарская область, г. Тольятти")
            .clientRepresentativId("123e4567-e89b-12d3-a456-426614174004")
            .telNumber(List.of("12345678912", "73536402326"))
            .managerId("123e4567-e89b-12d3-a456-426614174005")
            .revenue(77.03)
            .build();

    private final ResponseClientDto responseClientDto = ResponseClientDto.builder()
            .inn(6321323773L)
            .ogrn(11363200L)
            .formOwnershipId("123e4567-e89b-12d3-a456-426614174001")
            .fullNameClient("ИнструментТекстиль")
            .nameClient("ООО ИТекстиль")
            .okvedId(UUID.fromString("123e4567-e89b-12d3-a456-426614174020"))
            .businessVolId(UUID.fromString("123e4567-e89b-12d3-a456-426614174003"))
            .curAccId("55084002941471644000")
            .addressLegal("Самарская область, г. Тольятти")
            .clientRepresentativId(UUID.fromString("123e4567-e89b-12d3-a456-426614174004"))
            .telNumber(List.of("12345678912", "73536402326"))
            .managerId(UUID.fromString("123e4567-e89b-12d3-a456-426614174005"))
            .revenue(77.03)
            .build();

    @Test
    @DisplayName("Тест на успешное создание клиента")
    public void testSaveClientSuccess() throws Exception {
        String requestEmployeeId = "123e4567-e89b-12d3-a456-426614174001";

        when(clientService.saveClient(requestClientDto, requestEmployeeId))
                .thenReturn(responseClientDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/client/v1.0/clients")
                        .header("X-User-Id", requestEmployeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestClientDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("Тест на некорректные данные")
    public void testSaveClientBadRequest() throws Exception {
        String requestEmployeeId = "";

        when(clientService.saveClient(requestClientDto, requestEmployeeId))
                .thenThrow(new BadRequestException("RequestEmployeeId no corresponding value"));

        mockMvc.perform(MockMvcRequestBuilders.post("/client/v1.0/clients")
                        .header("X-User-Id", requestEmployeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestClientDto)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}