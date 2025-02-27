package aston.lab.clientserver.service.impl;

import aston.lab.clientserver.data.model.TelNumber;
import aston.lab.clientserver.data.repository.TelNumberRepository;
import aston.lab.clientserver.service.TelNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelNumberServiceImpl implements TelNumberService {
    private final TelNumberRepository telNumberRepository;

    @Override
    @Transactional
    public TelNumber saveTelNumber(TelNumber telNumber) {
        return telNumberRepository.save(telNumber);
    }
}
