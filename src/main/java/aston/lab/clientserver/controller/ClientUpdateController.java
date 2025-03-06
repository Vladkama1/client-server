package aston.lab.clientserver.controller;

import aston.lab.clientserver.dto.request.ClientRequestDto;
import aston.lab.clientserver.dto.response.ClientResponseDto;
import aston.lab.clientserver.service.ClientUpdateService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/client/v1.0/")
@RequiredArgsConstructor
public class ClientUpdateController {

    private final ClientUpdateService clientUpdateService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно изменен"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PatchMapping("client-update/{clientId}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable UUID clientId,
                                                          @Valid @RequestBody ClientRequestDto clientRequestDto,
                                                          @RequestHeader(value = "X-User-Id") String userId,
                                                          @RequestHeader(value = "X-User-Role") String userRole) {
        log.info("Получен запрос на обновление для client ID: {} для User ID: {} с Role: {}", clientId, userId, userRole);

        // Логика для проверки прав доступа в зависимости от userRole
        // if (!userRole.equals("ADMIN") && !userRole.equals("MANAGER")) {
        //     throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Недостаточно прав для выполнения операции");
        // }

        ClientResponseDto updatedClient = clientUpdateService.updateClient(clientId, clientRequestDto);
        log.info("Клиент успешно изменен", clientId);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
}

