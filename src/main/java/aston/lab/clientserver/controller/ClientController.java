package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.ClientDto;
import aston.lab.clientserver.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))),
            @ApiResponse(responseCode = "400", description = "Неверные входные данные",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "409", description = "Клиент с такими данными уже существует",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string")))
    })
    @PostMapping("clients")
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDto clientDto) {
        try {
            ClientDto createdClient = clientService.saveClient(clientDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } catch (DataIntegrityViolationException e) {
            log.error("Error saving client: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Клиент с такими данными уже существует");
        } catch (Exception e) {
            log.error("Error saving client: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка при сохранении клиента");
        }
    }
}
