package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import aston.lab.clientserver.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/client/v1.0/")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @Operation(
            summary = "Создание нового клиента",
            description = "Создает нового клиента и возвращает тело."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Клиент успешно создан",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RequestClientDto.class))),
            @ApiResponse(responseCode = "400", description = "Неверные входные данные",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string")))
    })
    @PostMapping("clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseClientDto createClient(@RequestBody @Valid RequestClientDto requestClientDto,
                                          @RequestHeader("X-User-Id") @UUID String requestEmployeeId) {
        log.info("Создание клиента {}", requestClientDto);
        return clientService.saveClient(requestClientDto, requestEmployeeId);
    }
}
