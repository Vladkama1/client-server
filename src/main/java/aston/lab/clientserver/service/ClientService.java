package aston.lab.clientserver.service;

import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;

public interface ClientService {
    ClientFindByInnAndOgrnResponseDto findClientByInnAndOgrn(Long inn, Long ogrn);

}
