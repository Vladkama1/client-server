package aston.lab.clientserver.controller;

import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.exception.BadRequestException;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.service.ClientService;
import aston.lab.clientserver.service.converter.ClientConverter;
import aston.lab.clientserver.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@DisplayName("Модульное тестирование ClientController")
public class ClientControllerTest {

    @MockitoBean
    private ClientService clientService;
    @MockitoBean
    private ClientRepository clientRepository;
    @MockitoBean
    private ClientConverter clientConverter;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Тест на успешное создание клиента")
    public void testSaveClientSuccess() throws Exception {
        String requestEmployeeId = "123e4567-e89b-12d3-a456-426614174001";

        when(clientService.saveClient(TestUtils.requestClientDto, requestEmployeeId))
                .thenReturn(TestUtils.responseClientDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/client/v1.0/clients")
                        .header("X-User-Id", requestEmployeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestUtils.requestClientDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("Тест на некорректные данные")
    public void testSaveClientBadRequest() throws Exception {
        String requestEmployeeId = "";

        when(clientService.saveClient(TestUtils.requestClientDto, requestEmployeeId))
                .thenThrow(new BadRequestException("RequestEmployeeId no corresponding value"));

        mockMvc.perform(MockMvcRequestBuilders.post("/client/v1.0/clients")
                        .header("X-User-Id", requestEmployeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestUtils.requestClientDto)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("Тест на невалидные данные")
    public void testSaveClientInvalid() throws Exception {
        String requestEmployeeId = "123e4567-e89b-12d3-a456-42664174001";
        RequestClientDto newrequestClientDto = RequestClientDto.builder()
                .inn(6321323773L)
                .ogrn(11363200L)
                .formOwnershipId("")
                .fullNameClient("ИнструментТекстиль")
                .nameClient("ООО ИТекстиль")
                .okvedId("123e4567-e89b-12d3-a456-426614174020")
                .businessVolId("123e4567-e89b-12d3")
                .curAccId("55084002971644000")
                .addressLegal("Самарская область, г. Тольятти")
                .clientRepresentativId("123e4567-e89b-12d3-a456-426614174004")
                .telNumber(List.of("12345678912", "73536402326"))
                .managerId("123e4567-e89b-12d3-a456-426614174005")
                .revenue(77.03)
                .build();
        when(clientService.saveClient(newrequestClientDto, requestEmployeeId))
                .thenThrow(new BadRequestException("RequestClientDto no invalid value"));

        mockMvc.perform(MockMvcRequestBuilders.post("/client/v1.0/clients")
                        .header("X-User-Id", requestEmployeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newrequestClientDto)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("Тест на успешный поиск по ИНН и ОГРН")
    void testfindClientByInnAndOgrn() throws Exception {
        long inn = 6321322525L;
        long ogrn = 1136320021512L;

        Mockito.when(clientService.findClientByInnAndOgrn(inn, ogrn)).thenReturn(TestUtils.responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/search?inn=6321322525&ogrn=1136320021512")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(clientService, times(1)).findClientByInnAndOgrn(inn, ogrn);
    }

    @Test
    @DisplayName("Тест если клиент не найден ")
    void testNotFoundClientFailure() throws Exception {
        long wrongInn = 6321322526L;
        long ogrn = 1136320021512L;

        doThrow(new ClientNotFoundException())
                .when(clientService).findClientByInnAndOgrn(wrongInn, ogrn);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/search?inn=6321322526&ogrn=1136320021512")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());

        Mockito.verify(clientService, times(1)).findClientByInnAndOgrn(wrongInn, ogrn);
    }

    @Test
    @DisplayName("Тест если неверные параметры запроса")
    void testNotValidClientFailure() throws Exception {
        long wrongInn = 63213225258L;
        long ogrn = 1136320021512L;

        doThrow(new CheckValidationException())
                .when(clientService).findClientByInnAndOgrn(wrongInn, ogrn);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/search?inn=63213225258&ogrn=1136320021512")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());

        Mockito.verify(clientService, times(1)).findClientByInnAndOgrn(wrongInn, ogrn);
    }
}