package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.FormOwnership;
import aston.lab.clientserver.data.repository.FormOwnershipRepository;
import aston.lab.clientserver.dto.response.FormOwnershipDto;
import aston.lab.clientserver.service.FormOwnershipService;
import aston.lab.clientserver.service.converter.FormOwnershipConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormOwnershipServiceImpl implements FormOwnershipService {

    private final FormOwnershipRepository formOwnershipRepository;
    private final FormOwnershipConverter formOwnershipConverter;

    @Override
    public List<FormOwnershipDto> findAllFormOwnerships() {

        List<FormOwnership> formOwnerships = formOwnershipRepository.findAll();

        if (formOwnerships.isEmpty()) {
            throw new RuntimeException("Информация не найдена");
        }
        log.info("FormOwnerships is found: {}", formOwnerships);

        return formOwnerships.stream().map(formOwnershipConverter::formOwnershipDto).toList();
    }
}
