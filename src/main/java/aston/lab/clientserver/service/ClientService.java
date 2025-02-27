package aston.lab.clientserver.service;

import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;

public interface ClientService {
    ResponseClientDto saveClient (RequestClientDto requestClientDto, String requestEmployeeId);

    ClientFindByInnAndOgrnResponseDto findClientByInnAndOgrn(Long inn, Long ogrn);
}
