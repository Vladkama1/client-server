package aston.lab.clientserver.service;

import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import aston.lab.clientserver.dto.request.ActivationStatusRequest;
import aston.lab.clientserver.dto.response.ActivationStatusResponse;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;

public interface ClientService {
    ResponseClientDto saveClient (RequestClientDto requestClientDto, String requestEmployeeId);

    ClientFindByInnAndOgrnResponseDto findClientByInnAndOgrn(String inn, String ogrn);

    ActivationStatusResponse updateActivationStatus(ActivationStatusRequest request);

}
