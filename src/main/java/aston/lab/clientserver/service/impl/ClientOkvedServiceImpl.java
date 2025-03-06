package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.repository.ClientOkvedRepository;
import aston.lab.clientserver.dto.ClientOkvedDTO;
import aston.lab.clientserver.exception.ClientOkvedNotFoundException;
import aston.lab.clientserver.service.ClientOkvedService;
import aston.lab.clientserver.service.mapper.ClientOkvedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientOkvedServiceImpl implements ClientOkvedService {

    private final ClientOkvedRepository clientOkvedRepository;
    private final ClientOkvedMapper clientOkvedMapper;

    @Override
    public List<ClientOkvedDTO> getAllClientOkveds() {
        List<ClientOkvedDTO> list = clientOkvedMapper.toListDto(clientOkvedRepository.findAll());
        if (list.isEmpty()) {
                throw new ClientOkvedNotFoundException("Информация не найдена");
        }
        return list;
    }

}
