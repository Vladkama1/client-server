package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.repository.FormOwnershipRepository;
import aston.lab.clientserver.dto.response.FormOwnershipDto;
import aston.lab.clientserver.service.converter.FormOwnershipConverter;
import aston.lab.clientserver.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class FormOwnershipServiceImplTest {
    @Mock
    FormOwnershipRepository formOwnershipRepositoryMock;
    @Mock
    FormOwnershipConverter formOwnershipConverterMock;
    @InjectMocks
    FormOwnershipServiceImpl formOwnershipServiceImpl;

    @Test
    void testFindAllFormOwnershipsSuccess() {

        Mockito.when(formOwnershipRepositoryMock.findAll()).thenReturn(TestUtils.formOwnershipList);

        List<FormOwnershipDto> formOwnershipDtos = formOwnershipServiceImpl.findAllFormOwnerships();

        assertEquals(TestUtils.formOwnershipList.size(), formOwnershipDtos.size());
        verify(formOwnershipRepositoryMock,times(1)).findAll();

    }

    @Test
    void testNotFoundAllFormOwnershipsFailure() {

            doThrow(new RuntimeException())
                    .when(formOwnershipRepositoryMock).findAll();

        assertThrows(RuntimeException.class, () -> formOwnershipServiceImpl.findAllFormOwnerships());
        verify(formOwnershipRepositoryMock, times(1)).findAll();



    }
}