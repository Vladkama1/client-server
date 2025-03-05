package aston.lab.clientserver.controller;

import aston.lab.clientserver.data.repository.FormOwnershipRepository;
import aston.lab.clientserver.service.FormOwnershipService;
import aston.lab.clientserver.service.converter.FormOwnershipConverter;
import aston.lab.clientserver.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FormOwnershipController.class)
class FormOwnershipControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    FormOwnershipRepository formOwnershipRepository;
    @MockitoBean
    FormOwnershipConverter formOwnershipConverter;
    @MockitoBean
    FormOwnershipService formOwnershipService;

    @Test
    @DisplayName("Тест на успешное получение всех записей")
    void testFindAllFormOwnerships() throws Exception {

        Mockito.when(formOwnershipService.findAllFormOwnerships()).thenReturn(TestUtils.formOwnershipDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/clients/formownerships")
                        .header("X-User-Id", "userId")
                        .header("X-User-Role", "role")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(formOwnershipService, Mockito.times(1)).findAllFormOwnerships();
    }

    @Test
    @DisplayName("Тест на пустой список")
    void testNotFoundAllFormOwnerships() throws Exception {

        Mockito.when(formOwnershipService.findAllFormOwnerships()).thenReturn(TestUtils.EmptyformOwnershipDtoList);

        doThrow(new RuntimeException())
                .when(formOwnershipService).findAllFormOwnerships();

        mockMvc.perform(MockMvcRequestBuilders.get("/client/v1.0/clients/formownerships")
                        .header("X-User-Id", "userId")
                        .header("X-User-Role", "role")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());

        Mockito.verify(formOwnershipService, Mockito.times(1)).findAllFormOwnerships();
    }
}