//package aston.lab.clientserver.controller;
//
//import aston.lab.clientserver.data.repository.ClientRepository;
//import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
//import aston.lab.clientserver.exception.CheckValidationException;
//import aston.lab.clientserver.exception.ClientNotFoundException;
//import aston.lab.clientserver.service.ClientService;
//import aston.lab.clientserver.service.converter.ClientConverter;
//import aston.lab.clientserver.utility.TestUtils;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.times;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ClientController.class)
//@DisplayName("Модульное тестирование ClientController")
//public class ClientControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @MockitoBean
//    ClientRepository clientRepository;
//    @MockitoBean
//    ClientConverter clientConverter;
//    @MockitoBean
//    ClientService clientService;
//
//    private final ClientFindByInnAndOgrnResponseDto responseDto = ClientFindByInnAndOgrnResponseDto.builder()
//            .clientRepresentativId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
//            .addressLegal("445047, Самарская область, г. Тольятти, Тополиная ул.,влд 1а, ком.53")
//            .fullNameClient("Общество с ограниченной ответственностью ИнструментТекстиль")
//            .nameClient("ООО ИнструментТекстиль")
//            .businessVolId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
//            .formOwnershipId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
//            .okvedId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
//            .curAccId("40702810000000000001")
//            .revenue(50.5f)
//            .telNumber(List.of("+12345678912", "+98765432198"))
//            .managerId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
//            .isActive(true)
//            .inn("6321322525")
//            .ogrn("1136320021512")
//            .build();
//
//    @Test
//    @DisplayName("Тест на успешный поиск по ИНН и ОГРН")
//    void testfindClientByInnAndOgrn() throws Exception {
//        String inn = "6321322525";
//        String ogrn = "1136320021512";
//
//        Mockito.when(clientService.findClientByInnAndOgrn(inn, ogrn)).thenReturn(TestUtils.responseDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/search?inn=6321322525&ogrn=1136320021512")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        Mockito.verify(clientService, times(1)).findClientByInnAndOgrn(inn, ogrn);
//    }
//
//    @Test
//    @DisplayName("Тест если клиент не найден ")
//    void testNotFoundClientFailure() throws Exception {
//        String wrongInn = "6321322526";
//        String ogrn = "1136320021512";
//
//        doThrow(new ClientNotFoundException())
//                .when(clientService).findClientByInnAndOgrn(wrongInn, ogrn);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/search?inn=6321322526&ogrn=1136320021512")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//
//        Mockito.verify(clientService, times(1)).findClientByInnAndOgrn(wrongInn, ogrn);
//    }
//
//    @Test
//    @DisplayName("Тест если неверные параметры запроса")
//    void testNotValidClientFailure() throws Exception {
//        String wrongInn = "63213225258";
//        String ogrn = "1136320021512";
//
//        doThrow(new CheckValidationException())
//                .when(clientService).findClientByInnAndOgrn(wrongInn, ogrn);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/search?inn=63213225258&ogrn=1136320021512")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//
//        Mockito.verify(clientService, times(1)).findClientByInnAndOgrn(wrongInn, ogrn);
//    }
//}
