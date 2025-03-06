package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.ClientOkvedDTO;
import aston.lab.clientserver.service.ClientOkvedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/v1.0/clients/okveds")
@RequiredArgsConstructor
public class ClientOkvedsController {

    private final ClientOkvedService clientOkvedService;

    @GetMapping
    public ResponseEntity<List<ClientOkvedDTO>> findAll() {
            List<ClientOkvedDTO> list = clientOkvedService.getAllClientOkveds();
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

}