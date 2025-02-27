package aston.lab.clientserver.service;

import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;

public interface ClientService {
    ResponseClientDto saveClient (RequestClientDto requestClientDto, String requestEmployeeId);
}
