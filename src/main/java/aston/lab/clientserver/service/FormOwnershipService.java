package aston.lab.clientserver.service;

import aston.lab.clientserver.dto.response.FormOwnershipDto;

import java.util.List;

public interface FormOwnershipService {
    List<FormOwnershipDto> findAllFormOwnerships();
}
