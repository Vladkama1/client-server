package aston.lab.clientserver.controller;


import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import aston.lab.clientserver.exception.BadRequestException;
import aston.lab.clientserver.data.repository.ClientRepository;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import aston.lab.clientserver.service.converter.ClientConverter;
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
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
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

    private final ClientFindByInnAndOgrnResponseDto responseDto = ClientFindByInnAndOgrnResponseDto.builder()
            .clientRepresentativId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .addressLegal("445047, Самарская область, г. Тольятти, Тополиная ул.,влд 1а, ком.53")
            .fullNameClient("Общество с ограниченной ответственностью ИнструментТекстиль")
            .nameClient("ООО ИнструментТекстиль")
            .businessVolId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .formOwnershipId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .okvedId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .curAccId("40702810000000000001")
            .revenue(50.5f)
            .telNumber(List.of("+12345678912", "+98765432198"))
            .managerId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .isActive(true)
            .inn(6321322525L)
            .ogrn(1136320021512L)
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

    @Test
    @DisplayName("Тест на успешный поиск по ИНН и ОГРН")
    void testfindClientByInnAndOgrn() throws Exception {
        long inn = 6321322525L;
        long ogrn = 1136320021512L;

        Mockito.when(clientService.findClientByInnAndOgrn(inn, ogrn)).thenReturn(responseDto);

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