package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import aston.lab.clientserver.exception.CheckValidationException;
import aston.lab.clientserver.exception.ClientNotFoundException;
import aston.lab.clientserver.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/client/v1.0/")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @Operation(
            summary = "Поиск клиента по ИНН и ОГРН",
            description = "Проводит поиск клиента по ИНН и ОГРН возвращает данные о клиенте."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент найден",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientFindByInnAndOgrnResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string")))
    })
    @GetMapping("search")
    public ResponseEntity<?> findClientByInnAndOgrn(@RequestParam(name = "inn") Long inn,
                                                    @RequestParam(name = "ogrn") Long ogrn) {
        try {
            ClientFindByInnAndOgrnResponseDto client = clientService.findClientByInnAndOgrn(inn, ogrn);
            return ResponseEntity.status(HttpStatus.OK).body(client);
        } catch (CheckValidationException e) {
            log.error("Error find client: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверные параметры запроса");
        } catch (ClientNotFoundException e) {
            log.error("Error find client: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент не найден");
        }
    }
}
