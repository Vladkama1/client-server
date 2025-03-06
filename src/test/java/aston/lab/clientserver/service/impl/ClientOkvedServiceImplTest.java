package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.repository.ClientOkvedRepository;
import aston.lab.clientserver.dto.ClientOkvedDTO;
import aston.lab.clientserver.exception.ClientOkvedNotFoundException;
import aston.lab.clientserver.service.mapper.ClientOkvedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ClientOkvedServiceImplTest {

    private final ClientOkvedRepository clientOkvedRepository = Mockito.mock();
    private final ClientOkvedMapper clientOkvedMapper = Mockito.mock();
    @InjectMocks
    private ClientOkvedServiceImpl clientOkvedService;
    List<ClientOkvedDTO> okvedListDto;

    @BeforeEach
    void setUp() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        ClientOkvedDTO dto1 = new ClientOkvedDTO(uuid1, "codeOkved1", "nameOkded1");
        ClientOkvedDTO dto2 = new ClientOkvedDTO(uuid2, "codeOkved2", "nameOkded2");
        okvedListDto = Arrays.asList(dto1, dto2);
    }

    @Test
    @DisplayName("Проверяет получение списка ОКВЭД из базы данных")
    public void checkGettingOkvedsListFromDataBase() {
        when(clientOkvedMapper.toListDto(anyList())).thenReturn(okvedListDto);

        assertEquals(okvedListDto, clientOkvedService.getAllClientOkveds());

        verify(clientOkvedRepository, times(1)).findAll();
        verify(clientOkvedMapper, times(1)).toListDto(anyList());
    }

    @Test
    @DisplayName("Вызывает ClientOkvedNotFoundException, когда список ОКВЭД пуст")
    public void throwClientOkvedNotFoundException_whenListOfOkvedsIsEmpty() {
        when(clientOkvedMapper.toListDto(anyList())).thenReturn(List.of());
        assertThrows(ClientOkvedNotFoundException.class, () -> clientOkvedService.getAllClientOkveds());
    }

}
